package hr.keychain.keychain.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.List;

import hr.keychain.database.entities.Key;
import hr.keychain.keychain.IzbornikActivity;
import hr.keychain.keychain.R;
import hr.keychain.keychain.helper.Internet;
import hr.keychain.keychain.helper.Session;
import hr.keychain.webservice.WebService;
import hr.keychain.webservice.responses.GenericResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class AddKeyFragment extends Fragment implements View.OnClickListener {

    private final int FINE_LOCATION = 1000;
    private final String baseUrl = "http://arka.foi.hr/WebDiP/2014_projekti/WebDiP2014x054/WS/";
    private String service = "addKey";
    private EditText name, description, address, code;
    private Button btnSave;
    private View view;

    public AddKeyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_key, container, false);
        name = (EditText) view.findViewById(R.id.key_name);
        name.setText("");
        description = (EditText) view.findViewById(R.id.key_description);
        description.setText("");
        address = (EditText) view.findViewById(R.id.key_address);
        address.setText("");
        code = (EditText) view.findViewById(R.id.key_code);
        code.setText("");
        btnSave = (Button) view.findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(this);

        //postavljanje naslova na ActionBar
        ((IzbornikActivity) getActivity()).setActionBarTitle("ADD KEY");
        return view;
    }


    @Override
    public void onClick(View v) {
        if (!name.getText().toString().isEmpty() && !description.getText().toString().isEmpty()
                && !address.getText().toString().isEmpty() && !code.getText().toString().isEmpty()) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        FINE_LOCATION);

            }
            else {
                getGeolocation(name.getText().toString(), description.getText().toString(), address.getText().toString(), code.getText().toString());
            }

        }
        else {
            displayToast("Fields are empty");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getGeolocation(name.getText().toString(), description.getText().toString(), address.getText().toString(), code.getText().toString());
                //addKey(name.getText().toString(), description.getText().toString(), code.getText().toString());
            }
            else {
                return;
            }
        }
    }

    private void addKey(final String name, final String description, final String address, final double longitude, final double latitude, final String code) {
        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        Session session = new Session(getContext());
        final String mail = session.getUsername();
        WebService serviceCaller = retrofit.create(WebService.class);
        Call<GenericResponse> call = serviceCaller.addKey(service, mail, name, description, address, longitude, latitude, code);
        if(call != null){
            call.enqueue(new Callback<GenericResponse>() {
                @Override
                public void onResponse(Response<GenericResponse> returnedResponse, Retrofit retrofit) {
                    try{
                        if(returnedResponse.isSuccess()){
                            if (returnedResponse.body().getCode() == 10) {
                                writeLocalDB(name, description, address, longitude, latitude, code, mail);
                                displayToast("Key successfully added");
                                AddKeyFragment addKeyFragment = new AddKeyFragment();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.detach(addKeyFragment);
                                fragmentTransaction.attach(addKeyFragment);

                                fragmentTransaction.commit();
                            } else {
                                displayToast(returnedResponse.body().getMessage());
                            }
                        }
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    //fetches latitude and longitude of a given address
    private void getGeolocation(String name, String description, String address, String code) {
        List<Address> addresses = null;
        Geocoder geocoder = new Geocoder(getContext());
        try {
            addresses = geocoder.getFromLocationName(address, 1);

            if (addresses == null || addresses.size()  == 0) {
                displayToast("Can't fetch address coordinates");
                return;
            }
            else {
                double longitude = addresses.get(0).getLongitude();
                double latitude = addresses.get(0).getLatitude();
                Session session = new Session(getContext());
                addKey(name, description, address, longitude, latitude, code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeLocalDB(String name, String description, String address, double longitude, double latitude, String code, String mail) {
        FlowManager.init(new FlowConfig.Builder(getContext()).build());

        Key key = new Key();
        key.setName(name);
        key.setDescription(description);
        key.setAddress(address);
        key.setLongitude(longitude);
        key.setLatitude(latitude);
        key.setCode(code);
        key.setMail(mail);
        key.save();
    }

    //displays the toast on screen
    private void displayToast(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}

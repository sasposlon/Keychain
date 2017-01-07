package hr.keychain.keychain.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.okhttp.OkHttpClient;

import hr.keychain.database.entities.User;
import hr.keychain.keychain.R;
import hr.keychain.webservice.WebService;
import hr.keychain.webservice.responses.GenericResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Saša Poslončec on 19.12.16..
 */

public class RegisterFragment extends Fragment implements View.OnClickListener{
    private final String baseUrl = "http://arka.foi.hr/WebDiP/2014_projekti/WebDiP2014x054/WS/";
    private final String service = "registration";
    private View view;
    private Button btnRegister;
    private EditText editMail, editPassword;

    public RegisterFragment () {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);
        editMail = (EditText) view.findViewById(R.id.email_registration);
        editPassword = (EditText) view.findViewById(R.id.lozinka_registration);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                if (!editMail.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty()) {
                    registerProcess(editMail.getText().toString(), editPassword.getText().toString());
                }
                else {
                    Toast toast = Toast.makeText(getContext(), "Fields are empty", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    private void registerProcess(final String email, final String password) {
        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        WebService serviceCaller = retrofit.create(WebService.class);
        Call<GenericResponse> call = serviceCaller.registration(service, email, password);
        if(call != null){
            call.enqueue(new Callback<GenericResponse>() {
                @Override
                public void onResponse(Response<GenericResponse> returnedResponse, Retrofit retrofit) {
                    try{
                        if(returnedResponse.isSuccess()){
                            if (returnedResponse.body().getCode() == 10) {
                                Toast toast = Toast.makeText(getContext(), "Registration successful", Toast.LENGTH_SHORT);
                                toast.show();
                                LoginFragment loginFragment = new LoginFragment();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.login_registration_container, loginFragment);

                                FlowManager.init(new FlowConfig.Builder(getContext()).build()); //Initialize DB
                                writeDb(returnedResponse.body().getItems().getMail(), returnedResponse.body().getItems().getPassword()); //Write data to local DB


                                fragmentManager.popBackStack();
                                fragmentTransaction.commit();
                            } else {
                                Toast toast = Toast.makeText(getContext(), returnedResponse.body().getMessage(), Toast.LENGTH_SHORT);
                                toast.show();
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

    //Method for writin a new user to local DB
    public void writeDb (String mail, String password) {
        User user = new User();
        user.setMail(mail);
        user.setPassword(password);
        user.save();
    }
}

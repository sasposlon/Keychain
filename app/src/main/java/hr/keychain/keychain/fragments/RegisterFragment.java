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

import com.squareup.okhttp.OkHttpClient;

import hr.keychain.keychain.R;
import hr.keychain.webservice.WebService;
import hr.keychain.webservice.responses.WebServiceResponse;
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
                String mail = editMail.getText().toString();
                String password = editPassword.getText().toString();
                if (!mail.isEmpty() && !password.isEmpty()) {
                    registerProcess(mail, password);
                }
                else {
                    Toast toast = Toast.makeText(getContext(), "Fields are empty", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    private void registerProcess(String email, String password) {
        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        WebService serviceCaller = retrofit.create(WebService.class);
        Call<WebServiceResponse> call = serviceCaller.registration(service, email, password);
        if(call != null){
            call.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Response<WebServiceResponse> returnedResponse, Retrofit retrofit) {
                    try{
                        if(returnedResponse.isSuccess()){
                            if (returnedResponse.body().getCode() == 10) {
                                Toast toast = Toast.makeText(getContext(), "Registration successful", Toast.LENGTH_SHORT);
                                toast.show();
                                LoginFragment loginFragment = new LoginFragment();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.login_registration_container, loginFragment);
                                fragmentTransaction.commit();
                            } else {
                                Toast toast = Toast.makeText(getContext(), returnedResponse.body().getMessage().toString(), Toast.LENGTH_SHORT);
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
}

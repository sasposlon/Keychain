package hr.keychain.keychain.fragments;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import hr.keychain.database.entities.User;
import hr.keychain.keychain.IzbornikActivity;
import hr.keychain.keychain.R;
import hr.keychain.webservice.WebService;
import hr.keychain.webservice.responses.RegistrationResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Saša Poslončec on 19.12.16..
 */

public class LoginFragment extends Fragment implements View.OnClickListener{
    private final String baseUrl = "http://arka.foi.hr/WebDiP/2014_projekti/WebDiP2014x054/WS/";
    private final String service = "login";
    private View view;
    private Button btnLogin;
    private TextView btnCreateAcc;
    private EditText editMail, editPassword;

    public LoginFragment () {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        editMail = (EditText) view.findViewById(R.id.email);
        editPassword = (EditText) view.findViewById(R.id.lozinka);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnCreateAcc = (TextView) view.findViewById(R.id.btnCreateAcc);
        btnCreateAcc.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (!editMail.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty()) {
                    login(editMail.getText().toString(), editPassword.getText().toString());
                }
                else {
                    Toast toast = Toast.makeText(getContext(), "Fields are empty", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;

            case R.id.btnCreateAcc:
                createAccount();
                break;
        }
    }

    private void login(String mail, String password){
        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        WebService serviceCaller = retrofit.create(WebService.class);
        Call<RegistrationResponse> call = serviceCaller.login(service, mail, password);
        if(call != null){
            call.enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Response<RegistrationResponse> returnedResponse, Retrofit retrofit) {
                    try{
                        if(returnedResponse.isSuccess()){
                            if (returnedResponse.body().getCode() == 10) {
                                Toast toast = Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT);
                                toast.show();
                                Intent myIntent = new Intent(getActivity(), IzbornikActivity.class);
                                startActivity(myIntent);
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
        //FlowManager.init(new FlowConfig.Builder(getContext()).build());
        //readDb(mail, password);
    }

    private void createAccount () {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RegisterFragment registerFragment = new RegisterFragment();
        fragmentTransaction.replace(R.id.login_registration_container, registerFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /*
    private void readDb (String mail, String password) {
        User users = SQLite.select().from(User.class).querySingle();
        if (users != null) {
            //otvaranje nove aktivnosti
            Intent myIntent = new Intent(getActivity(), IzbornikActivity.class);
            startActivity(myIntent);
            Toast toast = Toast.makeText(getContext(), "Login succsessful", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(getContext(), "Wrong e-mail or password", Toast.LENGTH_SHORT);
            toast.show();
        }
    }*/
}

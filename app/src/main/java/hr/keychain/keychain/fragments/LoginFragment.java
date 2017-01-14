package hr.keychain.keychain.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.squareup.okhttp.OkHttpClient;

import hr.keychain.database.entities.User;
import hr.keychain.database.entities.User_Table;
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

/**
 * Created by Saša Poslončec on 19.12.16..
 */

public class LoginFragment extends Fragment implements View.OnClickListener{
    private final String baseUrl = "http://arka.foi.hr/WebDiP/2014_projekti/WebDiP2014x054/WS/";
    private final String service = "login";
    private View view;
    private Button btnLogin;
    private TextView btnCreateAcc, btnForgotPassword;
    private EditText editMail, editPassword;
    private Session session;
    private Internet internet = new Internet();

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
        btnForgotPassword = (TextView) view.findViewById(R.id.btnForgotPass);
        btnForgotPassword.setOnClickListener(this);
        session = new Session(getContext());
        if(!internet.internetConnectivity(getContext())) {
            Toast toast = Toast.makeText(getContext(), "Offline mode", Toast.LENGTH_SHORT);
            toast.show();
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (!editMail.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty()) {
                    if(internet.internetConnectivity(getContext())) {
                        login(editMail.getText().toString(), editPassword.getText().toString());
                    }
                    else {
                        loginLocal(editMail.getText().toString(), editPassword.getText().toString());
                    }
                }
                else {
                    displayToast("Fields are empty");
                }
                break;

            case R.id.btnCreateAcc:
                createAccount();
                break;

            case R.id.btnForgotPass:
                forgotPassword();
                break;
        }
    }

    private void login(final String mail, String password){
        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        WebService serviceCaller = retrofit.create(WebService.class);
        Call<GenericResponse> call = serviceCaller.login(service, mail, password);
        if(call != null){
            call.enqueue(new Callback<GenericResponse>() {
                @Override
                public void onResponse(Response<GenericResponse> returnedResponse, Retrofit retrofit) {
                    try{
                        if(returnedResponse.isSuccess()){
                            if (returnedResponse.body().getCode() == 10) {
                                displayToast("Login successful");
                                session.setUsername(mail);
                                Intent myIntent = new Intent(getActivity(), IzbornikActivity.class);
                                startActivity(myIntent);
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
        //FlowManager.init(new FlowConfig.Builder(getContext()).build());
        //readDb(mail, password);
    }

    private void createAccount () {
        if(internet.internetConnectivity(getContext())) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RegisterFragment registerFragment = new RegisterFragment();
            fragmentTransaction.replace(R.id.login_registration_container, registerFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else {
            displayToast("Please go online to use this feature");
        }
    }

    private void forgotPassword() {
        if(internet.internetConnectivity(getContext())) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ForgotPasswordFragment forgotPass = new ForgotPasswordFragment();
            fragmentTransaction.replace(R.id.login_registration_container, forgotPass);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else {
            displayToast("Please go online to use this feature");
        }
    }

    private void loginLocal (String mail, String password) {
        FlowManager.init(new FlowConfig.Builder(getContext()).build());

        if (new Select().from(User.class).where(Condition.column(User_Table.mail.getNameAlias())
                .eq(mail)).and(Condition.column(User_Table.password.getNameAlias()).eq(password))
                .querySingle() != null) {
            //otvaranje nove aktivnosti
            Intent myIntent = new Intent(getActivity(), IzbornikActivity.class);
            startActivity(myIntent);
            displayToast("Login succsessful");
        }
        else {
            displayToast("Wrong e-mail or password");
        }
    }

    private void displayToast(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}

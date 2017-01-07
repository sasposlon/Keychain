package hr.keychain.keychain.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;

import hr.keychain.keychain.R;
import hr.keychain.webservice.WebService;
import hr.keychain.webservice.responses.GenericResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 24.12.16..
 */

public class ForgotPasswordFragment extends Fragment implements View.OnClickListener {
    private final String baseUrl = "http://arka.foi.hr/WebDiP/2014_projekti/WebDiP2014x054/WS/";
    private final String service = "forgotPassword";
    private View view;
    private Button btnSubmit;
    private EditText editMail;

    public ForgotPasswordFragment () {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        editMail = (EditText) view.findViewById(R.id.email_forgot_pass);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                if (!editMail.getText().toString().isEmpty()) {
                    forgotPasswordProcess(editMail.getText().toString());
                }
                else {
                    Toast toast = Toast.makeText(getContext(), "Email field empty", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }



    public void forgotPasswordProcess (String mail) {
        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        WebService serviceCaller = retrofit.create(WebService.class);
        Call<GenericResponse> call = serviceCaller.forgotPassword(service, mail);
        if(call != null){
            call.enqueue(new Callback<GenericResponse>() {
                @Override
                public void onResponse(Response<GenericResponse> returnedResponse, Retrofit retrofit) {
                    try{
                        if(returnedResponse.isSuccess()){
                            if (returnedResponse.body().getCode() == 10) {
                                Toast toast = Toast.makeText(getContext(), "Registration successful", Toast.LENGTH_SHORT);
                                toast.show();
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
}

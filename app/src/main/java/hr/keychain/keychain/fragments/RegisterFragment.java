package hr.keychain.keychain.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import hr.keychain.keychain.R;
import hr.keychain.webservice.WebServiceCaller;

/**
 * Created by Saša Poslončec on 19.12.16..
 */

public class RegisterFragment extends Fragment implements View.OnClickListener{
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
                System.out.println(editMail.getText().toString());
                System.out.println(editPassword.getText().toString());
                register();
                break;
        }
    }

    private void register() {
        WebServiceCaller webServiceCaller = new WebServiceCaller();
        webServiceCaller.registration(service, editMail.getText().toString(), editPassword.getText().toString());
    }
}

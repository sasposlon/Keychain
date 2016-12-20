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

import hr.keychain.keychain.R;

/**
 * Created by Saša Poslončec on 19.12.16..
 */

public class LoginFragment extends Fragment implements View.OnClickListener{
    View view;
    Button btnLogin;
    Button btnCreateAcc;

    public LoginFragment () {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnCreateAcc = (Button) view.findViewById(R.id.btnCreateAcc);
        btnCreateAcc.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                login();
                break;

            case R.id.btnCreateAcc:
                createAccount();
                break;
        }
    }

    private void login(){

    }

    private void createAccount () {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RegisterFragment registerFragment = new RegisterFragment();
        fragmentTransaction.replace(R.id.login_registration_container, registerFragment);
        fragmentTransaction.addToBackStack("login");
        fragmentTransaction.commit();
    }
}

package hr.keychain.keychain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hr.keychain.keychain.fragments.LoginFragment;

public class LoginRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration);

        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.login_registration_container, loginFragment).commit();
    }
}

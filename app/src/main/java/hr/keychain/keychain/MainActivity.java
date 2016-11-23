package hr.keychain.keychain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import hr.keychain.webservice.WebServiceCaller;


public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlowManager.init(new FlowConfig.Builder(this).build());
        login();
    }
    public void login() {
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.lozinka);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener( new View.OnClickListener(){
                                         @Override
                                         public void onClick(View v){
                                             WebServiceCaller webServiceCaller = new WebServiceCaller();
                                             webServiceCaller.registration("registration", etEmail.getText().toString(), etPassword.getText().toString());
                                             if(etEmail.getText().toString().equals("admin") && etPassword.getText().toString().equals("admin")  ) {
                                                 Toast.makeText(MainActivity.this,"Email and Password is correct",Toast.LENGTH_SHORT).show();

                                                 //otvaranje nove aktivnosti
                                                 Intent myIntent = new Intent(MainActivity.this, IzbornikActivity.class);
                                                 startActivity(myIntent);
                                                 finish();

                                             } else {
                                                 Toast.makeText(MainActivity.this,"Email and Password is not correct",Toast.LENGTH_SHORT).show();
                                             }
                                         }
                                     }
        );
    }


}
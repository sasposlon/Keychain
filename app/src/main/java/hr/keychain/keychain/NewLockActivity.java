package hr.keychain.keychain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Ines on 29.11.2016..
 */

public class NewLockActivity extends IzbornikActivity {

    private Toolbar toolbar;
    TextView mName, mDescription, mNFC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("NEW LOCK");
        setContentView(R.layout.activity_new_lock);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }




}

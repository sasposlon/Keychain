package hr.keychain.keychain;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import hr.keychain.keychain.fragments.AddKeyFragment;
import hr.keychain.keychain.fragments.AllKeysFragment;
import hr.keychain.keychain.fragments.LockFragment;
import hr.keychain.keychain.fragments.UnlockFragment;


public class MainActivity extends AppCompatActivity {
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UnlockFragment unlockFragment = new UnlockFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, unlockFragment).commit();
}




    }



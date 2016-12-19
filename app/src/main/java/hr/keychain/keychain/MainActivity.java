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

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case (R.id.menu_add):
                                fragment = new AddKeyFragment();
                                break;

                            case (R.id.menu_unlock):
                                fragment = new UnlockFragment();
                                break;

                            case (R.id.menu_lock):
                                fragment = new LockFragment();
                                break;

                            case (R.id.menu_all_keys):
                                fragment = new AllKeysFragment();
                                break;
                        }
                        final FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        fragmentTransaction.commit();
                        return true;
                    }
                }
        );
    }


}
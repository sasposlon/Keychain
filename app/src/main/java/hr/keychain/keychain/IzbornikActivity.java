package hr.keychain.keychain;

/**
 * Created by Ines on 12.11.2016..
 */

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import hr.keychain.keychain.fragments.AddKeyFragment;
import hr.keychain.keychain.fragments.AllKeysFragment;
import hr.keychain.keychain.fragments.IzbornikFragment;
import hr.keychain.keychain.fragments.LockFragment;

public class IzbornikActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbornik);

        //Postavljanje toolbara i drawer layout-a
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //Odmah kod kreiranja aktivnosti Izbornik poziva se fragment izbornik
        //koji se učitava u fragment_container
        fragment = new AllKeysFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case (R.id.menu_add):
                                fragment = new AddKeyFragment();
                                break;

                            case (R.id.menu_lock):
                                fragment = new LockFragment();
                                break;

                            case (R.id.menu_all_keys):
                                fragment = new AllKeysFragment();
                                break;
                        }
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, fragment)
                                .commit();

                        return true;
                    }
                }
        );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    //postavljanje tipka za vracanje kod fragmenata
    @Override
    public void onBackPressed() {
        getSupportFragmentManager()
                .popBackStack();
    }

    //metoda koja se poziva kod postavljanja naslova ActioBara
    public void setActionBarTitle(String title){
        getSupportActionBar()
                .setTitle(title);
    }
}
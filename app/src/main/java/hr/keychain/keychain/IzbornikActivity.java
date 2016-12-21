package hr.keychain.keychain;

/**
 * Created by Ines on 12.11.2016..
 */

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hr.keychain.keychain.adapteri.IzbornikAdapter;
import hr.keychain.keychain.klase.Izbornik;

public class IzbornikActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private List<Izbornik> izbornikList = new ArrayList<>();
    private RecyclerView recyclerView;
    private IzbornikAdapter mAdapter;

    PlayersFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MENI");
        setContentView(R.layout.activity_izbornik);

        //Postavljanje toolbara i drawer layout-a
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new IzbornikAdapter(izbornikList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        prepareIzbornik();


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

    private void prepareIzbornik(){
        Izbornik i = new Izbornik("New Lock", R.drawable.dl_logout);
        izbornikList.add(i);
        i = new Izbornik("All Locks", R.drawable.dl_sync);
        izbornikList.add(i);


        mAdapter.notifyDataSetChanged();

    }



    public void clickHandler(View view) {

        fragment = new PlayersFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }
}
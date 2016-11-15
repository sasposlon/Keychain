package hr.keychain.keychain;

/**
 * Created by Ines on 12.11.2016..
 */

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class IzbornikActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MENI");
        setContentView(R.layout.activity_izbornik);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.All_Locks_id:
                Toast.makeText(getApplicationContext(), "All locks optionselected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Lock_id:
                Toast.makeText(getApplicationContext(), "Lock option selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.NewLock_id:
                Toast.makeText(getApplicationContext(), "New lock option selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Unlock_id:
                Toast.makeText(getApplicationContext(), "Unlock option selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}

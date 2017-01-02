package hr.keychain.keychain.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import hr.keychain.keychain.IzbornikActivity;
import hr.keychain.keychain.JSONParser;
import hr.keychain.keychain.R;


public class AllKeysFragment extends Fragment {

    private final String baseUrl = "http://arka.foi.hr/WebDiP/2014_projekti/WebDiP2014x054/WS/";
    private final String servise = "allLocks";

    public AllKeysFragment(){}

    private JSONParser p = new JSONParser();

    ArrayList<String> keys = new ArrayList<String>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_keys, container, false);

        for(int i=0; i<5; i++ ) {   //dok ne povezem sa servisima
            String k = ("kljuc");
            keys.add(k);
        }
        ListView listview = (ListView) view.findViewById(R.id.listaKljuceva); //gdje prikazujemo sadrzaj(listView u layoutu)
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(      //adapter(aktivnost, layout za svaki red(template od androida), podaci)
                getActivity(), android.R.layout.simple_list_item_1, keys);
        listview.setAdapter(listViewAdapter);
        registerForContextMenu(listview);//postavljanje adaptera, prikaz sadrzaja

        //postavljanje naslova na ActionBar
        ((IzbornikActivity) getActivity()).setActionBarTitle("ALL LOCKS");

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.listaKljuceva){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(keys.get(info.position));
            String[] menuItems = getResources().getStringArray(R.array.context_menu_items);
            for (int i = 0; i<menuItems.length; i++){
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.context_menu_items);
        String menuItemName = menuItems[menuItemIndex];
        Toast.makeText(getActivity(), "Odabrali ste " + menuItemName, Toast.LENGTH_SHORT).show();

        return true;
    }
}

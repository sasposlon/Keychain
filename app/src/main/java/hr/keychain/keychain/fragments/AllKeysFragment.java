package hr.keychain.keychain.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hr.keychain.keychain.IzbornikActivity;
import hr.keychain.keychain.JSONParser;
import hr.keychain.keychain.R;


public class AllKeysFragment extends Fragment {

    private final String baseUrl = "http://arka.foi.hr/WebDiP/2014_projekti/WebDiP2014x054/WS/";
    private final String servise = "allLocks";

    public AllKeysFragment(){}

    private JSONParser p = new JSONParser();

    ArrayList<String> keys = null;
    ArrayAdapter<String> listViewAdapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_keys, container, false);

        keys = new ArrayList<String>();                                             // keys = p.parseAllLocks
        for(int i=0; i<5; i++ ) {                                                   //dok ne povezem sa servisima
            String k = ("kljuc");
            keys.add(k);
        }
        ListView listview = (ListView) view.findViewById(R.id.listaKljuceva);       //gdje prikazujemo sadrzaj(listView u layoutu)
        listViewAdapter = new ArrayAdapter<String>(                                 //adapter(aktivnost, layout za svaki red(template od androida), podaci)
                getActivity(), android.R.layout.simple_list_item_1, keys);
        listview.setAdapter(listViewAdapter);
        registerForContextMenu(listview);                                          //postavljanje adaptera, prikaz sadrzaja

        //postavljanje naslova na ActionBar
        ((IzbornikActivity) getActivity()).setActionBarTitle("ALL KEYS");

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
        int selectedIndex = info.position;
        String selectedItem =keys.get(selectedIndex);

        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.context_menu_items);
        String menuItemName = menuItems[menuItemIndex];

        Fragment fragment = null;
        switch (item.getItemId()){
            case 0:
                fragment = new AddKeyFragment();
                Toast.makeText(getActivity(), "Odabrali ste " + menuItemName, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                keys.remove(selectedItem);
                listViewAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), selectedItem + " deleted!", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getActivity(), "Odabrali ste " + menuItemName, Toast.LENGTH_SHORT).show();
                break;


        }
        if(fragment != null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }

        return true;
    }
}

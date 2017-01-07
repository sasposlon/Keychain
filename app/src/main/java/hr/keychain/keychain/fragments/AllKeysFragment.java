package hr.keychain.keychain.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hr.keychain.keychain.IzbornikActivity;
import hr.keychain.keychain.R;
import hr.keychain.keychain.helper.Session;


public class AllKeysFragment extends Fragment {

    private final String baseUrl = "http://arka.foi.hr/WebDiP/2014_projekti/WebDiP2014x054/WS/";
    private final String servise = "allLocks";
    private Session session;

    public AllKeysFragment(){}

    ArrayList<String> keys = new ArrayList<String>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_keys, container, false);
        session = new Session(getContext());
        Toast toast = Toast.makeText(getContext(), session.getUsername(), Toast.LENGTH_SHORT);
        toast.show();

        for(int i=0; i<5; i++ ) {   //dok ne povezem sa servisima
            String k = ("kljuc");
            keys.add(k);
        }

        ListView listview = (ListView) view.findViewById(R.id.listaKljuceva); //gdje prikazujemo sadrzaj(listView u layoutu)
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(      //adapter(aktivnost, layout za svaki red(template od androida), podaci)
                getActivity(), android.R.layout.simple_list_item_1, keys);
        listview.setAdapter(listViewAdapter);                                //postavljanje adaptera, prikaz sadrzaja

        //postavljanje naslova na ActionBar
        ((IzbornikActivity) getActivity()).setActionBarTitle("ALL LOCKS");

        return view;
    }

}

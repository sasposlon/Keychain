package hr.keychain.keychain.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
import hr.keychain.keychain.R;


public class AllKeysFragment extends Fragment {
    public AllKeysFragment(){}

    String[] keys = {"kljuc1", "kljuc2", "kljuc3"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_keys, container, false);


        ListView listview = (ListView) view.findViewById(R.id.listaKljuceva); //gdje prikazujemo sadrzaj(listView u layoutu)
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(      //adapter(aktivnost, layout za svaki red(template od androida), podaci)
                getActivity(), android.R.layout.simple_list_item_1, keys);
        listview.setAdapter(listViewAdapter);                                //postavljanje adaptera, prikaz sadrzaja

        //postavljanje naslova na ActionBar
        ((IzbornikActivity) getActivity()).setActionBarTitle("ALL LOCKS");

        return view;
    }

}

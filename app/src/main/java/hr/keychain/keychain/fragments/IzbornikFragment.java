package hr.keychain.keychain.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import hr.keychain.keychain.IzbornikActivity;
import hr.keychain.keychain.R;
import hr.keychain.keychain.fragments.AllKeysFragment;
import hr.keychain.keychain.helper.Internet;

public class IzbornikFragment extends Fragment {
    public Internet internet = new Internet();


    public IzbornikFragment() {
        // Required empty public constructor
    }

    String[] keys = {"Add New Key", "Lock / Unlock", "All Keys"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_izbornik, container, false);


        ListView listview = (ListView) view.findViewById(R.id.listaIzbornik);          //gdje ce se sadrzaj prikazati
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(               //adapter
                getActivity(), android.R.layout.simple_list_item_1, keys);
        listview.setAdapter(listViewAdapter);                                          //prikaz sadrzaja

        //postavljanje naslova na ActionBar
        ((IzbornikActivity) getActivity()).setActionBarTitle("MENI");

        //clickListener
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {   //clickListener
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        if(internet.internetConnectivity(getContext())) {
                            displayToast("banana");
                            fragment = new AddKeyFragment();
                        }
                        else {
                            displayToast("Please go online to use this feature");
                        }
                        break;
                    case 1:
                        displayToast("Nije realizirana ova funkcionalnost");
                        break;
                    case 2:
                        fragment = new AllKeysFragment();
                        break;
                }
                if(fragment != null){
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .addToBackStack(null)
                            .commit();
                }

            }
        });

        return view;
    }

    private void displayToast(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

}

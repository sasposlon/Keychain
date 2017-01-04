package hr.keychain.keychain.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hr.keychain.keychain.IzbornikActivity;
import hr.keychain.keychain.R;

/**
 * Created by Saša Poslončec on 19.12.16..
 */

public class LockFragment extends Fragment {

    LocationManager locationManager;
    LocationListener locationListener;
    ArrayList<String> keys = new ArrayList<String>();
    ArrayAdapter<String> listViewAdapter = null;
    View view;

    public LockFragment () {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lock, container, false);

        //postavljanje naslova na ActionBar
        ((IzbornikActivity) getActivity()).setActionBarTitle("LOCK / UNLOCK");


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(location != null){
                    double myLatitude = location.getLatitude();
                    double myLongitude = location.getLongitude();
                    Log.e("Latitude: ", "" + myLatitude);
                    Log.e("Longitude: ", "" + myLongitude);
                    Ispis(myLatitude, myLongitude);

                  //  TextView tv = (TextView) view.findViewById(R.id.lock);
                  //  tv.setText("duzina: " + myLatitude + "\nsirina: " + myLongitude);
                  //  TextView tv1 = (TextView) view.findViewById(R.id.distance);
                  //  tv1.setText("Udaljenost: " + Math.round(distance) + " m");
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {}

            @Override
            public void onProviderEnabled(String s) {}

            @Override
            public void onProviderDisabled(String s) {}
        };

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, locationListener);





        return view;
    }

    private void Ispis(double myLatitude, double myLongitude) {

        double distance = Udaljenost(myLatitude, myLongitude); //udaljenost
        for(int i=0; i<5; i++ ) {
            String k = ("kljuc, udaljenost:  " + Math.round(distance) + "m");
            if(distance < 10){keys.add(k);}
        }
        ListView listview = (ListView) view.findViewById(R.id.listaKljuceva);   //gdje prikazujemo sadrzaj(listView u layoutu)
        listViewAdapter = new ArrayAdapter<String>(                             //adapter(aktivnost, layout za svaki red(template od androida), podaci)
                getActivity(), android.R.layout.simple_list_item_1, keys);
        listview.setAdapter(listViewAdapter);
    }

    private double Udaljenost(double latitude, double longitude) {
        Location prvi = new Location("pocetni");
        prvi.setLatitude(latitude);
        prvi.setLongitude(longitude);

        Location drugi = new Location("usporedba");
        drugi.setLatitude(45.802411);
        drugi.setLongitude(16.064697);

        double distance = prvi.distanceTo(drugi);
        return  distance;
    }
}
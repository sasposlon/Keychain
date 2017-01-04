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
import android.widget.TextView;

import hr.keychain.keychain.IzbornikActivity;
import hr.keychain.keychain.R;

/**
 * Created by Saša Poslončec on 19.12.16..
 */

public class LockFragment extends Fragment {

    LocationManager locationManager;
    LocationListener locationListener;

    public LockFragment () {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_lock, container, false);

        //postavljanje naslova na ActionBar
        ((IzbornikActivity) getActivity()).setActionBarTitle("LOCK / UNLOCK");

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(location != null){
                    Log.e("Latitude: ", "" + location.getLatitude());
                    Log.e("Longitude: ", "" + location.getLongitude());

                    TextView tv = (TextView) view.findViewById(R.id.lock);
                    tv.setText("duzina: " + location.getLatitude() + "\nsirina: " + location.getLongitude());
                    double d = Udaljenost(location.getLatitude(), location.getLongitude());
                    TextView tv1 = (TextView) view.findViewById(R.id.distance);
                    tv1.setText("Udaljenost: " + Math.round(d) + " m");

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

    private double Udaljenost(double latitude, double longitude) {
        Location prvi = new Location("pocetni");
        prvi.setLatitude(latitude);
        prvi.setLongitude(longitude);

        Location drugi = new Location("usporedba");
        drugi.setLatitude(67.372102);
        drugi.setLongitude(-18.484196);

        double distance = prvi.distanceTo(drugi);
        return  distance;
    }
}
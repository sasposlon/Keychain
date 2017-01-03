package hr.keychain.keychain.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.keychain.keychain.IzbornikActivity;
import hr.keychain.keychain.R;

/**
 * Created by Saša Poslončec on 19.12.16..
 */

public class LockFragment extends Fragment {

    public LockFragment () {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lock, container, false);

        //postavljanje naslova na ActionBar
        ((IzbornikActivity) getActivity()).setActionBarTitle("LOCK / UNLOCK");
        return view;
    }
}
package hr.keychain.keychain.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.keychain.keychain.IzbornikActivity;
import hr.keychain.keychain.R;


public class AddKeyFragment extends Fragment{

    public AddKeyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_key, container, false);

        ((IzbornikActivity) getActivity()).setActionBarTitle("ADD KEY");
        return view;
    }
}

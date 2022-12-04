package com.kostya.constellationmap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class AboutAppFragment extends Fragment {

    public AboutAppFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // inflater - XML -> Java
        View view = inflater.inflate(R.layout.about_app_fragment, container, false);

        return view;
    }
}

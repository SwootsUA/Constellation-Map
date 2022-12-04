package com.kostya.constellationmap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class LoadFragment extends Fragment implements View.OnClickListener {
    private ListFragment listFragment;
    private AboutAppFragment aboutAppFragment;

    public LoadFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // inflater - XML -> Java
        View view = inflater.inflate(R.layout.load_fragment, container, false);

        Button btn_choose = view.findViewById(R.id.btn_choose_menuF);
        Button btn_about = view.findViewById(R.id.btn_about_menuF);

        btn_choose.setOnClickListener(this);
        btn_about.setOnClickListener(this);

        listFragment = new ListFragment();
        aboutAppFragment = new AboutAppFragment();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_choose_menuF:
                try {
                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.MainFragment, listFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            case R.id.btn_about_menuF:
                try {
                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.MainFragment, aboutAppFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
        }
    }
}

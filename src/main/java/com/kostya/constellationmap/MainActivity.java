package com.kostya.constellationmap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.app.FragmentManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // завантаження фрагменту меню
        LoadFragment loadFragment = new LoadFragment();
        try{
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.MainFragment, loadFragment);
            fragmentTransaction.commit();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void onBackPressed(){
        // повертає фрагмент зі стеку при наскинні кпопки назад
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
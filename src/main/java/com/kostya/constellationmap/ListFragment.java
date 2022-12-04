package com.kostya.constellationmap;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.kostya.constellationmap.db.DB_Helper;

public class ListFragment extends Fragment implements View.OnClickListener {
    private AboutConstellationFragment aboutConstellationFragment;
    private DB_Helper db_helper;

    public ListFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // inflater - XML -> Java
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        aboutConstellationFragment = new AboutConstellationFragment();
        LinearLayout listContainer = view.findViewById(R.id.listContainer);

        db_helper = new DB_Helper(getContext(), "Constellations", 1);
        SQLiteDatabase dataBase = db_helper.getReadableDatabase();
        Cursor cursor = dataBase.rawQuery("Select name, ImageId From Info;", null);

        View listObject;
        while(cursor.moveToNext()){
            listObject = getLayoutInflater().inflate(R.layout.list_object, listContainer, false);
            listObject.setOnClickListener(this);

            TextView text = listObject.findViewById(R.id.textId);
            ImageView img = listObject.findViewById(R.id.imageId);

            text.setText(cursor.getString(0));
            img.setImageResource(cursor.getInt(1));

            listContainer.addView(listObject);
        }

        cursor.close();
        dataBase.close();

        return view;
    }

    @Override
    public void onClick(View view) {
        TextView text = view.findViewById(R.id.textId);
        aboutConstellationFragment.setData(text.getText().toString(), db_helper);
        try {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.MainFragment, aboutConstellationFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

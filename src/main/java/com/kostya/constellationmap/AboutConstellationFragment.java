package com.kostya.constellationmap;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.kostya.constellationmap.db.DB_Helper;

public class AboutConstellationFragment extends Fragment implements View.OnClickListener {
    private String dataName;
    private DB_Helper db_helper;
    private int imgId;

    public AboutConstellationFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // inflater - XML -> Java
        View view = inflater.inflate(R.layout.about_constellation_fragment, container, false);

        TextView uaName = view.findViewById(R.id.ukrainianName);
        TextView latName = view.findViewById(R.id.latinName);
        TextView bStar = view.findViewById(R.id.brightestStar);
        TextView researcher = view.findViewById(R.id.researcher);
        TextView area = view.findViewById(R.id.area);
        TextView description = view.findViewById(R.id.description);
        ImageView constellationImage = view.findViewById(R.id.constellationImage);
        constellationImage.setOnClickListener(this);

        SQLiteDatabase dataBase = db_helper.getReadableDatabase();
        Cursor cursor = dataBase.rawQuery("Select name, Latin_name, Brightest_Star, Researchers.Researcher, Area, ImageId, Description From Info INNER JOIN Researchers ON Researchers._id = Info.id_Described_by  WHERE name LIKE + '" + dataName + "';", null);

        cursor.moveToFirst();
        uaName.setText(cursor.getString(0));
        latName.setText(String.format("(лат. %s)", cursor.getString(1)));
        bStar.setText(cursor.getString(2));
        researcher.setText(cursor.getString(3));
        area.setText(String.format("%d кв. град.", cursor.getInt(4)));
        imgId = cursor.getInt(5);
        constellationImage.setImageResource(imgId);
        description.setText(cursor.getString(6));

        cursor.close();
        dataBase.close();

        return view;
    }

    public void setData(String name, DB_Helper helper) {
        dataName = name;
        db_helper = helper;
    }

    @Override
    public void onClick(View view) {
        AboutConsImage aboutConsImage = new AboutConsImage();
        aboutConsImage.setContent(dataName, imgId);
        try {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.MainFragment, aboutConsImage);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

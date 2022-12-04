package com.kostya.constellationmap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class AboutConsImage extends Fragment {
    private ImageView img;
    private int aboutImgId;
    private String aboutImgName;
    private float mScaleFactor = 1.0f;
    private ScaleGestureDetector mScaleGestureDetector;

    public AboutConsImage() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_c_image, container, false);
        mScaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());

        TextView text = view.findViewById(R.id.aboutImgName);
        img = view.findViewById(R.id.img);

        text.setText(aboutImgName);
        img.setImageResource(aboutImgId);

        img.setOnTouchListener((v, event) -> {
            mScaleGestureDetector.onTouchEvent(event);
            return true;
        });

        return view;
    }

    public void setContent(String name, int imgId){
        aboutImgName = name;
        aboutImgId = imgId;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.8f, Math.min(mScaleFactor, 10.0f));
            img.setScaleX(mScaleFactor);
            img.setScaleY(mScaleFactor);
            return true;
        }
    }
}

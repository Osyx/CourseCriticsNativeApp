package io.osyx.coursecritics.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.osyx.coursecritics.R;

public class Review extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.content_review, container, false);
    }

}

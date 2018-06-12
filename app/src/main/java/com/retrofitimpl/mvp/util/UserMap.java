package com.retrofitimpl.mvp.util;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;


public class UserMap extends SupportMapFragment {

    public static UserMap newInstance() {
        return new UserMap();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root;

        try {
            root = super.onCreateView(inflater, container, savedInstanceState);
        } catch (Exception e) {
            Log.d("Google Map Creationg", "Error Building map, exception : " + e.toString());
            root = super.onCreateView(inflater, container, savedInstanceState);
        }

        return root;
    }

}
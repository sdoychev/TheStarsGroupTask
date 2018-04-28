package com.smd.studio.thestarsgrouptask.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.smd.studio.thestarsgrouptask.util.Constants;

public class ShankillTrainsFragment extends TrainsFragment {

    public ShankillTrainsFragment() {
    }

    public static android.support.v4.app.Fragment newInstance(String stationName) {
        Fragment shankillTrainsFragment = new ShankillTrainsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FRAGMENT_STATION_NAME, stationName);
        shankillTrainsFragment.setArguments(bundle);
        return shankillTrainsFragment;
    }
}
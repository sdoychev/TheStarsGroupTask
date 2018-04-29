package com.smd.studio.thestarsgrouptask.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.smd.studio.thestarsgrouptask.util.Constants;

public class ArklowTrainsFragment extends TrainsFragment {

    public ArklowTrainsFragment() {
    }

    public static android.support.v4.app.Fragment newInstance(String stationName) {
        Fragment arklowTrainsFragment = new ArklowTrainsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FRAGMENT_STATION_NAME, stationName);
        arklowTrainsFragment.setArguments(bundle);
        return arklowTrainsFragment;
    }
}

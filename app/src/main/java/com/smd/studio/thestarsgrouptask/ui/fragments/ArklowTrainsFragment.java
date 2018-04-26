package com.smd.studio.thestarsgrouptask.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import dagger.android.support.AndroidSupportInjection;

public class ArklowTrainsFragment extends Fragment {

    public ArklowTrainsFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        //TODO this.configureViewModel();
    }

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }
}

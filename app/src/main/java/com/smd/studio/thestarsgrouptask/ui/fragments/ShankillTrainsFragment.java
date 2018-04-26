package com.smd.studio.thestarsgrouptask.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import dagger.android.support.AndroidSupportInjection;

public class ShankillTrainsFragment extends android.support.v4.app.Fragment {

    public ShankillTrainsFragment() {
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

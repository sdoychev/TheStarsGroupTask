package com.smd.studio.thestarsgrouptask.di.module;

import com.smd.studio.thestarsgrouptask.ui.fragments.ArklowTrainsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ArklowFragmentModule {
    @ContributesAndroidInjector
    abstract ArklowTrainsFragment contributeArklowTrainsFragment();
}
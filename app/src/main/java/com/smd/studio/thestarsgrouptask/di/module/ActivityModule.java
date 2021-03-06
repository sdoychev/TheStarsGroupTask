package com.smd.studio.thestarsgrouptask.di.module;

import com.smd.studio.thestarsgrouptask.ui.activities.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = {ArklowTrainsFragmentModule.class, ShankillTrainsFragmentModule.class})
    abstract MainActivity contributeMainActivity();
}
package com.smd.studio.thestarsgrouptask.di.module;

import com.smd.studio.thestarsgrouptask.ui.fragments.ShankillTrainsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ShankillTrainsFragmentModule {
    @ContributesAndroidInjector
    abstract ShankillTrainsFragment contributShankillTrainsFragment();
}

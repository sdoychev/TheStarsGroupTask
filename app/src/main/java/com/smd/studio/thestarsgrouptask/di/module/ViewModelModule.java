package com.smd.studio.thestarsgrouptask.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.smd.studio.thestarsgrouptask.di.key.ViewModelKey;
import com.smd.studio.thestarsgrouptask.viewmodels.FactoryViewModel;
import com.smd.studio.thestarsgrouptask.viewmodels.TrainListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TrainListViewModel.class)
    abstract ViewModel bindTrainListViewModel(TrainListViewModel trainListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
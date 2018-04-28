package com.smd.studio.thestarsgrouptask.di.component;

import android.app.Application;

import com.smd.studio.thestarsgrouptask.App;
import com.smd.studio.thestarsgrouptask.di.module.ActivityModule;
import com.smd.studio.thestarsgrouptask.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {ActivityModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}
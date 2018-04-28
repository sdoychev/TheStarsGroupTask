package com.smd.studio.thestarsgrouptask.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smd.studio.thestarsgrouptask.database.TrainDatabase;
import com.smd.studio.thestarsgrouptask.database.dao.TrainDao;
import com.smd.studio.thestarsgrouptask.network.TrainWebService;
import com.smd.studio.thestarsgrouptask.repositories.TrainRepository;
import com.smd.studio.thestarsgrouptask.util.Constants;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    // Database Injection
    @Provides
    @Singleton
    TrainDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application, TrainDatabase.class, "Train.db").build();
    }

    @Provides
    @Singleton
    TrainDao provideUserDao(TrainDatabase database) {
        return database.trainDao();
    }

    // Repository Injection
    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    TrainRepository provideTrainRepository(TrainWebService webservice, TrainDao userDao, Executor executor) {
        return new TrainRepository(webservice, userDao, executor);
    }

    // Network Injection
    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    TrainWebService provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(TrainWebService.class);
    }
}
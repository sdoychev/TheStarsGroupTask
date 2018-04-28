package com.smd.studio.thestarsgrouptask.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.smd.studio.thestarsgrouptask.database.converter.DateConverter;
import com.smd.studio.thestarsgrouptask.database.dao.TrainDao;
import com.smd.studio.thestarsgrouptask.database.entity.TrainEntity;

@Database(entities = {TrainEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class TrainDatabase extends RoomDatabase {

    private static volatile TrainDatabase INSTANCE;

    public static TrainDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TrainDatabase.class, "Train.db").build();
        }
        return INSTANCE;
    }

    // Dao
    public abstract TrainDao trainDao();
}

package com.smd.studio.thestarsgrouptask.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.smd.studio.thestarsgrouptask.database.dao.TrainDao;
import com.smd.studio.thestarsgrouptask.database.entity.TrainEntity;

@Database(entities = {TrainEntity.class}, version = 1, exportSchema = false)
public abstract class TrainDatabase extends RoomDatabase {

    private static volatile TrainDatabase INSTANCE;

    // Dao
    public abstract TrainDao trainDao();
}

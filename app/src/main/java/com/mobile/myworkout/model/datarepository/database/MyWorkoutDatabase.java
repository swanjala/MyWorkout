package com.mobile.myworkout.model.datarepository.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.mobile.myworkout.model.datamodel.UserModel;

@Database(entities = {UserModel.class}, version = 12)
public abstract class MyWorkoutDatabase extends RoomDatabase {

    public static MyWorkoutDatabase DATABASEINSTANCE;
    public static Context dbContext;

    public static MyWorkoutDatabase getDatabase(final Context context) {
        dbContext = context;
        if (DATABASEINSTANCE == null) {
            synchronized (MyWorkoutDatabase.class) {
                DATABASEINSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MyWorkoutDatabase.class, "myworkout_database")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }

        return DATABASEINSTANCE;
    }

    public abstract UserDataAccessObject userDataAccessObject();

}

package com.alfinapp.data.db.database;

import android.content.Context;

import androidx.annotation.WorkerThread;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.alfinapp.data.db.dao.LogDAO;
import com.alfinapp.data.db.entity.LogClass;

/**
 * Created by Lalit Goswami on 03/12/2019.
 */

@Database(entities = {LogClass.class}, version = 1, exportSchema = false)
public abstract class AlfinDatabase extends RoomDatabase {

    private static AlfinDatabase sInstance;

    @WorkerThread
    public abstract LogDAO logDao();

    private static AlfinDatabase initialize(Context context) {
        sInstance = Room.databaseBuilder(context.getApplicationContext(), AlfinDatabase.class, "log-database").fallbackToDestructiveMigration().build();
        return sInstance;
    }

    public static AlfinDatabase getInstance(Context context) {
        if (sInstance == null) {
            return initialize(context);
        } else {
            return sInstance;
        }
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    public static void addLog(final AlfinDatabase db, final LogClass log) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                db.logDao().insertAll(log);

            }
        };
        thread.start();
    }


    public static void dropTable(AlfinDatabase db) {
        db.logDao().dropTable();
    }
}

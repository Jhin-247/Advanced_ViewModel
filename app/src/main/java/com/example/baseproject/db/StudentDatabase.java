package com.example.baseproject.db;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.baseproject.db.dao.StudentDao;
import com.example.baseproject.db.model.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class StudentDatabase extends RoomDatabase {
    private static StudentDatabase sInstance;
    public abstract StudentDao studentDao();

    public static synchronized StudentDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            StudentDatabase.class, "Student_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }


}

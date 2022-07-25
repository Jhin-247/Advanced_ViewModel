package com.example.baseproject.db.repository;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.baseproject.db.StudentDatabase;
import com.example.baseproject.db.dao.StudentDao;
import com.example.baseproject.db.model.Student;

import java.util.List;

public class StudentRepository {
    private StudentDao studentDao;
    private LiveData<List<Student>> mStudents;

    public StudentRepository(Application application) {
        StudentDatabase studentDatabase = StudentDatabase.getInstance(application);
        studentDao = studentDatabase.studentDao();
        mStudents = studentDao.getAllStudents();
    }

    public void insert(Student student){
        HandlerThread handlerThread = new HandlerThread("insert");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(() -> {
            long res = studentDao.insert(student);
            Log.i("123123123", "insert: " + res);
        });
    }

    public void delete(Student student){
        HandlerThread handlerThread = new HandlerThread("delete");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(() -> studentDao.delete(student));
    }

    public void update(Student student){
        HandlerThread handlerThread = new HandlerThread("update");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(() -> studentDao.update(student));
    }

    public void deleteAll(){
        HandlerThread handlerThread = new HandlerThread("deleteAll");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(() -> studentDao.deleteAll());
    }

    public LiveData<List<Student>> getAllStudent(){
        return mStudents;
    }
}

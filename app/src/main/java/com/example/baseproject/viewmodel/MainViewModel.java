package com.example.baseproject.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.baseproject.db.dao.DBCallBack;
import com.example.baseproject.db.model.Student;
import com.example.baseproject.db.repository.StudentRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<Student>> mStudentList;
    private StudentRepository mRepo;
    private Student student;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepo = new StudentRepository(application);
        mStudentList = mRepo.getAllStudent();
    }

    public Student getChosenStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LiveData<List<Student>> getStudentList() {
        return mStudentList;
    }


    public void addStudent(Student student, DBCallBack callBack) {
        if (!TextUtils.isEmpty(student.getName()) && !TextUtils.isEmpty(student.getAge())) {
            mRepo.insert(student);
            callBack.onInsertSuccess();
        } else {
            callBack.onInsertFail();
        }
    }
}

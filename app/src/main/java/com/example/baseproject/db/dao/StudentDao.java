package com.example.baseproject.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.baseproject.db.model.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    long insert(Student student);

    @Update
    void update(Student student);

    @Query("SELECT * FROM student")
    LiveData<List<Student>> getAllStudents();

    @Delete
    void delete(Student student);

    @Query("DELETE FROM student")
    void deleteAll();

}

package com.example.baseproject.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.baseproject.databinding.FragmentAddStudentBinding;
import com.example.baseproject.db.model.Student;
import com.example.baseproject.viewmodel.MainViewModel;

public class AddStudentFragment extends Fragment {
    private FragmentAddStudentBinding mBinding;
    private Student student;
    private MainViewModel mainViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentAddStudentBinding.inflate(inflater, container, false);
        student = new Student();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mBinding.setStudent(student);
        mBinding.executePendingBindings();

        mBinding.btnSave.setOnClickListener(v -> {
            mainViewModel.addStudent(student);
            Navigation.findNavController(mBinding.getRoot()).popBackStack();
        });

        mBinding.btnCancel.setOnClickListener(v -> {
            Navigation.findNavController(mBinding.getRoot()).popBackStack();
        });

        return mBinding.getRoot();
    }
}
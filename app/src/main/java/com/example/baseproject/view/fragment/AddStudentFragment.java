package com.example.baseproject.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentAddStudentBinding;
import com.example.baseproject.db.dao.DBCallBack;
import com.example.baseproject.db.model.Student;
import com.example.baseproject.viewmodel.MainViewModel;

public class AddStudentFragment extends Fragment implements DBCallBack {
    private FragmentAddStudentBinding mBinding;
    private Student student;
    private MainViewModel mainViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentAddStudentBinding.inflate(inflater, container, false);
        student = new Student();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.setStudent(student);
        mBinding.setStudentViewModel(mainViewModel);
        mBinding.executePendingBindings();

        mBinding.btnSave.setOnClickListener(v -> {
            mainViewModel.addStudent(student, this);
        });

        mBinding.btnCancel.setOnClickListener(v -> {
            Navigation.findNavController(mBinding.getRoot()).popBackStack();
        });

        return mBinding.getRoot();
    }

    @Override
    public void onInsertFail() {
        Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInsertSuccess() {
        Navigation.findNavController(mBinding.getRoot()).popBackStack();
    }
}
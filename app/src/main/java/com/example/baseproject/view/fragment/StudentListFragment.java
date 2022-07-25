package com.example.baseproject.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.baseproject.R;
import com.example.baseproject.adapters.StudentAdapter;
import com.example.baseproject.databinding.FragmentStudentListBinding;
import com.example.baseproject.db.model.Student;
import com.example.baseproject.viewmodel.MainViewModel;

public class StudentListFragment extends Fragment implements StudentAdapter.StudentClick {
    private FragmentStudentListBinding mBinding;
    private StudentAdapter mAdapter;
    private MainViewModel mModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentStudentListBinding.inflate(inflater, container, false);
        mAdapter = new StudentAdapter(this);
        mModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        mBinding.rcvStudents.setLayoutManager(new LinearLayoutManager(requireContext()));
        mBinding.rcvStudents.setAdapter(mAdapter);
        mModel.getStudentList().observe(getViewLifecycleOwner(), students -> mAdapter.setData(students));

        mBinding.btnAdd.setOnClickListener(v -> {
            Navigation.findNavController(mBinding.getRoot()).navigate(R.id.action_studentListFragment_to_addStudentFragment);
        });

        return mBinding.getRoot();
    }

    @Override
    public void onStudentClick(Student student) {
        mModel.setStudent(student);
        Navigation.findNavController(mBinding.getRoot()).navigate(R.id.action_studentListFragment_to_studentDetailFragment);
    }
}
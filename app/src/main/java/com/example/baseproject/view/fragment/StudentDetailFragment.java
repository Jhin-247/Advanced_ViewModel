package com.example.baseproject.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.baseproject.databinding.FragmentStudentDetailBinding;
import com.example.baseproject.viewmodel.MainViewModel;

public class StudentDetailFragment extends Fragment {
    private FragmentStudentDetailBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentStudentDetailBinding.inflate(inflater, container, false);
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        mBinding.setStudent(mainViewModel.getChosenStudent());
        mBinding.executePendingBindings();


        return mBinding.getRoot();
    }
}
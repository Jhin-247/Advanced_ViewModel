package com.example.baseproject.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.databinding.ItemStudentBinding;
import com.example.baseproject.db.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {
    private List<Student> students;
    private StudentClick mClick;

    public StudentAdapter(StudentClick mClick) {
        this.mClick = mClick;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Student> data) {
        this.students = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentHolder(ItemStudentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student student = students.get(position);
        holder.bind(student);
        holder.mBinding.getRoot().setOnClickListener(v -> mClick.onStudentClick(student));
    }

    @Override
    public int getItemCount() {
        return students == null ? 0 : students.size();
    }

    public interface StudentClick {
        void onStudentClick(Student student);
    }

    public static class StudentHolder extends RecyclerView.ViewHolder {
        ItemStudentBinding mBinding;

        public StudentHolder(@NonNull ItemStudentBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bind(Student student) {
            mBinding.setStudent(student);
            mBinding.executePendingBindings();
        }
    }
}

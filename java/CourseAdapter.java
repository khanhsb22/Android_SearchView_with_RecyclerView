package com.example.demoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private ArrayList<CourseModel> arrayList;
    private Context context;

    public CourseAdapter(ArrayList<CourseModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    // Method for filtering our recyclerview items.
    public void filterList(ArrayList<CourseModel> filterList) {
        arrayList = filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseModel model = arrayList.get(position);
        holder.textView_courseName.setText(model.getCourseName());
        holder.textView_courseDescription.setText(model.getCourseDescription());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_courseName, textView_courseDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_courseName = itemView.findViewById(R.id.textView_courseName);
            textView_courseDescription = itemView.findViewById(R.id.textView_courseDescription);
        }
    }
}

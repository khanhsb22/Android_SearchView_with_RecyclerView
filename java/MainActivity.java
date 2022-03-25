package com.example.demoapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CourseAdapter courseAdapter;
    private ArrayList<CourseModel> courseModels;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        buildRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        ArrayList<CourseModel> filterList = new ArrayList<>();
        for (CourseModel item : courseModels) {
            if (item.getCourseName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
        }
        if (filterList.isEmpty()) {
            Toast.makeText(this, "No data found !", Toast.LENGTH_SHORT).show();
        } else {
            courseAdapter.filterList(filterList);
        }
    }

    private void buildRecyclerView() {
        courseModels = new ArrayList<>();
        courseModels.add(new CourseModel("DSA", "DSA Self Paced Course"));
        courseModels.add(new CourseModel("JAVA", "JAVA Self Paced Course"));
        courseModels.add(new CourseModel("C++", "C++ Self Paced Course"));
        courseModels.add(new CourseModel("Python", "Python Self Paced Course"));
        courseModels.add(new CourseModel("Fork CPP", "Fork CPP Self Paced Course"));
        courseAdapter = new CourseAdapter(courseModels, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(courseAdapter);
    }
}
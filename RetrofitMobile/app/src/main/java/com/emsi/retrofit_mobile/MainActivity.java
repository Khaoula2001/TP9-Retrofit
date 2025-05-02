package com.emsi.retrofit_mobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emsi.retrofit_mobile.adapter.UserAdapter;
import com.emsi.retrofit_mobile.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {
    private UserAdapter adapter;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        SearchView searchView = findViewById(R.id.searchView);

        adapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        viewModel.getUsers().observe(this, users -> {
            if (users != null) {
                adapter.setUserList(users);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filterByIdOrName(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filterByIdOrName(newText);
                return false;
            }
        });
    }
}
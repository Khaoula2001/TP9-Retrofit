package com.emsi.retrofit_mobile.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.emsi.retrofit_mobile.model.User;
import com.emsi.retrofit_mobile.repository.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;
    private UserRepository repository;

    public UserViewModel() {
        repository = new UserRepository();
        users = new MutableLiveData<>();
        loadUsers();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    private void loadUsers() {
        repository.fetchUsers(users);
    }
}

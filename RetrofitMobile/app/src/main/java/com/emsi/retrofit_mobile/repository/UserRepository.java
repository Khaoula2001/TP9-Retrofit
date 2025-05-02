package com.emsi.retrofit_mobile.repository;

import androidx.lifecycle.MutableLiveData;

import com.emsi.retrofit_mobile.model.User;
import com.emsi.retrofit_mobile.network.RetrofitClient;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class UserRepository {
    public void fetchUsers(final MutableLiveData<List<User>> userListLiveData) {
        RetrofitClient.getApiService().getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    userListLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                userListLiveData.postValue(null);
            }
        });
    }
}
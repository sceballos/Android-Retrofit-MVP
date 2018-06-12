package com.retrofitimpl.mvp.interfaces;

import com.retrofitimpl.mvp.data.user.UsersList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetUsersDataService {
    @GET("/api/?results=150&nat=us,au,br,ca,de,dk,es,fi,fr,gb,ie")
    Call<UsersList> getUsersData();
}
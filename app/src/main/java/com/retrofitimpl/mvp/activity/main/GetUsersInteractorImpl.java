package com.retrofitimpl.mvp.activity.main;

import android.util.Log;

import com.retrofitimpl.mvp.data.user.UsersList;
import com.retrofitimpl.mvp.interfaces.GetUsersDataService;
import com.retrofitimpl.mvp.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetUsersInteractorImpl implements MainContract.GetUsersInteractor {

    @Override
    public void getUsersArrayList(final OnFinishedListener onFinishedListener) {
        /** Create handle for the RetrofitInstance interface*/
        GetUsersDataService service = RetrofitInstance.getRetrofitInstance().create(GetUsersDataService.class);

        /** Call the method with parameter in the interface to get the users data*/
        Call<UsersList> call = service.getUsersData();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<UsersList>() {
            @Override
            public void onResponse(Call<UsersList> call, Response<UsersList> response) {
                onFinishedListener.onFinished(response.body().getUsersArrayList());
            }

            @Override
            public void onFailure(Call<UsersList> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}

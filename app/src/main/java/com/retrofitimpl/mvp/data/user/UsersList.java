package com.retrofitimpl.mvp.data.user;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UsersList {

    @SerializedName("results")
    private ArrayList<RandomUser> usersList;

    public ArrayList<RandomUser> getUsersArrayList() {
        return usersList;
    }

    public void setUsersArrayList(ArrayList<RandomUser> usersArrayList) {
        this.usersList = usersArrayList;
    }
}

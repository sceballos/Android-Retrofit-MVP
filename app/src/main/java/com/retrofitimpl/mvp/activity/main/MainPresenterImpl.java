package com.retrofitimpl.mvp.activity.main;

import com.retrofitimpl.mvp.data.user.RandomUser;

import java.util.ArrayList;

public class MainPresenterImpl implements MainContract.presenter, MainContract.GetUsersInteractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetUsersInteractor getUsersInteractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetUsersInteractor getUsersInteractor) {
        this.mainView = mainView;
        this.getUsersInteractor = getUsersInteractor;
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onRefreshButtonClick() {

        if(mainView != null){
            mainView.showProgress();
        }
        getUsersInteractor.getUsersArrayList(this);

    }

    @Override
    public void requestDataFromServer() {
        getUsersInteractor.getUsersArrayList(this);
    }


    @Override
    public void onFinished(ArrayList<RandomUser> userArrayList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(userArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
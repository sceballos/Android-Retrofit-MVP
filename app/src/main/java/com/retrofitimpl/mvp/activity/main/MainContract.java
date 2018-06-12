package com.retrofitimpl.mvp.activity.main;

import com.retrofitimpl.mvp.data.user.RandomUser;

import java.util.ArrayList;

public interface MainContract {

    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();
    }


    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<RandomUser> userArrayList);

        void onResponseFailure(Throwable throwable);

    }

    interface GetUsersInteractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<RandomUser> noticeArrayList);
            void onFailure(Throwable t);
        }

        void getUsersArrayList(OnFinishedListener onFinishedListener);
    }

}

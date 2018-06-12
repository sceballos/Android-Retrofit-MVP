package com.retrofitimpl.mvp.activity.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.retrofitimpl.mvp.R;
import com.retrofitimpl.mvp.adapter.UsersAdapter;
import com.retrofitimpl.mvp.data.user.RandomUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MainContract.presenter presenter;
    private int LIST_COLUMNS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerViewAndProgressBar();
        presenter = new MainPresenterImpl(this, new GetUsersInteractorImpl());
        presenter.requestDataFromServer();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    /**
     *
     *
     * */

    private void initRecyclerViewAndProgressBar() {
        progressBar = findViewById(R.id.loading_users_pb);
        recyclerView = findViewById(R.id.users_rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, LIST_COLUMNS);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<RandomUser> userArrayList) {
        UsersAdapter adapter = new UsersAdapter(MainActivity.this, userArrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        //todo : change this to lower bar
        Toast.makeText(MainActivity.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }
}

package com.wipro.assignment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wipro.assignment.adapter.RowAdapter;
import com.wipro.assignment.response.Response;

public class MainActivity extends AppCompatActivity implements RowContract.View {

    TextView tvTitle, tvNoRecords;
    RecyclerView rvRows;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefresh;

    MainPresenter mainPresenter;
    RowAdapter rowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        rvRows = findViewById(R.id.rvRows);
        progressBar = findViewById(R.id.progressBar);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        tvNoRecords = findViewById(R.id.tvNoRecords);

        mainPresenter = new MainPresenter(this);
        mainPresenter.requestDataFromServer();

        rvRows.setLayoutManager(new LinearLayoutManager(this));

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                mainPresenter.onRefresh();
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(Response response) {
        swipeRefresh.setRefreshing(false);

        if (response != null) {
            tvTitle.setText(response.getTitle());

            if (response.getRows().size() > 0) {
                rvRows.setVisibility(View.VISIBLE);
                tvNoRecords.setVisibility(View.GONE);

                rowAdapter = new RowAdapter();
                rowAdapter.setRows(response.getRows());
                rvRows.setAdapter(rowAdapter);
            } else {
                rvRows.setVisibility(View.GONE);
                tvNoRecords.setVisibility(View.VISIBLE);
            }
        } else {
            rvRows.setVisibility(View.GONE);
            tvNoRecords.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }
}

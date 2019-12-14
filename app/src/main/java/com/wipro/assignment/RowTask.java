package com.wipro.assignment;

import com.wipro.assignment.network.ApiClient;
import com.wipro.assignment.network.ApiInterface;
import com.wipro.assignment.network.NetworkUtils;
import com.wipro.assignment.response.Response;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;

public class RowTask implements RowContract.Model{

    @Override
    public void getData(final OnFinishedListener listener) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Response> call = apiService.getResponse();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                listener.onFinished(response.body());
            }

            public void onFailure(Call<Response> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}

package com.wipro.assignment;

import com.wipro.assignment.response.Response;

public class MainPresenter implements RowContract.Presenter, RowContract.Model.OnFinishedListener {

    RowContract.View view;
    RowContract.Model model;

    public MainPresenter(RowContract.View view) {
        this.view = view;
        model = new RowTask();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onRefresh() {
        model.getData(this);
    }

    @Override
    public void requestDataFromServer() {
        if (view != null)
            view.showProgress();

        model.getData(this);
    }

    @Override
    public void onFinished(Response response) {
        view.setDataToRecyclerView(response);

        if (view != null)
            view.hideProgress();
    }

    @Override
    public void onFailure(Throwable t) {
        view.onResponseFailure(t);

        if (view != null)
            view.hideProgress();
    }
}

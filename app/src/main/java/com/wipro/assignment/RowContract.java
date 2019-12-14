package com.wipro.assignment;

import com.wipro.assignment.response.Response;

public interface RowContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(Response response);

            void onFailure(Throwable t);
        }

        void getData(OnFinishedListener onFinishedListener);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(Response response);

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();

        void onRefresh();

        void requestDataFromServer();

    }
}

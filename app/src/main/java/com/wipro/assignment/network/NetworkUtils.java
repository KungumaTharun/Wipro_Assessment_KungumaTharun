package com.wipro.assignment.network;

public class NetworkUtils {

    private static ApiInterface apiInterface;

    public static ApiInterface getApiInstance() {
        if (apiInterface == null)
            apiInterface = ApiClient.getClient().create(ApiInterface.class);

        return apiInterface;
    }
}

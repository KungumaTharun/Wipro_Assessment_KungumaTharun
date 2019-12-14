package com.wipro.assignment.network;

import com.wipro.assignment.response.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<Response> getResponse();
}

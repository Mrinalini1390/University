package com.virtusa.university.rest;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RestFactory {

    public static RestInterface build() {
        OkHttpClient client = new OkHttpClient();

        // Original timing values
        client.setConnectTimeout(15, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(30, TimeUnit.SECONDS);    // socket timeout

        client.interceptors().add(new LoggingInterceptor());

        //Retrofit builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.128.30.248:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(RestInterface.class);
    }

}

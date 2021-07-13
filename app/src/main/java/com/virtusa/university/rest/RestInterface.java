package com.virtusa.university.rest;

import com.virtusa.university.model.UniversityJson;

import retrofit.Call;
import retrofit.http.GET;

public interface RestInterface {



    ///////////////////////////////////////////////////////////////////////////
// Get All Universities

    @GET("/data/universities")
    Call<UniversityJson> getUniversities();

}

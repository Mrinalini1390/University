package com.virtusa.university.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UniversityJson implements Serializable {

    @SerializedName("status")
    public String status;

    @SerializedName("msg")
    public String msg;

    @SerializedName("res")
    public List<UniversityResJson> res;

    public String errorMessage;

    public int errorCode;

    public boolean isError;


}

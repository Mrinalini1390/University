package com.virtusa.university.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UniversityResJson implements Serializable {

    @SerializedName("domains")
    public List<String> domains;

    @SerializedName("web_pages")
    public List<String> webPages;

    @SerializedName("name")
    public String name;

    @SerializedName("alpha_two_code")
    public String alphaTwoCode;

    @SerializedName("state-province")
    public String stateProvince;

    @SerializedName("image_url")
    public String imageUrl;

    @SerializedName("country")
    public String country;


    public String getDomain(){
        return "Domain : "+domains.get(0);
    }

    public String getCountryValue(){
        return "Country : "+country;
    }

    public String getCode(){
        return "Alpha Code : "+alphaTwoCode;
    }



}

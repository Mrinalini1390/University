package com.virtusa.university.datahandler;

import androidx.annotation.NonNull;

import com.virtusa.university.App;
import com.virtusa.university.model.UniversityJson;
import com.virtusa.university.rest.RestInterface;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class UniversityDataHandler {

    private UniversityListener mListener;

    public interface UniversityListener{
        void onSuccess(UniversityJson response);
        void onError(int errorcode, String message);
    }

     /**
     * Constructor for data handler.
     *
     * @param listener callback listener
     */
    public UniversityDataHandler(@NonNull UniversityListener listener) {
        this.mListener = listener;
    }

    public void getAllUniversities(){
        RestInterface rest = App.app.rest;
        Call<UniversityJson> call = rest.getUniversities();

        call.enqueue(new Callback<UniversityJson>() {
            @Override
            public void onResponse(Response<UniversityJson> response, Retrofit retrofit) {
                mListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                mListener.onError(t.hashCode(),t.getMessage());
            }
        });
    }


}

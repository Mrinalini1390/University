package com.virtusa.university.manager;

import androidx.annotation.NonNull;

import com.virtusa.university.model.UniversityJson;

public interface UniversityManager {

    /**
     * Callback for All Universities.
     */
    interface UniversityListener {
        void onReceiveUniversity(UniversityJson response);

        void onError(int errorcode,String message);
    }

    /**
     * Get the University  Response.
     *
     * @param callback response callback
     */
    void getUniversity( @NonNull UniversityListener callback);


}

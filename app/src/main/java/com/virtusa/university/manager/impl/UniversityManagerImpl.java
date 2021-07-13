package com.virtusa.university.manager.impl;

import androidx.annotation.NonNull;

import com.virtusa.university.datahandler.UniversityDataHandler;
import com.virtusa.university.manager.UniversityManager;
import com.virtusa.university.model.UniversityJson;

import org.jetbrains.annotations.NotNull;

public class UniversityManagerImpl implements UniversityManager {
    @Override
    public void getUniversity(@NonNull @NotNull UniversityListener callback) {

        UniversityDataHandler universityDataHandler = new UniversityDataHandler(new UniversityDataHandler.UniversityListener() {
            @Override
            public void onSuccess(UniversityJson response) {
              callback.onReceiveUniversity(response);
            }

            @Override
            public void onError(int errorcode, String message) {
                callback.onError(errorcode, message);
            }
        });


        universityDataHandler.getAllUniversities();


    }
}

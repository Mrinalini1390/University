package com.virtusa.university.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.virtusa.university.manager.UniversityManager;
import com.virtusa.university.manager.impl.UniversityManagerImpl;
import com.virtusa.university.model.UniversityJson;

import org.jetbrains.annotations.NotNull;

public class UniversityViewModel extends AndroidViewModel {
    private MutableLiveData<UniversityJson> mData = new MutableLiveData<>();

    public UniversityViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public LiveData<UniversityJson> getAllUniversities(){
        UniversityManager manager = new UniversityManagerImpl();
        manager.getUniversity(new UniversityManager.UniversityListener() {
            @Override
            public void onReceiveUniversity(UniversityJson response) {
                mData.postValue(response);
            }

            @Override
            public void onError(int errorcode, String message) {
                UniversityJson json = new UniversityJson();
                json.errorCode = errorcode;
                json.errorMessage = message;
                json.isError = true;
                mData.postValue(json);
            }
        });
        return mData;
    }

}


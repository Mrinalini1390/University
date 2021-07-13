package com.virtusa.university.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.virtusa.university.helpers.FragmentScreenNavigation;

import org.jetbrains.annotations.NotNull;

public class FragmentTransitionViewModel extends AndroidViewModel {

    private MutableLiveData<FragmentScreenNavigation> mData = new MutableLiveData();

    public void setScreenNav(FragmentScreenNavigation data){
        mData.postValue(data);
    }

    public LiveData<FragmentScreenNavigation> getScreenNav(){
        return mData;
    }

    public FragmentTransitionViewModel(@NonNull @NotNull Application application) {
        super(application);
    }
}

package com.virtusa.university.view.university.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.virtusa.university.R;
import com.virtusa.university.databinding.UniversityDetailsFragmentBinding;
import com.virtusa.university.model.UniversityResJson;
import com.virtusa.university.view.university.activity.UniversityActivity;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

public class UniversityDetailFragment extends Fragment {

    public static final String TAG = UniversityDetailFragment.class.getSimpleName();

    private UniversityActivity mActivity;
    private UniversityDetailsFragmentBinding mBinding;
    private UniversityResJson resJson;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        Log.d(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.university_details_fragment,container,false);
        mBinding = DataBindingUtil.bind(view);
        try {
            setUpValue();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return view;

    }

    /*To populate the values to the view*/
    private void setUpValue() throws MalformedURLException {
        mActivity = (UniversityActivity) getActivity();
        resJson = (UniversityResJson) getArguments().getSerializable(mActivity.UNIVERSITY_MANAGER);
        mBinding.setRes(resJson);
        setUpThumpNail(mBinding.ivIcon,new URL(resJson.imageUrl));
    }

    /*To download the image and display in the view*/
    private void setUpThumpNail(ImageView icon, URL url) {

        RequestOptions options = new RequestOptions()
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(this)
                .load(url)
                .apply(options)
                .into(icon);
    }
}

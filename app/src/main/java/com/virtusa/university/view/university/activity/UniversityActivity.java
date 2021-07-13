package com.virtusa.university.view.university.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.virtusa.university.R;
import com.virtusa.university.databinding.ActivityFragmentBaseBinding;
import com.virtusa.university.helpers.FragmentScreenNavigation;
import com.virtusa.university.model.UniversityResJson;
import com.virtusa.university.view.BaseActivity;
import com.virtusa.university.view.university.fragment.UniversityDetailFragment;
import com.virtusa.university.view.university.fragment.UniversityListFragment;

public class UniversityActivity extends BaseActivity {

    private ActivityFragmentBaseBinding mBinding;
    public static final String UNIVERSITY_MANAGER = "university_manager";
    private UniversityResJson resJson;
    private Fragment navFragment;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_fragment_base,null,false);
        mBinding = DataBindingUtil.bind(rootView);
        setContentView(rootView);
        initializeFragmentTrans();
        setScreenData(FragmentScreenNavigation.UNIVERSITIES_LIST, new UniversityResJson());
    }

    //To transfer data and to desired fragment
    public void setScreenData(FragmentScreenNavigation nav, UniversityResJson res){
        resJson = res;
        mFragViewModel.setScreenNav(nav);
    }

    //For the fragment navigation
    @Override
    public void onShowNextFragment(FragmentScreenNavigation screenNavigation) {
        super.onShowNextFragment(screenNavigation);

            String tag = "";
        switch (screenNavigation){
            case UNIVERSITIES_LIST:
                navFragment = new UniversityListFragment();
                tag = UniversityListFragment.TAG;
                break;

            case UNIVERSITIES_DETAILS:
                navFragment = new UniversityDetailFragment();
                tag = UniversityDetailFragment.TAG;
                break;

        }

        Bundle args = new Bundle();
        args.putSerializable(UNIVERSITY_MANAGER, resJson);

        showFragment(navFragment,args,R.id.fl_base,true, R.anim.fade_in,
                R.anim.slide_from_center_to_left, R.anim.slide_from_left_to_center,
                R.anim.slide_from_center_to_right, tag);

    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        // count is one its the last fragment no other fragment exsist
        if (count == 1) {
            super.onBackPressed();
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }

}

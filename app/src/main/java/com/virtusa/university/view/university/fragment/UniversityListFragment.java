package com.virtusa.university.view.university.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.virtusa.university.R;
import com.virtusa.university.databinding.UniversityListFragmentBinding;
import com.virtusa.university.helpers.FragmentScreenNavigation;
import com.virtusa.university.model.UniversityJson;
import com.virtusa.university.view.university.activity.UniversityActivity;
import com.virtusa.university.view.university.adapter.UniversityListAdapter;
import com.virtusa.university.viewmodel.UniversityViewModel;

import org.jetbrains.annotations.NotNull;

public class UniversityListFragment extends Fragment {

    public static final String TAG = UniversityListFragment.class.getSimpleName();

    private UniversityActivity mActivity;

    private UniversityListFragmentBinding mBinding;
    private UniversityViewModel mViewModel;
    private UniversityListAdapter adapter;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        Log.d(TAG,"onCreateView");

        View view = inflater.inflate(R.layout.university_list_fragment,container,false);
        mBinding = DataBindingUtil.bind(view);
        setUpValue();

        return view;

    }

    /*To initialize and get the values from backend*/
    private void setUpValue() {

        mActivity = (UniversityActivity) getActivity();
        mViewModel = new ViewModelProvider.AndroidViewModelFactory(mActivity.getApplication()).create(UniversityViewModel.class);
        mBinding.prgressBar.show();

        final Observer<UniversityJson> universityJsonObserver = universityResponse->{
            if(universityResponse!= null && !universityResponse.isError){

                Log.d(TAG,universityResponse.toString());
                setUpAdapter(universityResponse);

            }
        };
        mViewModel.getAllUniversities().observe(mActivity,universityJsonObserver);

    }

   /* To convert the Data to the views*/
    private void setUpAdapter(UniversityJson universityResponse) {

        adapter = new UniversityListAdapter(getContext(),universityResponse);
        adapter.setOnItemClickListener(new UniversityListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                mActivity.setScreenData(FragmentScreenNavigation.UNIVERSITIES_DETAILS,universityResponse.res.get(position));
                Log.d("",String.valueOf(position));

            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.d("",String.valueOf(position));

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        mBinding.recyclerView.setNestedScrollingEnabled(false);
        mBinding.recyclerView.setLayoutManager(layoutManager);
        mBinding.recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerAppliancesVer = new DividerItemDecoration(
                mBinding.recyclerView.getContext(),
                DividerItemDecoration.VERTICAL
        );
        mBinding.recyclerView.addItemDecoration(dividerAppliancesVer);
        mBinding.prgressBar.hide();

        mBinding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(mActivity, "Reached end of the List !!!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}

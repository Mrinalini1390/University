package com.virtusa.university.view;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.virtusa.university.App;
import com.virtusa.university.helpers.FragmentScreenNavigation;
import com.virtusa.university.viewmodel.FragmentTransitionViewModel;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    public FragmentTransitionViewModel mFragViewModel;

    public static App app;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (App) getApplication();

    }

    /*This has to be override in the activity for fragment navigation*/
    public void onShowNextFragment(FragmentScreenNavigation screenNavigation){}

    /*This we need to call from activity which need screen navigation for fragments*/
    public void initializeFragmentTrans(){
        mFragViewModel = new ViewModelProvider(this).get(FragmentTransitionViewModel.class);
        final Observer<FragmentScreenNavigation> fragmentScreenNavigationObserver = fragScreenResponse ->{
            if(fragScreenResponse != null){
                onShowNextFragment(fragScreenResponse);
            }
        };
        mFragViewModel.getScreenNav().observe(this,fragmentScreenNavigationObserver);
    }


    /**
     * Displays requested fragment.
     *
     * @param fragment to be displayed
     * @param args     any arguments applicable to the fragment
     * @return the fragment identifier for the new fragment - useful for
     * backstack navigation
     */
    public int showFragment(Fragment fragment, Bundle args, int layoutId, boolean addToBackStack,
                            int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim,
                            String fragmentTag) {
        Log.d(TAG, "showFragment - " + fragment.getClass().getSimpleName());

        return showChildFragment(getSupportFragmentManager(), fragment, args, layoutId,
                addToBackStack, enterAnim, exitAnim, popEnterAnim, popExitAnim, fragmentTag);
    }

    /**
     * Displays requested fragment.
     *
     * @param fragment to be displayed
     * @param args     any arguments applicable to the fragment
     * @return the fragment identifier for the new fragment - useful for
     * backstack navigation
     */
    public int showChildFragment(FragmentManager fragmentManager, Fragment fragment, Bundle args,
                                 int layoutId, boolean addToBackStack, int enterAnim, int exitAnim,
                                 int popEnterAnim,
                                 int popExitAnim, String fragmentTag) {
        Log.d(TAG, "showChildFragment - " + fragment.getClass().getSimpleName());

        if (args != null)
            fragment.setArguments(args);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (enterAnim != 0) {
            transaction.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim);
        }

        // replace whatever is in the fragment_container view with this
        // fragment
        if (fragmentTag == null)
            transaction.replace(layoutId, fragment);
        else
            transaction.replace(layoutId, fragment, fragmentTag);

        // add the transaction to the back stack so the user can navigate
        // back
        if (addToBackStack)
            transaction.addToBackStack(fragmentTag);

        // Commit the transaction
        if (!isFinishing()) {
            return transaction.commitAllowingStateLoss();
        } else {
            return -1;
        }
    }



}

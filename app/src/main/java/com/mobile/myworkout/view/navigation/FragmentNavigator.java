package com.mobile.myworkout.view.navigation;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mobile.myworkout.R;


public class FragmentNavigator extends Fragment {


    public boolean fragmentLoader(@NonNull Fragment mFragment,
                               FragmentManager fragmentManager) {

        try {
            assert getFragmentManager() != null;
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, mFragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;

        }
    }
}

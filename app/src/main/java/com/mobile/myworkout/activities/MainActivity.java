package com.mobile.myworkout.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mobile.myworkout.R;
import com.mobile.myworkout.view.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState != null) {

            Toast.makeText(this,"SavedInstanceState",
                    Toast.LENGTH_LONG).show();
           /*TODO
            * Implement login/signup logic, contegent how I implement view model*/
        } else {

            HomeFragment homeFragment = new HomeFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.main_container, homeFragment)
                    .commitAllowingStateLoss();
        }
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}

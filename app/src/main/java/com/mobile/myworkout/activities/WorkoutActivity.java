package com.mobile.myworkout.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.mobile.myworkout.R;
import com.mobile.myworkout.view.fragments.HomeFragment;
import com.mobile.myworkout.view.fragments.ProfileFragment;

public class WorkoutActivity extends AppCompatActivity {

    private ProfileFragment profileFragment = new ProfileFragment();

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment homeFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*Given that the workout activity is the first that loads
         * checking for an auth token would help us determine whether
         * the previous user logged out of the application */
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        String authToken = preferences
                .getString(this.getString(R.string.auth_token_key_string), "");

        /*Naviagate to the right fragment if the user is not authenticated
         * */

        assert authToken != null;
        if (authToken.equals("")) {

            fragmentManager.beginTransaction()
                    .add(R.id.main_container, homeFragment)
                    .commitAllowingStateLoss();

        } else {

            fragmentManager.beginTransaction()
                    .add(R.id.main_container, profileFragment)
                    .commitAllowingStateLoss();
        }


    }
}

package com.mobile.myworkout.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mobile.myworkout.R;
import com.mobile.myworkout.view.fragments.HomeFragment;
import com.mobile.myworkout.view.fragments.ProfileFragment;
import com.mobile.myworkout.view.navigation.FragmentNavigator;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*Check if the user is already logged in by chekcking for a
         * valid auth token which is a non-null non empty value*/

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);

        String authToken = preferences.getString("auth_token", "");

        /*Invoke the fragment navigator when the conditions are met, and
         * toasts a message when the fragment is not successfuly loaded */

        FragmentNavigator fragmentNavigator = new FragmentNavigator();
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        if (authToken != null && !authToken.equals("")) {


            if (!fragmentNavigator.fragmentLoader(new ProfileFragment(),
                    fragmentManager, null)) {
                Toast.makeText(this,
                        "Unable to load the home page",
                        Toast.LENGTH_LONG).show();

            }
        }

        /*Navigates home for the beginning of the authentication process if the
         * user does not have a valid token*/

        if (!fragmentNavigator.fragmentLoader(new HomeFragment(),
                fragmentManager, null)) {
            Toast.makeText(this,
                    "Unable to load the home page",
                    Toast.LENGTH_LONG).show();

        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}

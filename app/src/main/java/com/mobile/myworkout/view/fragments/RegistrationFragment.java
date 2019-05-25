package com.mobile.myworkout.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mobile.myworkout.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationFragment extends Fragment {

    @BindView(R.id.img_cancel_registration)
    ImageView backPressImage;


    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             ViewGroup viewGroup,
                             Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_registration,
                viewGroup, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        final FragmentManager fragmentManager = getFragmentManager();

        /* Button press pops the stacked fragments. The current
        * fragment which is at the top of the stack is removed
        * and the previous cached fragment (which is at the top of
        * the stack) is diaplayed
        * */
        backPressImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStack();
            }
        });

    }
}

package com.mobile.myworkout.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.myworkout.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileAdapter extends
        RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private ArrayList<HashMap<String, String>> mProfileData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ProfileAdapter(Context context, ArrayList<HashMap<String, String>> profileData) {

        this.mProfileData = profileData;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;


    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                int viewType) {
        View view = layoutInflater.inflate(R.layout.card_profile_details_layout, viewGroup,
                false);

        return new ProfileViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {

        HashMap<String, String> currentData = mProfileData.get(position);

        holder.setData(currentData, position);
    }

    @Override
    public int getItemCount() {
        return mProfileData.size();
    }


    class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView tv_key, tv_value;

        public ProfileViewHolder(View profileView) {
            super(profileView);
            tv_key = profileView.findViewById(R.id.tv_key);
            tv_value = profileView.findViewById(R.id.tv_value);

        }

        public void setData(HashMap<String, String> currentData, final int position) {


            if (position == 0) {

                this.tv_key.setText(context.getString(R.string.tv_key_set_text_email));
                this.tv_value.setText(currentData.get(context.getString(R.string
                        .key_email_map_data)));

            } else if (position == 1) {
                this.tv_key.setText(context.getString(R.string.tv_gender_label));
                this.tv_value.setText(currentData.get(context
                        .getString(R.string.tv_gender_map_data)));
            }


        }

    }
}

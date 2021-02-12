package com.mmfinfotech.NavneetTiwaritest.browse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.facebook.login.widget.LoginButton;
import com.mmfinfotech.NavneetTiwaritest.MainActivity;
import com.mmfinfotech.NavneetTiwaritest.R;
import com.mmfinfotech.NavneetTiwaritest.TabActivity;
import com.mmfinfotech.NavneetTiwaritest.interfacess.Consts;
import com.mmfinfotech.NavneetTiwaritest.preferences.SharedPrefrence;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements  View.OnClickListener{


    TabActivity homeActivity;

    private static final String TAG = ProfileFragment.class.getSimpleName();
    LinearLayoutManager layoutManager;
    View view;
    CircleImageView profilePic;
    TextView tvName,tvEmail,tvMobile;
    LoginButton loginButton;
    SharedPrefrence prefrence;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_profile, container, false);


        loginButton = view.findViewById(R.id.login_button);
        prefrence=SharedPrefrence.getInstance(getActivity());
        profilePic=view.findViewById(R.id.civProfilePic);
        tvName=view.findViewById(R.id.tvName);
        tvEmail=view.findViewById(R.id.tvEmail);
        tvMobile=view.findViewById(R.id.tvMobile);
        setData();
        return view;
    }

        private void setData() {

            Glide.with(getActivity())
                    .load(prefrence.getValue(Consts.IMAGE_URL_FB))
                    .error(R.drawable.browse)
                    .into(profilePic);
            tvEmail.setText(Consts.EMAIL+"  "+prefrence.getValue(Consts.EMAIL));
            tvName.setText(Consts.FULL_NAME+"  "+prefrence.getValue(Consts.FULL_NAME));
            tvMobile.setText(Consts.MOBILE+"  "+prefrence.getValue(Consts.MOBILE));


        loginButton.setOnClickListener(this);

        }




        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            homeActivity = (TabActivity) activity;
        }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_button:
                prefrence.clearAllPreferences();
                Intent in = new Intent(getActivity(), MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
                getActivity().finish();
                break;
        }
    }





}

package com.mmfinfotech.NavneetTiwaritest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmfinfotech.NavneetTiwaritest.browse.BrowseFragment;
import com.mmfinfotech.NavneetTiwaritest.browse.HomeFragment;
import com.mmfinfotech.NavneetTiwaritest.browse.ProfileFragment;

public class TabActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = TabActivity.class.getSimpleName();
    public FragmentManager fragmentManager;
    public HomeFragment homeFragment = new HomeFragment();
    public BrowseFragment browseFragment = new BrowseFragment();
    public ProfileFragment profileFragment = new ProfileFragment();
    Context mContext;
    RelativeLayout mRelativeLayout;
    FrameLayout frameLayout;
    TextView tvHome,  navBrowse, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_tab);
        mContext = TabActivity.this;

        mRelativeLayout = (RelativeLayout) findViewById(R.id.relLayoutParent);
        frameLayout = (FrameLayout) findViewById(R.id.dashboard_frame);
        tvHome = (TextView) findViewById(R.id.tvHome);
        navBrowse = (TextView) findViewById(R.id.nav_Browse);
        navProfile = (TextView) findViewById(R.id.nav_Profile);


        setUiAction();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.dashboard_frame, homeFragment).commit();
        setSelected(true, false, false, false, false);
    }


    /*changing icons as clicks */
    public void setSelected(boolean firstBTN, boolean secondBTN, boolean thirdBTN, boolean fourthBTN, boolean fifthBTN) {
        if (firstBTN) {

            tvHome.setCompoundDrawablesWithIntrinsicBounds(null, ResourcesCompat.getDrawable(getResources(), R.drawable.home_icon, null), null, null);
            navBrowse.setCompoundDrawablesWithIntrinsicBounds(null, ResourcesCompat.getDrawable(getResources(), R.drawable.browse_icon, null), null, null);
            navProfile.setCompoundDrawablesWithIntrinsicBounds(null, ResourcesCompat.getDrawable(getResources(), R.drawable.profile_icon, null), null, null);
        }
        if (secondBTN) {

            tvHome.setCompoundDrawablesWithIntrinsicBounds(null, ResourcesCompat.getDrawable(getResources(), R.drawable.home, null), null, null);
            navBrowse.setCompoundDrawablesWithIntrinsicBounds(null, ResourcesCompat.getDrawable(getResources(), R.drawable.browse, null), null, null);
            navProfile.setCompoundDrawablesWithIntrinsicBounds(null, ResourcesCompat.getDrawable(getResources(), R.drawable.profile_icon, null), null, null);
        }
        if (thirdBTN) {

            tvHome.setCompoundDrawablesWithIntrinsicBounds(null, ResourcesCompat.getDrawable(getResources(), R.drawable.home, null), null, null);
            navBrowse.setCompoundDrawablesWithIntrinsicBounds(null, ResourcesCompat.getDrawable(getResources(), R.drawable.browse_icon, null), null, null);
          navProfile.setCompoundDrawablesWithIntrinsicBounds(null, ResourcesCompat.getDrawable(getResources(), R.drawable.profile, null), null, null);
        }

        tvHome.setSelected(firstBTN);
        navBrowse.setSelected(secondBTN);
       navProfile.setSelected(thirdBTN);


    }

    /*setting clicks on the bottom */
    public void setUiAction() {
        tvHome.setOnClickListener(this);
        navBrowse.setOnClickListener(this);
        navProfile.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvHome:
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.dashboard_frame, homeFragment).commit();
                setSelected(true, false, false, false, false);
                break;
            case R.id.nav_Browse:
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.dashboard_frame, browseFragment).commit();
                setSelected(false, true, false, false, false);
                break;

            case R.id.nav_Profile:
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.dashboard_frame, profileFragment).commit();
                setSelected(false, false, false, false, true);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (browseFragment != null && browseFragment.isVisible()) {


            setSelected(true, false, false, false, false);
            fragmentManager.beginTransaction().replace(R.id.dashboard_frame, homeFragment).commit();
        }  else if (profileFragment != null && profileFragment.isVisible()) {
            setSelected(true, false, false, false, false);
            fragmentManager.beginTransaction().replace(R.id.dashboard_frame, homeFragment).commit();
        } else {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

            finish();
        }

    }
}
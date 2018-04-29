package com.smd.studio.thestarsgrouptask.ui.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.smd.studio.thestarsgrouptask.R;
import com.smd.studio.thestarsgrouptask.ui.fragments.ArklowTrainsFragment;
import com.smd.studio.thestarsgrouptask.ui.fragments.ShankillTrainsFragment;
import com.smd.studio.thestarsgrouptask.util.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AndroidInjection.inject(this);

        //Show first fragment at startup
        setupInitialFragment();

        //Bottom Navigation
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setupInitialFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, ArklowTrainsFragment.newInstance(Constants.ARKLOW_STATION_NAME));
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_arklow:
                selectedFragment = ArklowTrainsFragment.newInstance(Constants.ARKLOW_STATION_NAME);
                break;
            case R.id.navigation_shankill:
                selectedFragment = ShankillTrainsFragment.newInstance(Constants.SHANKILL_STATION_NAME);
                break;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, selectedFragment);
        transaction.commit();
        return true;
    };
}

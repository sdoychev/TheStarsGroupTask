package com.smd.studio.thestarsgrouptask.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.smd.studio.thestarsgrouptask.R;
import com.smd.studio.thestarsgrouptask.ui.fragments.ArklowTrainsFragment;
import com.smd.studio.thestarsgrouptask.ui.fragments.ShankillTrainsFragment;
import com.smd.studio.thestarsgrouptask.util.Constants;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Fragment currentFragment;
    private Bundle bundle = new Bundle();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_arklow:
                    mTextMessage.setText(R.string.title_arklow);
                    currentFragment = new ArklowTrainsFragment();
                    loadTrainFragment(currentFragment, Constants.ARKLOW_STATION_NAME);
                    return true;
                case R.id.navigation_shankill:
                    mTextMessage.setText(R.string.title_shankill);
                    currentFragment = new ShankillTrainsFragment();
                    loadTrainFragment(currentFragment, Constants.SHANKILL_STATION_NAME);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        configureDagger();

        currentFragment = new ArklowTrainsFragment();
        loadTrainFragment(currentFragment, Constants.ARKLOW_STATION_NAME);
    }

    private void loadTrainFragment(Fragment currentFragment, String fragmentIdentifier) {
        bundle.putString(Constants.UID_KEY, fragmentIdentifier);
        currentFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, currentFragment);
        fragmentTransaction.commit();
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }
}

package com.smd.studio.thestarsgrouptask.ui.activities;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.smd.studio.thestarsgrouptask.R;
import com.smd.studio.thestarsgrouptask.adapters.RecyclerViewAdapter;
import com.smd.studio.thestarsgrouptask.util.Constants;
import com.smd.studio.thestarsgrouptask.viewmodels.TrainListViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.message)
    TextView textMessage;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String currentStationCode;
    private RecyclerViewAdapter recyclerViewAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_arklow:
                    textMessage.setText(R.string.title_arklow);
                    currentStationCode = Constants.ARKLOW_STATION_NAME;
                    return true;
                case R.id.navigation_shankill:
                    textMessage.setText(R.string.title_shankill);
                    currentStationCode = Constants.SHANKILL_STATION_NAME;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AndroidInjection.inject(this);

        //Bottom Navigation
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Recycler View
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        //View Model
        TrainListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrainListViewModel.class);
        viewModel.init(currentStationCode);
        viewModel.getTrains().observe(this, trainsList -> recyclerViewAdapter.addItems(trainsList));
    }
}

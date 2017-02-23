package com.mvvmsample.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.mvvmsample.R;
import com.mvvmsample.databinding.ActivityMainBinding;
import com.mvvmsample.model.MainModelBean;
import com.mvvmsample.viewmodel.MainViewModel;

import static android.R.color.white;

public class MainActivity extends AppCompatActivity {
    //对应就是本Activity
    private ActivityMainBinding mainBinding;
    private MainViewModel viewModel;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new MainViewModel(this);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                MainModelBean modelBean = (MainModelBean) viewModel.showData();
                mainBinding.setModelBean(modelBean);
            }
        },2000);

    }
}

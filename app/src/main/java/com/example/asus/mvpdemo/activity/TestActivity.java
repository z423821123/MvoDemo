package com.example.asus.mvpdemo.activity;
import android.os.Bundle;

import com.example.asus.mvpdemo.R;
import com.example.asus.mvpdemo.base.BaseActivity;
import com.example.asus.mvpdemo.presenter.TestPresenter;
import com.example.asus.mvpdemo.view.ITestView;



/**
 * Created by ZWX
 * Description:
 * on 2019/7/17.
 */
public class TestActivity extends BaseActivity<ITestView, TestPresenter> implements ITestView {

    TestPresenter testPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected TestPresenter createPresenter() {
        testPresenter = new TestPresenter(this);
        return testPresenter;
    }

    @Override
    public void onLoginSuccess(String msg) {

    }

    @Override
    public void onLoginError(String msg) {

    }
}
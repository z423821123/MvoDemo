package com.example.asus.mvpdemo.presenter;

import com.example.asus.mvpdemo.listener.ITestListener;
import com.example.asus.mvpdemo.model.ITestModel;
import com.example.asus.mvpdemo.model.TestModelImpl;
import com.example.asus.mvpdemo.view.ITestView;
import com.example.asus.mvpdemo.base.BasePresenter;

/**
* Created by ZMvp on 2019/07/17
*/

public class TestPresenter extends BasePresenter<ITestView> {
    ITestView iTestView;
    ITestModel iTestModel;

    public TestPresenter(ITestView iTestView) {
        this.iTestView = iTestView;
        iTestModel = new TestModelImpl();
    }

    public void Login(String userName, String password){
        iTestModel.getData(userName, password, new ITestListener.ILoginListener() {
            @Override
            public void onLoginSuccess(String msg) {
                if(isViewAttached()){
                    getView().onLoginSuccess(msg);
                }
            }

            @Override
            public void onLoginError(String msg) {
                if(isViewAttached()){
                    getView().onLoginError(msg);
                }
            }
        });
    }
}
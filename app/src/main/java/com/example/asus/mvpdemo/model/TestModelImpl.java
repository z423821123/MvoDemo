package com.example.asus.mvpdemo.model;

import com.example.asus.mvpdemo.listener.ITestListener;

/**
* Created by ZMvp on 2019/07/17
*/

public class TestModelImpl implements ITestModel {

    public static final int SUCCESS = 1;
    public static final int Error = 2;

    @Override
    public void getData(String userName, String password, ITestListener.ILoginListener iLoginListener) {
        //网络操作
        int type = SUCCESS;
        switch (type){
            case 1:
                iLoginListener.onLoginSuccess("登录成功");
                break;
            case 2:
                iLoginListener.onLoginError("登录成功");
                break;
                default:
                    break;
        }
    }
}
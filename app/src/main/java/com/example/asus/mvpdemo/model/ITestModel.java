package com.example.asus.mvpdemo.model;

import com.example.asus.mvpdemo.listener.ITestListener;

/**
* Created by ZMvp on 2019/07/17
*/

public interface ITestModel{
    void getData(String userName, String password, ITestListener.ILoginListener iLoginListener);
}
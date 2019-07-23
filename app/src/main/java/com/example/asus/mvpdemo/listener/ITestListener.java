package com.example.asus.mvpdemo.listener;

/**
* Created by ZMvp on 2019/07/17
*/

public class ITestListener{
    public interface ILoginListener{
        void onLoginSuccess(String msg);
        void onLoginError(String msg);
    }
}
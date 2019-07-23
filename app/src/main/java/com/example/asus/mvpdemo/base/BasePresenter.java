package com.example.asus.mvpdemo.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 创建日期：2018/5/10 on 17:40
 * 描述:
 * 作者:
 */
public abstract class BasePresenter<T> {
    protected Reference<T> mViewRef;//view接口类型的弱引用

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);//建立关联
    }

    protected T getView() {
        if (isViewAttached()) {
            return mViewRef.get();
        } else {
            return null;
        }
    }

    /**
     * 判断是否与MvpView处于连接状态
     *
     * @return
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }


    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}

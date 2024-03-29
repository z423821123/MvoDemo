package com.example.asus.mvpdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 创建日期：2018/5/8 on 14:40
 * 描述:
 * 作者:
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    protected View rootView;
    private boolean isInitView = false;
    private boolean isVisible = false;

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(setContentView(), container, false);

        initView(rootView);
        isInitView = true;
        isCanLoadData();
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见，获取该标志记录下来
        if(isVisibleToUser){
            isVisible = true;
            isCanLoadData();
        }else{
            isVisible = false;
        }
    }

    private void isCanLoadData(){
        //所以条件是view初始化完成并且对用户可见
        if(isInitView && isVisible){
            lazyLoad();
            //防止重复加载数据
            isInitView = false;
            isVisible = false;
        }
    }

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    /**
     * 加载页面布局文件
     * @return
     */
    protected abstract int setContentView();

    /**
     * 让布局中的view与fragment中的变量建立起映射
     */
    protected abstract void initView(View rootView);

    /**
     * 加载要显示的数据
     */
    protected abstract void lazyLoad();


}

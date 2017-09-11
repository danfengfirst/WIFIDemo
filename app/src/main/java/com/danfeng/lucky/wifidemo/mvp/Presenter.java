package com.danfeng.lucky.wifidemo.mvp;

/**
 * Created by danfeng.wang on 2017/2/7.
 */

public class Presenter<T extends IView> {
    private T mView;

    public void onCreate(T view){mView=view;}
    public void onDestroy(){
        mView=null;

    }
    protected T getView(){
        if (hasView()){
            return mView;
        }
        return null;
    }
    protected boolean hasView(){
        return mView!=null;
    }
}

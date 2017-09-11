package com.danfeng.lucky.wifidemo.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by danfeng.wang on 2017/1/22.
 */

public class BusinessProcessorHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e("","这是业务代理类");
        return null;
    }
}

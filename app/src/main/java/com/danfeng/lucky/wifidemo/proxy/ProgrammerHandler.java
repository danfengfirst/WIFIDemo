package com.danfeng.lucky.wifidemo.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by danfeng.wang on 2017/2/6.
 */

public class ProgrammerHandler implements InvocationHandler {
    AndroidProgrammer programmer;

    public ProgrammerHandler(AndroidProgrammer programmer) {
        this.programmer = programmer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        programmer.program();
        Log.e("你猜这是什么","答：动态代理");
        return null;
    }
}

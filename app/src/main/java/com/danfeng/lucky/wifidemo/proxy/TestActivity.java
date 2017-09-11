package com.danfeng.lucky.wifidemo.proxy;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.danfeng.lucky.wifidemo.R;

import java.lang.reflect.Proxy;

/**
 * Created by danfeng.wang on 2017/1/22.
 */

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
//        BusinessProcessorImpl bpimpl=new BusinessProcessorImpl();
//        BusinessProcessorHandler bphandler=new BusinessProcessorHandler();
//        BusinessProcessor bp= (BusinessProcessor) java.lang.reflect.Proxy.newProxyInstance(bpimpl.getClass().getClassLoader(),bpimpl.getClass().getInterfaces(),bphandler);
//        bp.processBusiness();
        AndroidProgrammer programmer=new AndroidProgrammer();
        ProgrammerHandler invocation=new ProgrammerHandler(programmer);
        IProgrammer ip= (IProgrammer) Proxy.newProxyInstance(AndroidProgrammer.class.getClassLoader(),new Class[]{IProgrammer.class},invocation);
        //也可以这么写
       // IProgrammer ip= (IProgrammer) Proxy.newProxyInstance(AndroidProgrammer.class.getClassLoader(),programmer.getClass().getInterfaces(),invocation);
        ip.program();
        ProxyProgrammer pp=new ProxyProgrammer(programmer);
        pp.program();
    }
}

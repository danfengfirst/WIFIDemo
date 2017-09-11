package com.danfeng.lucky.wifidemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mWifiRecyclerView;
    private RecyclerAdapter<ScanResult> mAdapter;
    private IntentFilter mIntentFilter1;
    private IntentFilter mIntentFilter2;

    private WifiManager mWifimaManager;//wifi管理者
    WifiBroadCastReceiver mWifiReceiver;
    private List<ScanResult> mScanResult=new ArrayList<>();
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            removeRepeatNameWifi();
            Collections.sort(mScanResult,WifiUtil.comparator);
            mAdapter.setData(mScanResult);
            mAdapter.notifyDataSetChanged();


        }
    };

    /**
     * 去除重复且信号弱的wifi
     */
    private void removeRepeatNameWifi() {
        for (int i=0;i<mScanResult.size();i++){

            for (int j=mScanResult.size()-1;j>i;j--){
                if (mScanResult.get(i).SSID.equals(mScanResult.get(j).SSID)){
                    if (mScanResult.get(i).level>mScanResult.get(j).level){
                        mScanResult.remove(j);
                    }else {
                        mScanResult.remove(i);
                    }
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWifiRecyclerView= (RecyclerView) findViewById(R.id.wifirecycler);
        mWifiRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter=new RecyclerAdapter<ScanResult>(R.layout.wifishow_item) {
            @Override
            public void onBind(CommHolder holder, int position, int viewType, ScanResult data) {
                TextView textView= (TextView) holder.itemView.findViewById(R.id.wifiname);
                ImageView imageView= (ImageView) holder.itemView.findViewById(R.id.wifilevel);
                textView.setText(data.SSID);

                /**
                 * wifi信息强度
                 */
                if (data.level > -55) {
                    imageView.setBackgroundResource(R.mipmap.wifi_leavel1);
                } else if (data.level > -70 && data.level <= -50) {
                    imageView.setBackgroundResource(R.mipmap.wifi_leavel2);
                } else if (data.level > -85 && data.level <= -70) {
                    imageView.setBackgroundResource(R.mipmap.wifi_leavel3);
                } else {
                    imageView.setBackgroundResource(R.mipmap.wifi_leavel4);
                }

            }
        };
        mWifimaManager= (WifiManager) getSystemService(WIFI_SERVICE);
        mWifiRecyclerView.setAdapter(mAdapter);
        mWifiReceiver=new WifiBroadCastReceiver();
        mIntentFilter1=new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        mIntentFilter2=new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        mWifimaManager.startScan();
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(mWifiReceiver,mIntentFilter1);
        registerReceiver(mWifiReceiver,mIntentFilter2);

    }

    class WifiBroadCastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null&&intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)){
                mScanResult= WifiUtil.getWifiScanResult(BaseApplication.mContext);
                mHandler.sendEmptyMessage(01);
            }
            if (intent.getAction() != null&&intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){

            }
        }
    }


}

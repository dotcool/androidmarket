package com.example.netease.market;

import de.greenrobot.event.EventBus;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AppReceiver extends BroadcastReceiver{
      
    @Override  
    public void onReceive(Context context, Intent intent){
        //接收安装广播 
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {   
            String packageName = intent.getDataString();   
            packageName = packageName.substring(packageName.indexOf(":")+1) ;
            EventBus.getDefault().post(new AppEvent.OnPkgAddedEvent(packageName)) ;
            System.out.println("安装了:" +packageName + "包名的程序");     
        }   
        //接收卸载广播  
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {   
            String packageName = intent.getDataString();   
            packageName = packageName.substring(packageName.indexOf(":")+1) ;
            EventBus.getDefault().post(new AppEvent.OnPkgRemovedEvent(packageName)) ;
            System.out.println("卸载了:"  + packageName + "包名的程序");
 
        }
    }
}  
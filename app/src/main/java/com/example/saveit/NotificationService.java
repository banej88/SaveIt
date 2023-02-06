package com.example.saveit;

import android.content.Context ;
import android.os.Bundle;
import android.service.notification.NotificationListenerService ;
import android.service.notification.StatusBarNotification ;
import android.util.Log ;

public class NotificationService extends NotificationListenerService {

    public static String bundle2string(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = "";

        for (String key : bundle.keySet()) {
            if(key.contains("android.title")){
                string += "Korisnik -> "+bundle.get(key)+": ";
            }
            else if(key.contains("android.text")){
                string += bundle.get(key)+"\n";
            }

        }
        return string;
    }

    private String TAG = this .getClass().getSimpleName() ;
    Context context ;
    static MyListener myListener ;
    @Override
    public void onCreate () {
        super .onCreate() ;
        context = getApplicationContext() ;
    }
    @Override
    public void onNotificationPosted (StatusBarNotification sbn) {
        String t = bundle2string(sbn.getNotification().extras);
        if(t.contains("null")){

        }else {
            myListener.setValue("\n" + t);
        }
    }
    @Override
    public void onNotificationRemoved (StatusBarNotification sbn) {
        String t = bundle2string(sbn.getNotification().extras);
        if(t.contains("null")){

        }else {
            myListener .setValue( "Remove: " + sbn.getPackageName()) ;
        }
    }
    public void setListener (MyListener myListener) {
        NotificationService. myListener = myListener ;
    }
}
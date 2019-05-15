package com.example.myalarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class AlarmReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        int notifId =   intent.getIntExtra("notification", 0);
        String message = intent.getStringExtra ("todo");
        Intent mainIntent = new Intent (context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);
        NotificationManager myNotificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon (android.R.drawable.ic_dialog_info).setContentTitle ("It's Time").setContentText (message).setWhen (System.currentTimeMillis()).setAutoCancel(true).setContentIntent(contentIntent);
        myNotificationManager.notify(notifId, builder.build());
    }
}

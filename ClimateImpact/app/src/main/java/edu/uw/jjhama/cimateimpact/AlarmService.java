package edu.uw.jjhama.cimateimpact;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by iguest on 5/3/16.
 */
public class AlarmService extends IntentService {

    public static final String CREATE = "CREATE";
    public static final String CANCEL = "CANCEL";

    private static final int MY_NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;
    private Notification myNotification;
    private final String myBlog = "abc";

    private IntentFilter matcher;

    public AlarmService() {
        super("");
        matcher = new IntentFilter();
        matcher.addAction(CREATE);
        matcher.addAction(CANCEL);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        String notificationId = intent.getStringExtra("notificationId");

//        // TODO Auto-generated method stub
//        notificationManager = (NotificationManager)   getSystemService(Context.NOTIFICATION_SERVICE);
//        myNotification = new Notification(R.drawable.ic_launcher,
//                "Notification!", System.currentTimeMillis());
//        Context context = getApplicationContext();
//        String notificationTitle = "Exercise of Notification!";
//        String notificationText = "hello";
//        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri
//                .parse(myBlog));
//        PendingIntent pendingIntent = PendingIntent.getActivity(
//                MainActivity.this, 0, myIntent,
//                Intent.FLAG_ACTIVITY_NEW_TASK);
//        myNotification.defaults |= Notification.DEFAULT_SOUND;
//        myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
//        myNotification.setLatestEventInfo(context, notificationTitle,
//                notificationText, pendingIntent);
//        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
        setAlarm();
    }

    private void setAlarm(){
        Context context = getApplicationContext();

        AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Landing.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        Calendar myCal = Calendar.getInstance();
        long curTime = myCal.getTimeInMillis();
        myCal.setTimeInMillis(curTime + 50000);
        mgr.set(AlarmManager.RTC_WAKEUP, myCal.getTimeInMillis(), pi);
        Log.i("1", "alarm set for " + myCal.getTime().toLocaleString());
        Toast.makeText(getApplicationContext(),"Alarm set for " + myCal.getTime().toLocaleString(), Toast.LENGTH_LONG).show();

    }
}
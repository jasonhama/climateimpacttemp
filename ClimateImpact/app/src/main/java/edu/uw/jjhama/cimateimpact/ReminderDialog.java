package edu.uw.jjhama.cimateimpact;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by iguest on 4/29/16.
 */
public class ReminderDialog extends DialogFragment{

    public static String BROADCAST_ACTION =
            "edu.uw.jjhama.cimateimpact.broadcasttest.SHOWTOAST";

    Context context = getActivity();
    String action;
    String email;
    NotificationCompat.Builder mBuilder;
    private static final String TAG = "ReminderDialog";

    public ReminderDialog(){
        //required empty
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        action = bundle.getString("action");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //get and set email variable from previous fragment
        Bundle getBundle = getArguments();
        if(getBundle != null){
            if(getBundle.containsKey("email")){
                email = getBundle.getString("email");
            } else {
                email = "test";
            }
        } else {
            email = "test";
        }


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.reminder_dialog, null))
                // Add action buttons
                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog f = (Dialog) dialog;
                        int hoursStart = Integer.parseInt((String) ((EditText) f.findViewById(R.id.hoursStart)).getText().toString());
                        int minutesStart = Integer.parseInt((String) ((EditText) f.findViewById(R.id.minutesStart)).getText().toString());
                        int hoursEnd = Integer.parseInt(((String) ((EditText) f.findViewById(R.id.hoursEnd)).getText().toString()));
                        int minutesEnd = Integer.parseInt(((String) ((EditText) f.findViewById(R.id.minutesEnd)).getText().toString()));

                        final String startTime = ((String) ((EditText) f.findViewById(R.id.hoursStart)).getText().toString()) + ":" + ((String) ((EditText) f.findViewById(R.id.minutesStart)).getText().toString());
                        final String endTime = ((String) ((EditText) f.findViewById(R.id.hoursEnd)).getText().toString()) + ":" + ((String) ((EditText) f.findViewById(R.id.minutesEnd)).getText().toString());


                        if(hoursStart < 24 && minutesEnd < 60 && minutesStart < 60 && hoursEnd < 24) {
                            Log.v(TAG, "Event Created");
                            // sign in the user ...
                            //make a personalized notification

                            EditText firstName = (EditText) f.findViewById(R.id.firstName);
                            RadioGroup radioGroup = (RadioGroup) f.findViewById(R.id.radioGroup);
                            Log.v(TAG, radioGroup.getCheckedRadioButtonId() + "");

                            EditText timeStartHours = (EditText) f.findViewById(R.id.hoursStart);

                            timeStartHours.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                @Override
                                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                                        return true;
                                    }
                                    return false;
                                }
                            });

                            Log.v(TAG, startTime);
                            if (!startTime.equals(":")) {
                                //Log.v(TAG, "Submit was selected \n username: "+ );
                                Context context = getActivity();

                                NotificationCompat.Builder mBuilder =
                                        new NotificationCompat.Builder(getActivity())
                                                .setSmallIcon(R.drawable.ic_menu_send)
                                                .setContentTitle("Reminder Set!")
                                                .setAutoCancel(true)
                                                .setContentText("Reminder set to " + action + " at " + startTime + ".");
                                Intent resultIntent = new Intent(context, Landing.class);
                                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                                stackBuilder.addParentStack(Landing.class);
                                stackBuilder.addNextIntent(resultIntent);
                                PendingIntent resultPendingIntent =
                                        stackBuilder.getPendingIntent(
                                                0,
                                                PendingIntent.FLAG_UPDATE_CURRENT
                                        );
                                mBuilder.setContentIntent(resultPendingIntent);
                                NotificationManager mNotificationManager =
                                        (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);


                                mNotificationManager.notify(1, mBuilder.build());

                                //setAlarm();

                                startAlert();

                                //add task to database
                                UUID uuid = UUID.randomUUID();
                                final DatabaseReference mDatabase;
                                mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(email).child("tasks").child("" + uuid);
                                mDatabase.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {
                                        mDatabase.child("action").setValue(action);
                                        mDatabase.child("startTime").setValue(startTime);
                                        mDatabase.child("endTime").setValue(endTime);
                                        mDatabase.child("frequency").setValue("Every Other Day");
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                                        Toast.makeText(getActivity(), "Failed to load post.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Toast.makeText(getActivity(), "Time for alarm not set", Toast.LENGTH_LONG).show();
                                ReminderDialog.this.getDialog().cancel();
                            }
                        } else {
                            Log.v(TAG, "issue with time selected");
                            Toast.makeText(getActivity(), "Please set valid time options", Toast.LENGTH_LONG).show();
                            ReminderDialog.this.getDialog().cancel();
                        }

                    }


                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.v(TAG, "Cancel was selected");
                        ReminderDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    private void setAlarm(){
        Log.v(TAG, "setAlarm Called");


//        AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//        Intent i = new Intent(context, Landing.class);
//        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
//        Calendar myCal = Calendar.getInstance();
//        long curTime = myCal.getTimeInMillis();
//        myCal.setTimeInMillis(curTime + 50000);
//        mgr.set(AlarmManager.RTC_WAKEUP, myCal.getTimeInMillis(), pi);
//        Log.i("1", "alarm set for " + myCal.getTime().toLocaleString());
//        Toast.makeText(getActivity().getApplicationContext(),"Alarm set for " + myCal.getTime().toLocaleString(), Toast.LENGTH_LONG).show();
//        Log.v(TAG, "setAlarm Done.");


        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        //cal.add(Calendar.HOUR_OF_DAY,hour);
        //cal.add(Calendar.MINUTE, min);
        Intent intent = new Intent(context,  MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 1, intent,0);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis() , pendingIntent);
        Toast.makeText(context, "Alarm set", Toast.LENGTH_LONG).show();
        Log.v(TAG, "setAlarm Done.");
    }

    public void startAlert() {
        //EditText text = (EditText) f.findViewById(R.id.time);
        //int i = Integer.parseInt(text.getText().toString());
        Log.v(TAG, "startAlert called");
        int i = 0;
        Intent intent = new Intent(getActivity(), MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getActivity().getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (i * 1000), pendingIntent);
//        Toast.makeText(getActivity(), "Alarm set in " + i + " seconds",
//                Toast.LENGTH_LONG).show();
        Log.v(TAG, "startAlert finished");
    }

    public void sendBroadcast() {
        Intent broadcast = new Intent();
        broadcast.setAction(BROADCAST_ACTION);
        broadcast.addCategory(Intent.CATEGORY_DEFAULT);
        //sendBroadcast(broadcast);
    }


}

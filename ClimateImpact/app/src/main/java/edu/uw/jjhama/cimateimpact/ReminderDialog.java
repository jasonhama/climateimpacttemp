package edu.uw.jjhama.cimateimpact;

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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by iguest on 4/29/16.
 */
public class ReminderDialog extends DialogFragment{

    String action;
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

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.reminder_dialog, null))
                // Add action buttons
                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.v(TAG, "Event Created");
                        // sign in the user ...
                        //make a personalized notification
                        Dialog f = (Dialog) dialog;
                        EditText firstName = (EditText) f.findViewById(R.id.firstName);
                        String startTime = ((String) ((EditText) f.findViewById(R.id.hoursStart)).getText().toString()) + ":" + ((String) ((EditText) f.findViewById(R.id.minutesStart)).getText().toString());

                        //Log.v(TAG, "Submit was selected \n username: "+ );
                        Context context = getActivity();

                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(getActivity())
                                        .setSmallIcon(R.drawable.ic_menu_send)
                                        .setContentTitle("Reminder Set!")
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
}

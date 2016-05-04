package edu.uw.jjhama.cimateimpact;

/**
 * Created by iguest on 5/3/16.
 */

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.os.Vibrator;
        import android.util.Log;
        import android.widget.Toast;


public class MyBroadcastReceiver extends BroadcastReceiver  {

    private static final String TAG = "MyBroadcastReceiver";

    BroadcastReceiver br = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.w("Check", "Inside On Receiver");
            Toast.makeText(context, "received",
                    Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "BROADCAST RECEIVED!!!=======================================================");
        Toast.makeText(context, "Don't panic but your time is up!!!!",
                Toast.LENGTH_LONG).show();

        /*// Vibrate the mobile phone
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);  */
    }
}
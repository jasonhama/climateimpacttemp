package edu.uw.jjhama.cimateimpact;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by iguest on 4/29/16.
 */
public class Activity extends Fragment {

    private static final String TAG = "Activity";

    public Activity(){
        //required empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.activity, container, false);
        Button info = (Button) rootView.findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.v(TAG, "INFO was selected!");

                InfoFragment infoFragment = new InfoFragment();
                infoFragment.show(getActivity().getFragmentManager(), "hello");
                /*getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new Activity())
                        .addToBackStack(null)
                        .commit();
                        */
            }
        });

        Button reminder = (Button) rootView.findViewById(R.id.reminder);
        reminder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.v(TAG, "Reminder was selected");
            }
        });

        return rootView;
    }
}

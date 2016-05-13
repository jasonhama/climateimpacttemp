package edu.uw.jjhama.cimateimpact;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by iguest on 5/12/16.
 */
public class ActivityListFragment extends Fragment {

    private static final String TAG = "Profile";

    public ActivityListFragment(){
        //required empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.activity_list, container, false);
        getActivity().setTitle("Activity List");
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        Log.v(TAG, "Fragment loaded!");


        return rootView;
    }
}

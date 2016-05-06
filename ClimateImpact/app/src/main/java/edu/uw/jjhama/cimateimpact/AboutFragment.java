package edu.uw.jjhama.cimateimpact;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by iguest on 5/5/16.
 */
public class AboutFragment extends Fragment {
    private static final String TAG = "AboutFragment";

    public AboutFragment(){
        //required empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.about_layout, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        return rootView;
    }
}

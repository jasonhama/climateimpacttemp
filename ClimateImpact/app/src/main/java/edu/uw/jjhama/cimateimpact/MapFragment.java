package edu.uw.jjhama.cimateimpact;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by iguest on 5/12/16.
 */
public class MapFragment extends Fragment{

    private static final String TAG = "MapFragment";

    public MapFragment(){
        //required empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.map_layout, container, false);
        getActivity().setTitle("My Environment");
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        // Here it is
        ImageButton camBt = (ImageButton)rootView.findViewById(R.id.map);
        camBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new CountyFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });


        Log.v(TAG, "Fragment loaded!");


        return rootView;
    }
}

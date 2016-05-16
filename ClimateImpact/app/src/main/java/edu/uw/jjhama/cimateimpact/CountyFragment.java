package edu.uw.jjhama.cimateimpact;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by iguest on 5/13/16.
 */
public class CountyFragment extends Fragment {

    private static final String TAG = "CountyFragment";

    public CountyFragment(){
        //required empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.county_layout, container, false);
        getActivity().setTitle("County");
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        Log.v(TAG, "Fragment loaded!");


        final Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);

        //TextView tmpView = (TextView) spinner.getSelectedView().findViewById(android.R.id.);
        //tmpView.setTextColor(Color.WHITE);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                ImageView map = (ImageView) rootView.findViewById(R.id.map);
                String text = spinner.getSelectedItem().toString();
                Log.v(TAG, text);
                if(text.equals("King")){
                    map.setImageResource(R.drawable.king);
                } else if (text.equals("Snohomish")){
                    map.setImageResource(R.drawable.snohomish);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        return rootView;
    }}

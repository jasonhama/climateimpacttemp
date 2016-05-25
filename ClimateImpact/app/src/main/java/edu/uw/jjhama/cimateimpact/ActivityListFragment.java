package edu.uw.jjhama.cimateimpact;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

/**
 * Created by iguest on 5/12/16.
 */
public class ActivityListFragment extends Fragment {

    private static final String TAG = "ActivityList";
    private DatabaseReference mDatabase;
    String email;
    ActivityDetails activityDetails;
    private AdapterView listView; //gets a listview
    //private ArrayAdapter<String> adapter = null;
    private List<ActivityDetails> activityList = new ArrayList<ActivityDetails>();
    private ArrayAdapter<ActivityDetails> adapter; //is the adapter to store all the rankings
    public ActivityListFragment(){
        //required empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.detail, container, false);
        listView = (ListView) rootView.findViewById(R.id.leaderboard_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                String selectedFromList =(listView.getItemAtPosition(myItemInt).toString());
                Log.v(TAG, "selected something~" + selectedFromList);

                //todo:send to an ActivityInfoDialog with a bundle attached
                Bundle bundle = new Bundle();

                bundle.putString("email", email);
                bundle.putString("uuid", activityDetails.getUUID());
                bundle.putString("action", activityDetails.getAction());
                bundle.putString("startTime", activityDetails.getStartTime());
                bundle.putString("endTime", activityDetails.getEndTime());
                bundle.putString("frequency", activityDetails.getFrequency());
                final ActivityInfoDialog activityInfoDialog = new ActivityInfoDialog();
                activityInfoDialog.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, activityInfoDialog)
                        .addToBackStack(null)
                        .commit();
//                new Handler().post(new Runnable() {
//                    public void run() {
//                        activityInfoDialog.show(getActivity().getFragmentManager(), "hello");
//                    }
//                });
//
//                Bundle bundle = new Bundle();
//                bundle.putString("title", selectedFromList);
//
//                DetailFragment detail = new DetailFragment();
//                detail.setArguments(bundle);
//
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.container2, detail)
//                        .addToBackStack(null)
//                        .commit();
            }
        });
        getActivity().setTitle("Activity List");
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        Log.v(TAG, "Fragment loaded!");

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
        Log.v(TAG, email);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(email).child("tasks");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Log.v(TAG, postSnapshot.getKey() + "");
                    activityDetails = new ActivityDetails(postSnapshot.getKey(), (String) postSnapshot.child("startTime").getValue(), (String) postSnapshot.child("endTime").getValue(), (String) postSnapshot.child("action").getValue(), (String) postSnapshot.child("frequency").getValue());
                    Log.v(TAG, activityDetails.toString());
                    activityList.add(activityDetails);
                    adapter.add(activityDetails);
                }

                Log.v(TAG, activityList.size() + "");
                //adapter = new ArrayAdapter(getActivity(), R.layout.details, R.id.txtItem);
                Log.v(TAG, "activities have been loaded!");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                Toast.makeText(getActivity(), "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
            }
        });

//        Log.v(TAG, activityList.size() + "");
        adapter = new ArrayAdapter(getActivity(), R.layout.details, R.id.txtItem);
//        Log.v(TAG, "activities have been loaded!");
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        listView.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

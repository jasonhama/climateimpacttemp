package edu.uw.jjhama.cimateimpact;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by iguest on 4/21/16.
 */
public class ProfileFragment extends Fragment {

    private static final String TAG = "Profile";
    private DatabaseReference mDatabase;
    String email;
    Transaction.Handler myHandler;

    private String name = "";
    private final String[] waterAmount = {"0"};
    private final String[] carbonAmount ={"0"};
    private String fName = "";
    private String lName = "";
    private String zipValue = "";

    public ProfileFragment(){
        //required empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.profile, container, false);
        getActivity().setTitle("Profile");
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        Log.v(TAG, "Profile page loading");



        //set the values to users account details

        //get email from
        Bundle getBundle = getArguments();
        email = getBundle.getString("email");

//        String name = "";
//        final String[] waterAmount = {"0"};
//        final String[] carbonAmount ={"0"};

        //query firebase and retrieve user info
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(email);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //set variables
                waterAmount[0] = String.valueOf(snapshot.child("water").getValue());
                Log.v(TAG, "");
                //waterAmount[0] = "a";
                Log.v(TAG, "--------------------------------------------------");
                Log.v(TAG, snapshot.child("water") + "");
                //Log.v(TAG, snapshot.child("uuid").getValue() + "");
                Log.v(TAG, "--------------------------------------------------");
                carbonAmount[0] = String.valueOf(snapshot.child("carbon").getValue() + "");
                //carbonAmount[0] = "b";
                name = (String) snapshot.child("name").getValue();

                //todo:username, firstname, lastname, zip
                Log.v(TAG, snapshot.child("zip") + "");
                zipValue = (String) snapshot.child("zip").getValue();
                fName = (String) snapshot.child("fName").getValue();
                lName = (String) snapshot.child("lName").getValue();
                Log.v(TAG, waterAmount[0] + carbonAmount[0] + zipValue + fName + lName + name);

                //get the different textviews I want to manipulate
                TextView firstName = (TextView) rootView.findViewById(R.id.fName);
                TextView lastName = (TextView) rootView.findViewById(R.id.lName);
                TextView carbon = (TextView) rootView.findViewById(R.id.carbon);
                TextView water = (TextView) rootView.findViewById(R.id.water);
                TextView username = (TextView) rootView.findViewById(R.id.username);
                TextView zip = (TextView) rootView.findViewById(R.id.txtZip);

                //set the textviews with data from the database
                carbon.setText(carbonAmount[0] + "");
                water.setText(waterAmount[0] + "");
                firstName.setText(fName);
                lastName.setText(lName);
                zip.setText(zipValue);
                Log.v(TAG, zipValue);
                username.setText(name);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                Toast.makeText(getActivity(), "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //go get to activities
        Button button = (Button) rootView.findViewById(R.id.activity);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new Activity())
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button change = (Button) rootView.findViewById(R.id.alterInfo);
        change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.v(TAG, "alter user info");
                ChangeUserDataFragment changeUserDataFragment = new ChangeUserDataFragment();
                changeUserDataFragment.show(getActivity().getFragmentManager(), "hello");

            }
        });


//        if(getBundle != null) {
//            name = getBundle.getString("name");
//            waterAmount = getBundle.getInt("water");
//            carbonAmount = getBundle.getInt("carbon");
//        }

        return rootView;
    }
}

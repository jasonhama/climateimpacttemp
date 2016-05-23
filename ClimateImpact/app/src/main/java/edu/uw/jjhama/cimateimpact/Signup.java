package edu.uw.jjhama.cimateimpact;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Map;
import java.util.UUID;

public class Signup extends Fragment {

    private static final String TAG = "Signup";
    private DatabaseReference mDatabase;

    public Signup(){
        //required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.signup, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        Button submit = (Button) rootView.findViewById(R.id.submit);
        Button signin = (Button) rootView.findViewById(R.id.signin);

        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                //gets the users information
                String name = ((EditText) rootView.findViewById(R.id.name)).getText().toString();
                String email = ((EditText) rootView.findViewById(R.id.email)).getText().toString();
                String password = ((EditText) rootView.findViewById(R.id.password)).getText().toString();
                String confirmPassword = ((EditText) rootView.findViewById(R.id.confirmPassword)).getText().toString();


                if(name != null && email != null) {
                    if(password.length() > 0) {
                        if (password.equals(confirmPassword)) {

                            //pack up a bundle
                            Bundle bundle = new Bundle();
                            bundle.putString("email", email);
                            
                            //creates a new user on Firebase
                            UUID uuid = UUID.randomUUID();
                            mDatabase = mDatabase.child(email);
                            mDatabase.child("uuid").setValue(uuid + "");
                            mDatabase.child("name").setValue(name);
                            mDatabase.child("password").setValue(password);
                            mDatabase.child("carbon").setValue(0);
                            mDatabase.child("water").setValue(0);
                            mDatabase.child("fName").setValue("first name");
                            mDatabase.child("lName").setValue("last name");
                            mDatabase.child("zip").setValue("not yet set");
                            //zipcode
                            //current_score
                            //carbon_footprint
                            //firstname
                            //lastname
                            //profile pic
                            //default location

                            Log.v(TAG, "user created");
                            MapFragment mapFragment = new MapFragment();
                            mapFragment.setArguments(bundle);

                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.container, mapFragment)
                                    .addToBackStack(null)
                                    .commit();
                        } else {
                            Log.v(TAG, "password and confirm were not the same");
                        }
                    } else {
                        Log.v(TAG, "password was empty");
                    }
                } else {
                    Log.v(TAG, "name or email was null");
                }
            }
        });


        signin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                //send the user to the signin page
                //Todo:change fragment to Signin
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new Signin())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return rootView;
    }
}

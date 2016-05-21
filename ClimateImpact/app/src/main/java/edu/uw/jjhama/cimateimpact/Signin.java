package edu.uw.jjhama.cimateimpact;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

//import com.firebase.client.AuthData;
//import com.firebase.client.DataSnapshot;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

/**
 * Created by iguest on 4/21/16.
 */
public class Signin extends Fragment {

    private static final String TAG = "Signin";

    AccountDetails accountDetails;
    private DatabaseReference mDatabase;



    public Signin(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.signin, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        //Firebase.setAndroidContext(getActivity());

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");



        Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                final String email = (String) ((EditText) rootView.findViewById(R.id.email)).getText().toString();
                final String password = (String) ((EditText) rootView.findViewById(R.id.password)).getText().toString();
                Log.v(TAG, "Email : " + email + "\nPassword : " + password);
                //mDatabase = mDatabase.child(email);

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        // Get Post object and use the values to update the UI
                        //Post post = dataSnapshot.getValue(Post.class);
                        // ...
//                        Log.v(TAG, snapshot.child(email) + "");
//                        Log.v(TAG, "----------------------------------------------------");
//                        Log.v(TAG, "Email : " + snapshot.child(email).getKey());
//                        Log.v(TAG, "Email : " + snapshot.child(email).getValue());
//                        Log.v(TAG, "Email : " + snapshot.child(email).getValue());
//                        Log.v(TAG, "----------------------------------------------------");
//                        Log.v(TAG, "Password : " + snapshot.child(email).child(password));
//                        Log.v(TAG, "----------------------------------------------------");
//                        Log.v(TAG, "Password : " + snapshot.child(email).child(password).getKey());
//                        Log.v(TAG, "Password : " + snapshot.child(email).child("password").getValue());
//                        Log.v(TAG, "----------------------------------------------------");

                        if(snapshot.child(email).getValue() != null){
                            if(snapshot.child(email).child("password").getValue().equals(password)){
                                //Log.v(TAG, "Success! email and password match!");
                                //accountDetails = new AccountDetails("a","a","a", "k");
                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.container, new ProfileFragment())
                                        .addToBackStack(null)
                                        .commit();
                            } else {
                                //password is incorrect
                                //Log.v(TAG, "password is incorrect");
                                Toast.makeText(getActivity(),"Login attempt failed!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            //user does not exist
                            //Log.v(TAG, "user does not exist");
                            Toast.makeText(getActivity(),"Login attempt failed!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                        Toast.makeText(getActivity(), "Failed to load post.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                //Firebase ref = new Firebase("https://climateimpact.firebaseio.com/");
                //Firebase userRef = ref.child("users").child(email).child("password");
                //Log.v(TAG, userRef.child("password").toString());
                //userRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot snapshot) {
//                        Log.v(TAG, "" + snapshot.getValue());
//                        if(snapshot.getValue() != null){
//                            if(snapshot.getValue().equals(password)){
//                                Log.v(TAG, "Success! email and password match!");
//                                accountDetails = new AccountDetails("a","a","a", "k");
//                                getActivity().getSupportFragmentManager()
//                                        .beginTransaction()
//                                        .replace(R.id.container, new ProfileFragment())
//                                        .addToBackStack(null)
//                                        .commit();
//                            } else {
//                                //password is incorrect
//                                Toast.makeText(getActivity(),"Login attempt failed!", Toast.LENGTH_LONG).show();
//                            }
//                        } else {
//                            //user does not exist
//                            Toast.makeText(getActivity(),"Login attempt failed!", Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                    @Override
//                    public void onCancelled(FirebaseError firebaseError) {
//                        Log.v(TAG, "The read failed: " + firebaseError.getMessage());
//                    }
//                });
//                userRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
//                    @Override
//                    public void onAuthenticated(AuthData authData) {
//                        //System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
//                        Log.v(TAG, "Success!");
//                        //send the user to the signin page
//                        getActivity().getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.container, new MapFragment())
//                                .addToBackStack(null)
//                                .commit();
//                    }
//                    @Override
//                    public void onAuthenticationError(FirebaseError firebaseError) {
//                        // there was an error
//                        Log.v(TAG, "ERROR+!+!+!+!+!+!+!");
//                    }
//                });


            }
        });

        Button signup = (Button) rootView.findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Log.v(TAG, "Helasdfasdfasdfasdflo! ==========================================================");

                //send the user to the signin page
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new Signup())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }
}

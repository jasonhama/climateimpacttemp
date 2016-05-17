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

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.UUID;

/**
 * Created by iguest on 4/21/16.
 */
public class Signin extends Fragment {

    private static final String TAG = "Signin";



    public Signin(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.signin, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Firebase.setAndroidContext(getActivity());



        Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Log.v(TAG, "Hello! ==========================================================");


                String email = (String) ((EditText) rootView.findViewById(R.id.email)).getText().toString();
                String password = (String) ((EditText) rootView.findViewById(R.id.password)).getText().toString();
                Log.v(TAG, "Email : " + email + "\nPassword : " + password);

                Firebase ref = new Firebase("https://climateimpact.firebaseio.com/");
                Firebase userRef = ref.child("users").child(email);


                userRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        //System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                        Log.v(TAG, "Success!");
                        //send the user to the signin page
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, new MapFragment())
                                .addToBackStack(null)
                                .commit();
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // there was an error
                        Log.v(TAG, "ERROR+!+!+!+!+!+!+!");
                    }
                });


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

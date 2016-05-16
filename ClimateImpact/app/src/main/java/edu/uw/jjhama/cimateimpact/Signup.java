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

/**
 * Created by iguest on 4/21/16.
 */
public class Signup extends Fragment {

    private static final String TAG = "Signup";

    public Signup(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.signup, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        Button submit = (Button) rootView.findViewById(R.id.submit);
        Button signin = (Button) rootView.findViewById(R.id.signin);

        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Log.v(TAG, "Hello! ==========================================================");

                //gets the users information
                EditText nameET = (EditText) rootView.findViewById(R.id.name);
                EditText emailET = (EditText) rootView.findViewById(R.id.email);
                EditText passwordET = (EditText) rootView.findViewById(R.id.password);
                EditText confirmPasswordET = (EditText) rootView.findViewById(R.id.confirmPassword);
                String name = nameET.getText().toString();
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                String confirmPassword = confirmPasswordET.getText().toString();


                if(name != null && email != null) {
                    if(password.length() > 0) {
                        if (password.equals(confirmPassword)) {
                            //send the user to the signin page
                            AccountDetails accountDetails = new AccountDetails();
                            accountDetails.setfName(name);
                            accountDetails.setEmail(email);
                            Bundle bundle = new Bundle();
                            bundle.putString("name", name);
                            bundle.putString("email", email);
                            bundle.putInt("carbon", 0);
                            bundle.putInt("water", 0);
                            
                            //Todo: create a new fragment (MapFragment) and change fragment to MapFragment
                            //ProfileFragment profileFragment = new ProfileFragment();
                            //profileFragment.setArguments(bundle);
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
                Log.v(TAG, "Helasdfasdfasdfasdflo! ==========================================================");

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

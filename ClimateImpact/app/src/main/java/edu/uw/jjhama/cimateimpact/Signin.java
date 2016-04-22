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

        Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Log.v(TAG, "Hello! ==========================================================");



                //send the user to the signin page
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new ProfileFragment())
                        .addToBackStack(null)
                        .commit();
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
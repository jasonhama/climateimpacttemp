package edu.uw.jjhama.cimateimpact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import junit.framework.Test;

/**
 * Created by iguest on 4/21/16.
 */
public class Startup extends Fragment {

    private static final String TAG = "Startup";

    public Startup(){
        //required empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.startup, container, false);
        Button signup = (Button) rootView.findViewById(R.id.signup);
        Button signin = (Button) rootView.findViewById(R.id.signin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Here!");
                //Fragment manager

                //Signin signin = new Signin();
                //signin.show(getActivity().getSupportFragmentManager());


                ProfileFragment profileFragment = new ProfileFragment();
                //profileFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, profileFragment)
                        .addToBackStack(null)
                        .commit();
                /*
                RecordFragment frag = new RecordFragment();
                frag.show(getActivity().getSupportFragmentManager(), "banana");


                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container1, new MasterListFragment())
                        .addToBackStack(null)
                        .commit();
                Intent intent = new Intent(TestFragment.this, edu.uw.jjhama.cimateimpact.Signup.class);
                startActivity(intent);
                */
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fragment manager
                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.container, new Signin())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return rootView;

    }

}
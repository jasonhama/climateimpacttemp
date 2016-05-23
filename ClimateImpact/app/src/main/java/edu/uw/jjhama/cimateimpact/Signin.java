package edu.uw.jjhama.cimateimpact;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signin extends Fragment {

    private static final String TAG = "Signin";
    private DatabaseReference mDatabase;

    public Signin(){
        //required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.signin, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                final String email = ((EditText) rootView.findViewById(R.id.email)).getText().toString();
                final String password = ((EditText) rootView.findViewById(R.id.password)).getText().toString();
                Log.v(TAG, "Email : " + email + "\nPassword : " + password);

                //check if the user input an email
                if (!email.equals("")){

                    Log.v(TAG, "email exists");

                    //checks if the user input a password
                    if (!password.equals("")) {

                        Log.v(TAG, "password exists");
                        mDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {

                                Log.v(TAG, "checking database");
                                //check if the email provided exists
                                if (snapshot.child(email).getValue() != null) {

                                    Log.v(TAG, "email exists in database");
                                    //check if the password is the same as the user supplied password
                                    if (snapshot.child(email).child("password").getValue().equals(password)) {

                                        Log.v(TAG, "password matches");
                                        Log.v(TAG, "Success! email and password match!");
                                        Bundle bundle = new Bundle();
                                        bundle.putString("email", email);
                                        final ProfileFragment profileFragment = new ProfileFragment();
                                        profileFragment.setArguments(bundle);
                                        new Handler().post(new Runnable() {
                                            public void run() {
                                                getActivity().getSupportFragmentManager()
                                                        .beginTransaction()
                                                        .replace(R.id.container, profileFragment)
                                                        .addToBackStack(null)
                                                        .commit();
                                            }
                                        });

                                    } else {

                                        //password is incorrect
                                        Log.v(TAG, "password is incorrect");
                                        Toast.makeText(getActivity(), "Login attempt failed!", Toast.LENGTH_LONG).show();
                                    }
                                } else {

                                    //user does not exist
                                    Log.v(TAG, "user does not exist");
                                    Toast.makeText(getActivity(), "Login attempt failed!", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                                Toast.makeText(getActivity(), "Failed to load post.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        Log.v(TAG, "password is empty");
                        Toast.makeText(getActivity(), "Please enter email and password to sign in",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.v(TAG, "email is empty");
                    Toast.makeText(getActivity(), "Please enter email and password to sign in",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button signup = (Button) rootView.findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Log.v(TAG, "Going to signup page!");

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

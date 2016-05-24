package edu.uw.jjhama.cimateimpact;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
//import com.firebase.client.AuthData;
//import com.firebase.client.Firebase;

import junit.framework.Test;

/**
 * Created by iguest on 4/21/16.
 */
public class Startup extends Fragment {

    private static final String TAG = "Startup";
    CallbackManager callbackManager;
    private LoginButton loginButton;

    /* *************************************
     *              GENERAL                *
     ***************************************/
    /* TextView that is used to display information about the logged in user */
    private TextView mLoggedInStatusTextView;

    /* A dialog that is presented until the Firebase authentication finished. */
    private ProgressDialog mAuthProgressDialog;

    /* A reference to the Firebase */
//    private Firebase mFirebaseRef;
//
//    /* Data from the authenticated user */
//    private AuthData mAuthData;
//
//    /* Listener for Firebase session changes */
//    private Firebase.AuthStateListener mAuthStateListener;

    /* *************************************
     *              FACEBOOK               *
     ***************************************/
    /* The login button for Facebook */
    private LoginButton mFacebookLoginButton;
    /* The callback manager for Facebook */
    private CallbackManager mFacebookCallbackManager;
    /* Used to track user logging in/out off Facebook */
    private AccessTokenTracker mFacebookAccessTokenTracker;


    public Startup(){
        //required empty
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.startup, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

//        /* *************************************
//         *              FACEBOOK               *
//         ***************************************/
//        /* Load the Facebook login button and set up the tracker to monitor access token changes */
//        mFacebookCallbackManager = CallbackManager.Factory.create();
//        mFacebookLoginButton = (LoginButton) findViewById(R.id.login_with_facebook);
//        mFacebookAccessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//                Log.i(TAG, "Facebook.AccessTokenTracker.OnCurrentAccessTokenChanged");
//                .onFacebookAccessTokenChange(currentAccessToken);
//            }
//        };

        Button signup = (Button) rootView.findViewById(R.id.signup);
        Button signin = (Button) rootView.findViewById(R.id.signin);
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) rootView.findViewById(R.id.login_button);
        loginButton.setFragment(this);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.v(TAG, "Successs");
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Success", Toast.LENGTH_SHORT);
                toast.show();
                //send the user to the signin page
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.container, new ProfileFragment())
//                        .addToBackStack(null)
//                        .commit();
            }

            @Override
            public void onCancel() {
                Log.v(TAG, "Cancel");
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Cancel", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onError(FacebookException e) {
                Log.v(TAG, "Error");
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Here!");
                //Fragment manager

                //Signin signin = new Signin();
                //signin.show(getActivity().getSupportFragmentManager());


                //Signup signupFragment = new Signup();
                //profileFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new Signup())
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
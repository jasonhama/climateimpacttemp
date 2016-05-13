package edu.uw.jjhama.cimateimpact;

import android.accounts.Account;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
// Add this to the header of your file:
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

/**
 * Created by iguest on 4/21/16.
 */
public class Landing extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "Landing";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "edu.uw.jjhama.cimateimpact",
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.v(TAG, "==================================================");
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                Log.v(TAG, "================================================");
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.v(TAG, "Catch1");
        } catch (NoSuchAlgorithmException e) {
            Log.v(TAG, "Catch2");
        }
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_startup);

        //put the startup fragment in view
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new Startup())
                .addToBackStack(null)
                .commit();

//zY00cczs2SG28RTn1bZQvsCftvI=

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.startup, menu);
        return true;
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //AccountDetails accountDetails = ((AccountDetails) this.getApplicationContext());
        //AccountDetails accountDetails = new AccountDetails().getAccountDetails();
        if (id == R.id.profile) {
            Log.v(TAG, "camera");
            //if(accountDetails.getfName() == null) {
                //put the startup fragment in view
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new ProfileFragment())
                        .addToBackStack(null)
                        .commit();
            //} else {
                //Toast.makeText(this,"You must be logged in to get to Profile", Toast.LENGTH_LONG).show();
            //}
            // Handle the camera action
        } else if (id == R.id.signup) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Signup())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.signin) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Signin())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.activities) {
            Log.v(TAG, "hihihihihihi");
            //if (accountDetails.getfName() == null) {
                //put the startup fragment in view
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new Activity())
                        .addToBackStack(null)
                        .commit();
//            } else {
//                Toast.makeText(this, "You must be logged in to get to Profile", Toast.LENGTH_LONG).show();
//            }

        } else if (id == R.id.landing) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Startup())
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.about) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new AboutFragment())
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.map) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new MapFragment())
                    .addToBackStack(null)
                    .commit();
        }
//        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
    public void selectDrawerItem(MenuItem menuItem) {
        if(menuItem == null) {
            if(mLastMenuItem != null) {
                mLastMenuItem.setChecked(false);
            }
            return;
        }
        mLastMenuItem = menuItem;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        //menu items
        //each case hides the non active fragments and shows the selected fragment
        switch(menuItem.getItemId()) {
            case R.id.nav_map_fragment:
                ft.show(mMapFragment)
                        .hide(mChatFragment)
                        .hide(mLeaderboardFragment)
                        .hide(mProfileFragment);
                killButton.setVisibility(View.VISIBLE);
                break;
            case R.id.nav_chat_fragment:
                ft.show(mChatFragment)
                        .hide(mMapFragment)
                        .hide(mLeaderboardFragment)
                        .hide(mProfileFragment);
                killButton.setVisibility(View.GONE);
                break;
            case R.id.nav_profile_fragment:
                ft.show(mProfileFragment)
                        .hide(mChatFragment)
                        .hide(mMapFragment)
                        .hide(mLeaderboardFragment);
                killButton.setVisibility(View.GONE);
                break;
            case R.id.nav_leaderboard_fragment:
                ft.show(mLeaderboardFragment)
                        .hide(mChatFragment)
                        .hide(mMapFragment)
                        .hide(mProfileFragment);
                killButton.setVisibility(View.GONE);
                break;
            default:
                ft.show(mMapFragment)
                        .hide(mChatFragment)
                        .hide(mLeaderboardFragment)
                        .hide(mProfileFragment);
                killButton.setVisibility(View.GONE);
                break;
        }
        ft.commit();

        // Highlight the selected item, update the title, and close the drawer
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }*/
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.everyDay:
                if (checked)
                    // Pirates are the best
                    Log.v(TAG, "every day selected");
                break;
            case R.id.everyOtherDay:
                if (checked)
                    // Ninjas rule
                    Log.v(TAG, "every other day selected");
                break;
            case R.id.everyWeek:
                if (checked)
                    // Ninjas rule
                    Log.v(TAG, "every week selected");
                break;
            case R.id.everyOtherWeek:
                if (checked)
                    // Ninjas rule
                    Log.v(TAG, "every other week selected");
                break;
            case R.id.everyMonth:
                if (checked)
                    // Ninjas rule
                    Log.v(TAG, "every month selected");
                break;

        }
    }

}

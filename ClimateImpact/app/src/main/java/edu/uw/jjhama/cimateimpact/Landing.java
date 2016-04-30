package edu.uw.jjhama.cimateimpact;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by iguest on 4/21/16.
 */
public class Landing extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "Landing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //put the startup fragment in view
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new Startup())
                .addToBackStack(null)
                .commit();
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

        if (id == R.id.profile) {
            Log.v(TAG, "camera");
            //put the startup fragment in view
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new ProfileFragment())
                    .addToBackStack(null)
                    .commit();
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
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

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

}

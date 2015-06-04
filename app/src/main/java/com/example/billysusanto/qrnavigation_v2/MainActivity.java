package com.example.billysusanto.qrnavigation_v2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.billysusanto.qrnavigation_v2.dummy.DummyContent;

import static com.example.billysusanto.qrnavigation_v2.R.id;
import static com.example.billysusanto.qrnavigation_v2.R.layout;
import static com.example.billysusanto.qrnavigation_v2.R.string;

//import android.support.v4.app.Fragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, ItemFragment.OnFragmentInteractionListener, DetailFragment.OnFragmentInteractionListener{
    android.app.FragmentManager fragmentManager;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    ItemFragment history = new ItemFragment();
    Fragment detailFragment = new DetailFragment();
    public static boolean kentangRebus = false;
    public static boolean burger = true;
    int counter = 6;
    public static Context mainContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                id.navigation_drawer,
                (DrawerLayout) findViewById(id.drawer_layout));

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        history = ItemFragment.newInstance("", "");

        fragmentManager = getFragmentManager();

        if(position == 0) {
           fragmentManager.beginTransaction()
                   .replace(id.container, history).addToBackStack(null)
                    .commit();
        }
        else if(position == 1) {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        }
        else if(position == 2){
            finish();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan
                String split [] = contents.split("/");

                if((split[0].equalsIgnoreCase("Burger Lapar") && burger == true) || (split[0].equalsIgnoreCase("Kentang Rebus") && kentangRebus == true)){
                    Toast.makeText(this, "Data "+split[0]+" Sudah Ada\n" + "Tidak Ditambahkan lagi ke History", Toast.LENGTH_LONG).show();
                }
                else{
                    DummyContent.ITEMS.add(0, new DummyContent.DummyItem(""+counter, split[0], split[1], split[2], split[3], split[4], split[5], split[6]));
                    if(split[0].equalsIgnoreCase("Burger Lapar")){
                        burger = true;
                    }
                    else if(split[0].equalsIgnoreCase("Kentang Rebus")){
                        kentangRebus = true;
                    }
                    counter++;
                    onNavigationDrawerItemSelected(0);
                }

            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(string.title_section1);
                break;
            case 2:
                mTitle = getString(string.title_section2);
                break;
            case 3:
                mTitle = getString(string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void loadDetailFragment(int position,
                                   String sLat, String sLong,
                                   String dLat, String dLong,
                                   String judul, String detail){
        fragmentManager = getFragmentManager();

        detailFragment = DetailFragment.newInstance(position, judul, detail);
        fragmentManager.beginTransaction()
                .detach(history)
                .replace(id.container, detailFragment)
                .attach(detailFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {

        } else {
            //getFragmentManager().popBackStack();
        }
        this.finish();
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}



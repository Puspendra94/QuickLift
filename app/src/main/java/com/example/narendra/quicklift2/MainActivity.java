package com.example.narendra.quicklift2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback,NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    private GoogleMap mMap;
    private static EditText source,dest;
    private static TextView fare;
    private static Button ride,confirm,bike,car,shareCar,auto,shareAuto,rickshaw,shareRickshaw;
    private static HorizontalScrollView fragmentTwo;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng currentLocation = new LatLng(12.831385, 77.700493);
        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        //mMap.getMaxZoomLevel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bike = findViewById(R.id.bike);
        bike.setOnClickListener(this);

        car = findViewById(R.id.car);
        car.setOnClickListener(this);

        shareCar = findViewById(R.id.shareCar);
        shareCar.setOnClickListener(this);

        auto = findViewById(R.id.auto);
        auto.setOnClickListener(this);

        shareAuto = findViewById(R.id.shareAuto);
        shareAuto.setOnClickListener(this);

        rickshaw = findViewById(R.id.rickshaw);
        rickshaw.setOnClickListener(this);

        shareRickshaw = findViewById(R.id.shareRickshaw);
        shareRickshaw.setOnClickListener(this);

        source = findViewById(R.id.source_main);
        dest = findViewById(R.id.destination_main);
        fare = findViewById(R.id.fare_main);
        ride = findViewById(R.id.ride_button_main);
        ride.setOnClickListener(this);
        confirm = findViewById(R.id.confirm_main);
        confirm.setOnClickListener(this);
        fragmentTwo = findViewById(R.id.fragmentTwo);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Upcoming Offers !!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {

            int id = view.getId();
            switch(id){
                case R.id.bike :
                    Log.i("clicked Bike","Hide fragment");
                    //transaction.detach(two);
                    //transaction.commit();
                    bike.setEnabled(false);
                    car.setEnabled(true);
                    shareCar.setEnabled(true);
                    auto.setEnabled(true);
                    shareAuto.setEnabled(true);
                    rickshaw.setEnabled(true);
                    shareRickshaw.setEnabled(true);
                    ride.setVisibility(View.VISIBLE);
                    fare.setVisibility(View.GONE);
                    confirm.setVisibility(View.GONE);
                    break;
                case R.id.car :
                    bike.setEnabled(true);
                    car.setEnabled(false);
                    shareCar.setEnabled(true);
                    auto.setEnabled(true);
                    shareAuto.setEnabled(true);
                    rickshaw.setEnabled(true);
                    shareRickshaw.setEnabled(true);
                    ride.setVisibility(View.VISIBLE);
                    fare.setVisibility(View.GONE);
                    confirm.setVisibility(View.GONE);
                    break;
                case R.id.shareCar :
                    bike.setEnabled(true);
                    car.setEnabled(true);
                    shareCar.setEnabled(false);
                    auto.setEnabled(true);
                    shareAuto.setEnabled(true);
                    rickshaw.setEnabled(true);
                    shareRickshaw.setEnabled(true);
                    ride.setVisibility(View.VISIBLE);
                    fare.setVisibility(View.GONE);
                    confirm.setVisibility(View.GONE);
                    break;
                case R.id.auto :
                    bike.setEnabled(true);
                    car.setEnabled(true);
                    shareCar.setEnabled(true);
                    auto.setEnabled(false);
                    shareAuto.setEnabled(true);
                    rickshaw.setEnabled(true);
                    shareRickshaw.setEnabled(true);
                    ride.setVisibility(View.VISIBLE);
                    fare.setVisibility(View.GONE);
                    confirm.setVisibility(View.GONE);
                    break;
                case R.id.shareAuto :
                    bike.setEnabled(true);
                    car.setEnabled(true);
                    shareCar.setEnabled(true);
                    auto.setEnabled(true);
                    shareAuto.setEnabled(false);
                    rickshaw.setEnabled(true);
                    shareRickshaw.setEnabled(true);
                    ride.setVisibility(View.VISIBLE);
                    fare.setVisibility(View.GONE);
                    confirm.setVisibility(View.GONE);
                    break;
                case R.id.rickshaw :
                    bike.setEnabled(true);
                    car.setEnabled(true);
                    shareCar.setEnabled(true);
                    auto.setEnabled(true);
                    shareAuto.setEnabled(true);
                    rickshaw.setEnabled(false);
                    shareRickshaw.setEnabled(true);
                    ride.setVisibility(View.VISIBLE);
                    fare.setVisibility(View.GONE);
                    confirm.setVisibility(View.GONE);
                    break;
                case R.id.shareRickshaw :
                    bike.setEnabled(true);
                    car.setEnabled(true);
                    shareCar.setEnabled(true);
                    auto.setEnabled(true);
                    shareAuto.setEnabled(true);
                    rickshaw.setEnabled(true);
                    shareRickshaw.setEnabled(false);
                    ride.setVisibility(View.VISIBLE);
                    fare.setVisibility(View.GONE);
                    confirm.setVisibility(View.GONE);
                    break;
                case R.id.ride_button_main:
                    Log.i("ride button","ride button clicked");
                    fragmentTwo.setVisibility(View.GONE);
                    ride.setVisibility(View.GONE);
                    fare.setVisibility(View.VISIBLE);
                    confirm.setVisibility(View.VISIBLE);
                    break;
                default:
                    fragmentTwo.setVisibility(View.VISIBLE);
                    bike.setEnabled(true);
                    car.setEnabled(true);
                    shareCar.setEnabled(true);
                    auto.setEnabled(true);
                    shareAuto.setEnabled(true);
                    rickshaw.setEnabled(true);
                    shareRickshaw.setEnabled(false);
                    ride.setVisibility(View.GONE);
                    fare.setVisibility(View.GONE);
                    confirm.setVisibility(View.GONE);
            }
    }

    @Override
    protected void onStart() {
        super.onStart();
        fragmentTwo.setVisibility(View.VISIBLE);
        ride.setVisibility(View.GONE);
        fare.setVisibility(View.GONE);
        confirm.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent profile = new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(profile);
        } else if (id == R.id.nav_rides) {
            Intent rides = new Intent(MainActivity.this,RideActivity.class);
            startActivity(rides);
        } else if (id == R.id.nav_free_rides) {
            Intent free_ride = new Intent(MainActivity.this,FreeRideActivity.class);
            startActivity(free_ride);
            finish();
        } else if (id == R.id.nav_notif) {
            Intent notif = new Intent(MainActivity.this,NotifActivity.class);
            startActivity(notif);
            finish();
        } else if (id == R.id.nav_offers) {
            Intent offers = new Intent(MainActivity.this,OffersActivity.class);
            startActivity(offers);
            finish();
        } else if (id == R.id.nav_about) {
            Intent offers = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(offers);
            finish();
        } else if (id == R.id.nav_terms_cond) {
            Intent terms = new Intent(MainActivity.this,OffersActivity.class);
            startActivity(terms);
            finish();
        }  else if (id == R.id.nav_share) {
            ApplicationInfo app = getApplicationContext().getApplicationInfo();
            String filePath = app.sourceDir;
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/vnd.android.package-archive");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
            startActivity(Intent.createChooser(intent, "Share app using :"));

        } else if (id == R.id.nav_logout) {
            /*
            if (getIntent().getStringExtra("phone")!= null){
                AuthUI.getInstance().signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                                finish();
                            }

                        });
            }else{
                Toast.makeText(this, "phone is empty", Toast.LENGTH_SHORT).show();
            }
            */

        }else if (id == R.id.nav_home){
            Intent home = new Intent(MainActivity.this, MainActivity.class);
            startActivity(home);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

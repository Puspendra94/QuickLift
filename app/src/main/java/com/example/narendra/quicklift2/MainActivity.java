package com.example.narendra.quicklift2;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.Spinner;
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
    private FrameLayout button,vehicle_list,fav_dest;
    private EditText source,destination;
    private TextView fare;
    private CardView confirm0,confirm1,confirm2,confirm3;
    private Spinner user_list,seat_num,coupon_list,payment;
    String[] user_item,seat_item,coupon_item,payment_item;
    private DisplayMetrics displayMetrics;
    private Button bike,car,shareCar,auto,shareAuto,rickshaw,
            shareRickshaw,ride,confirm,home,work,marathahalli,kadubisnahalli,extra;

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

        user_item = new String[]{"Personal","Add Other"};
        seat_item = new String[]{"1","2","3","4","5","6"};
        coupon_item = new String[]{"QUICK_LIFT_WLC","QUICK_LIFT_RIDE","QUICK_LIFT_FIRST"};
        payment_item = new String[]{"Cash Mode","Paytm","PayUMoney"};

        user_list = (Spinner) findViewById(R.id.user);
        seat_num = (Spinner)findViewById(R.id.seat);
        coupon_list = (Spinner)findViewById(R.id.apply);
        payment = (Spinner)findViewById(R.id.payment_method);

        ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, user_item);
        ArrayAdapter<String> seatAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, seat_item);
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, payment_item);
        ArrayAdapter<String> couponAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, coupon_item);

        user_list.setAdapter(userAdapter);
        seat_num.setAdapter(seatAdapter);
        coupon_list.setAdapter(couponAdapter);
        payment.setAdapter(paymentAdapter);

        fav_dest = (FrameLayout)findViewById(R.id.fav_destination);
        button = (FrameLayout)findViewById(R.id.button);
        vehicle_list = (FrameLayout)findViewById(R.id.vehicles_list);
        source =  (EditText)findViewById(R.id.source_main);
        destination = (EditText)findViewById(R.id.destination_main);
        destination.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    hideFrame(fav_dest);
                }else {
                    String dest = destination.getText().toString().trim();
                    if (TextUtils.isEmpty(dest)){
                        showFrame(fav_dest);
                    }else {
                        String pickup = source.getText().toString().trim();
                        if (TextUtils.isEmpty(pickup)){
                            source.setFocusable(true);
                        }else {
                            hideFrame(fav_dest);
                            showFrame(vehicle_list);
                        }
                    }
                }
            }
        });

        fare = (TextView)findViewById(R.id.fare_main);
        fare.setOnClickListener(this);

        bike = (Button)findViewById(R.id.bike);
        bike.setOnClickListener(this);

        car = (Button)findViewById(R.id.car);
        car.setOnClickListener(this);

        shareCar = (Button)findViewById(R.id.shareCar);
        shareCar.setOnClickListener(this);

        auto = (Button)findViewById(R.id.auto);
        auto.setOnClickListener(this);

        shareAuto = (Button)findViewById(R.id.shareAuto);
        shareAuto.setOnClickListener(this);

        rickshaw = (Button)findViewById(R.id.rickshaw);
        rickshaw.setOnClickListener(this);

        shareRickshaw = (Button)findViewById(R.id.shareRickshaw);
        shareRickshaw.setOnClickListener(this);

        ride = (Button)findViewById(R.id.ride_button_main);
        ride.setOnClickListener(this);

        confirm = (Button)findViewById(R.id.confirm_main);
        confirm.setOnClickListener(this);

        home = (Button)findViewById(R.id.home);
        home.setOnClickListener(this);

        work = (Button)findViewById(R.id.work);
        work.setOnClickListener(this);

        marathahalli = (Button)findViewById(R.id.marathahalli);
        marathahalli.setOnClickListener(this);

        kadubisnahalli = (Button)findViewById(R.id.kadubisnahalli);
        kadubisnahalli.setOnClickListener(this);

        extra = (Button)findViewById(R.id.extra);
        extra.setOnClickListener(this);

        confirm0 = (CardView)findViewById(R.id.confirm00);
        confirm1 = (CardView)findViewById(R.id.confirm01);
        confirm2 = (CardView)findViewById(R.id.confirm10);
        confirm3 = (CardView)findViewById(R.id.confirm11);

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

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
                case R.id.work:
                    destination.setText(work.getText());
                    hideFrame(fav_dest);
                    showFrame(vehicle_list);
                    break;
                case R.id.home:
                    destination.setText(home.getText());
                    hideFrame(fav_dest);
                    showFrame(vehicle_list);
                    break;
                case R.id.marathahalli:
                    destination.setText(marathahalli.getText());
                    hideFrame(fav_dest);
                    showFrame(vehicle_list);
                    break;
                case R.id.kadubisnahalli:
                    destination.setText(kadubisnahalli.getText());
                    hideFrame(fav_dest);
                    showFrame(vehicle_list);
                    break;
                case R.id.extra:
                    destination.setText(extra.getText());
                    hideFrame(fav_dest);
                    showFrame(vehicle_list);
                    break;
                case R.id.bike :
                    selectVehicle(bike);
                    break;
                case R.id.car :
                    selectVehicle(car);
                    break;
                case R.id.shareCar :
                    selectVehicle(shareCar);
                    break;
                case R.id.auto :
                    selectVehicle(auto);
                    break;
                case R.id.shareAuto :
                    selectVehicle(shareAuto);
                    break;
                case R.id.rickshaw :
                    selectVehicle(rickshaw);
                    break;
                case R.id.shareRickshaw :
                    selectVehicle(shareRickshaw);
                    break;
                case R.id.ride_button_main:
                    Log.i("ride button","ride button clicked");
                    hideFrame(vehicle_list);
                    showFrame(button);
                    break;
                case R.id.confirm_main:
                    hideFrame(button);
                    Toast.makeText(this, "Show Driver Information", Toast.LENGTH_SHORT).show();
                    break;
            }
    }

    private void selectVehicle(Button button){
        bike.setEnabled(true);
        car.setEnabled(true);
        shareCar.setEnabled(true);
        auto.setEnabled(true);
        shareAuto.setEnabled(true);
        rickshaw.setEnabled(true);
        shareRickshaw.setEnabled(true);
        button.setEnabled(false);
        ride.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onStart() {
        super.onStart();
        showFrame(fav_dest);
        hideFrame(button);
        hideFrame(vehicle_list);
        ride.setVisibility(View.GONE);

        int width = displayMetrics.widthPixels/2;
        confirm0.setMinimumWidth(width);
        confirm1.setMinimumWidth(width);
        confirm2.setMinimumWidth(width);
        confirm3.setMinimumWidth(width);
    }

    private void showFrame(FrameLayout frameLayout){
        frameLayout.setVisibility(View.VISIBLE);
    }

    private void hideFrame(FrameLayout frameLayout){
        frameLayout.setVisibility(View.GONE);
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
        } else if (id == R.id.nav_notif) {
            Intent notif = new Intent(MainActivity.this,NotifActivity.class);
            startActivity(notif);
        } else if (id == R.id.nav_offers) {
            Intent offers = new Intent(MainActivity.this,OffersActivity.class);
            startActivity(offers);
        } else if (id == R.id.nav_about) {
            Intent offers = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(offers);
        } else if (id == R.id.nav_terms_cond) {
            Intent terms = new Intent(MainActivity.this,TermsActivity.class);
            startActivity(terms);
        }  else if (id == R.id.nav_share) {
            ApplicationInfo app = getApplicationContext().getApplicationInfo();
            String filePath = app.sourceDir;
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/vnd.android.package-archive");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
            startActivity(Intent.createChooser(intent, "Share app using :"));

        } else if (id == R.id.nav_logout) {
            if (getIntent().getStringExtra("phone") !=  null){
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
        }else if (id == R.id.nav_home){
            Intent home = new Intent(MainActivity.this, MainActivity.class);
            startActivity(home);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

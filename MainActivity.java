package com.apkglobal.tiffin7;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageViewbreakfast = (ImageView) findViewById(R.id.imageViewBreakfast);

        imageViewbreakfast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Breakfast is yet to come", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, BreakfastActivity.class));

            }
        });


        ImageView imageViewlunch = (ImageView) findViewById(R.id.imageViewLunch);

        imageViewlunch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Lunch is yet to come", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, LunchActivity.class));
            }
        });

        ImageView imageViewdinner = (ImageView) findViewById(R.id.imageViewDinner);

        imageViewdinner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Dinner Plans yet to come", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, DinnerActivity.class));
            }
        });


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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_promises) {
            Intent promises = new Intent(getApplicationContext(), PromisesActivity.class);
            startActivity(promises);

        } else if (id == R.id.nav_services) {
            Intent services = new Intent(getApplicationContext(), ServicesActivity.class);
            startActivity(services);

        } else if (id == R.id.nav_account) {
            Intent account = new Intent(getApplicationContext(), AccountActivity.class);
            startActivity(account);


        } else if (id == R.id.nav_share) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.putExtra(Intent.EXTRA_TEXT, "Download this app for exciting opportunities/n" + "https://play.google.com/store/apps/details?id=com.apkglobal.3&hl=en");
            share.setType("text/plain");
            startActivity(Intent.createChooser(share, "Share App via:"));


        } else if (id == R.id.nav_email) {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"care@makemymeals.co.in"});
            email.putExtra(Intent.EXTRA_SUBJECT, "Query");
            email.putExtra(Intent.EXTRA_TEXT, "Please refer to this query of mine.");
            email.setType("email/rfc822");
            startActivity(Intent.createChooser(email, "Email to Send:"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
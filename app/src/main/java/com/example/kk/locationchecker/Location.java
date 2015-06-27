package com.example.kk.locationchecker;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Location extends ActionBarActivity {

    static final int LOCATION_SETTINGS_REQUEST = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOCATION_SETTINGS_REQUEST) {
            // user is back from location settings - check if location services are now enabled
            LocationManager locMan = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
            boolean location_on = false;

            try {
                location_on = locMan.isProviderEnabled(LocationManager.GPS_PROVIDER);
            } catch (Exception e) {}

            if (location_on) {
                startActivity(new Intent(Location.this, Swipies.class));
                finish();
            }
        }

    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationManager locMan = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        boolean location_on = false;

        try {
            location_on = locMan.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {}

        if (!location_on) {
            Button yesButton;
            setContentView(R.layout.activity_location);

            yesButton = (Button)findViewById(R.id.yes_button);

            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),1);
                }
            });

        } else {

            startActivity(new Intent(Location.this, Swipies.class));
            finish();
        }



    }

    public void checkLocationStatus() {
        LocationManager locMan = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        boolean location_on = false;

        try {
            location_on = locMan.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {}

        if (!location_on) {
            Button yesButton;
            setContentView(R.layout.activity_location);

            yesButton = (Button)findViewById(R.id.yes_button);

            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
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
}

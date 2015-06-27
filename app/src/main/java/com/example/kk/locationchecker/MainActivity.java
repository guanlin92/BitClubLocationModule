package com.example.kk.locationchecker;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler splashHandler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,Location.class));
            }
        };

        splashHandler.postDelayed(runnable, 5000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}

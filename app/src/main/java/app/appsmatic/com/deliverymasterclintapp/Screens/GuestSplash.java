package app.appsmatic.com.deliverymasterclintapp.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import java.util.logging.Handler;

import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;

public class GuestSplash extends AppCompatActivity {

    private FrameLayout splash;
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_splash);
        splash = (FrameLayout) findViewById(R.id.frame);


        //Set image language for Splash
        if (SaveSharedPreference.getLangId(this).equals("ar")) {
            splash.setBackgroundResource(R.drawable.guestwelcomescreenar);
        } else {
            splash.setBackgroundResource(R.drawable.guestwelcomescreenen);
        }

        Thread splasch_sc = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(2 * SPLASH_DISPLAY_LENGTH);

                    // After 5 seconds redirect to another intent
                    Intent i = new Intent(getBaseContext(),Home.class);
                    startActivity(i);
                    GuestSplash.this.finish();

                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        splasch_sc.start();








    }
 }

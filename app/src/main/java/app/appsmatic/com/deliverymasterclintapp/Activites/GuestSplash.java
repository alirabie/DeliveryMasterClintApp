package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

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
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }



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

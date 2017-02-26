package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Splash extends AppCompatActivity {

    private TextView signin,signup,guest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }




        //Check Logging Status
        if(!SaveSharedPreference.getOwnerId(Splash.this).isEmpty()){
            startActivity(new Intent(Splash.this, Home.class));
            Splash.this.finish();
        }



        //set app lang
        setLang(R.layout.activity_splash);


       //Setup Label's
        signin=(TextView)findViewById(R.id.tv_signin);
        signup=(TextView)findViewById(R.id.tv_signup);
        guest=(TextView)findViewById(R.id.tv_guest);


        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            signin.setBackgroundResource(R.drawable.ripple);
            signup.setBackgroundResource(R.drawable.ripple);
            guest.setBackgroundResource(R.drawable.ripple);
        }


        //animate Control panel
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.toptodown);
        final LinearLayout controalPanell=(LinearLayout)findViewById(R.id.c_panel);
        controalPanell.clearAnimation();
        controalPanell.setAnimation(anim);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), SignIn.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),SignUp.class));
            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),GuestSplash.class));
            }
        });


        Log.e("KEY", getAppID());


        Typeface face=Typeface.createFromAsset(getAssets(), "arabicfont.ttf");
        signin.setTypeface(face);
        signup.setTypeface(face);
        guest.setTypeface(face);










    }



    // Get App Id
    private String getAppID() {
        PackageInfo pi;
        try {
            pi = getPackageManager().getPackageInfo(getPackageName(), 0);
            return pi.packageName;
        } catch (final PackageManager.NameNotFoundException e) {
            return "na";
        }
    }



    // Change language method
    public void setLang(int layout){
        String languageToLoad =SaveSharedPreference.getLangId(this);
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(layout);
    }

}

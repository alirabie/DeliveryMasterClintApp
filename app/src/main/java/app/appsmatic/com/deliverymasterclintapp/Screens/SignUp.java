package app.appsmatic.com.deliverymasterclintapp.Screens;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Locale;

import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;

public class SignUp extends AppCompatActivity {

    private ImageView logo,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setLang(R.layout.activity_sign_up);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }


        logo=(ImageView)findViewById(R.id.signup_logo);
        signup=(ImageView)findViewById(R.id.signup_btn);

        //Set image language for logo and Signin button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            logo.setImageResource(R.drawable.logoarabic);
            signup.setImageResource(R.drawable.signupbuttonarabic);
        }else{
            logo.setImageResource(R.drawable.logo);
            signup.setImageResource(R.drawable.signupbtnen);
        }

        //Animate Signup Layout
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.toptodown);
        LinearLayout loginpanel=(LinearLayout)findViewById(R.id.signuplayout);
        loginpanel.clearAnimation();
        loginpanel.setAnimation(anim);














    }



    // Change language method
    public void setLang(int layout){
        String languageToLoad = SaveSharedPreference.getLangId(this);
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(layout);
    }

}

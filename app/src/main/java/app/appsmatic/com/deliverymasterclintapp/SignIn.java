package app.appsmatic.com.deliverymasterclintapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Locale;

public class SignIn extends AppCompatActivity {

    private ImageView logo,signinbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setLang(R.layout.activity_sign_in);

        logo=(ImageView)findViewById(R.id.logo);
        signinbtn=(ImageView)findViewById(R.id.loginbtn);


        //Set image language for logo and login button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            logo.setImageResource(R.drawable.logoarabic);
            signinbtn.setImageResource(R.drawable.signinbtnar);
        }else{
            logo.setImageResource(R.drawable.logo);
            signinbtn.setImageResource(R.drawable.signinbtn);
        }

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.toptodown);
        LinearLayout loginpanel=(LinearLayout)findViewById(R.id.loginlayout);
        loginpanel.clearAnimation();
        loginpanel.setAnimation(anim);








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

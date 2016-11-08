package app.appsmatic.com.deliverymasterclintapp.Screens;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;

public class SignIn extends AppCompatActivity {

    private ImageView logo,signinbtn;
    private EditText phonenum,password;
    private TextView forgetpass,signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setLang(R.layout.activity_sign_in);

        forgetpass=(TextView)findViewById(R.id.tv_forgetpass);
        signup=(TextView)findViewById(R.id.tv_newaccount);
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























        //Forgot password button
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),ForgetpasswordS1.class));
            }
        });

        //SignUp button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),SignUp.class));
            }
        });






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

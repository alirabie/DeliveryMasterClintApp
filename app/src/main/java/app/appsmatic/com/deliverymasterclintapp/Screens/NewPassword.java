package app.appsmatic.com.deliverymasterclintapp.Screens;

import android.content.Intent;
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
import android.widget.ImageView;

import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;

public class NewPassword extends AppCompatActivity {

    private ImageView logo,resetpassbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logo=(ImageView)findViewById(R.id.forgetpassword_logo);
        resetpassbtn=(ImageView)findViewById(R.id.resetpassbtn);

        //Set image language for logo and login button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            logo.setImageResource(R.drawable.logoarabic);
            resetpassbtn.setImageResource(R.drawable.newpasswordbtnar);
        }else{
            logo.setImageResource(R.drawable.logo);
            resetpassbtn.setImageResource(R.drawable.newpasswordbtnen);
        }


        resetpassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),SignIn.class));
            }
        });


    }

}

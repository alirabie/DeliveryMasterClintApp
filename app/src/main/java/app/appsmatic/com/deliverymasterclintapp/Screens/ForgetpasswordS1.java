package app.appsmatic.com.deliverymasterclintapp.Screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;

public class ForgetpasswordS1 extends AppCompatActivity {

    private ImageView logo,contbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword_s1);
        logo=(ImageView)findViewById(R.id.forgetpassword_logo);
        contbtn=(ImageView)findViewById(R.id.forgetpassword_cont_1);

        //Set image language for logo and login button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            logo.setImageResource(R.drawable.logoarabic);
            contbtn.setImageResource(R.drawable.continuebtnarabic);
        }else{
            logo.setImageResource(R.drawable.logo);
            contbtn.setImageResource(R.drawable.continuebtnenglish);
        }

        contbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SecurityCode.class));
            }
        });
    }
}

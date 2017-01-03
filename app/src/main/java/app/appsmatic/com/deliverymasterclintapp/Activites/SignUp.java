package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Locale;
import app.appsmatic.com.deliverymasterclintapp.API.Models.Msg;
import app.appsmatic.com.deliverymasterclintapp.API.Models.RegistrationData;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    private ImageView logo,signup;
    private RegistrationData registrationData;
    private EditText
            fname,
            lname,
            password,
            phonenum;
    private ImageView signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setLang(R.layout.activity_sign_up);

        //SetUp Items
        registrationData =new RegistrationData();
        fname=(EditText)findViewById(R.id.signup_f_name);
        lname=(EditText)findViewById(R.id.signup_l_name);
        password=(EditText)findViewById(R.id.signup_password_input);
        phonenum=(EditText)findViewById(R.id.signup_phone_num);
        signupbtn=(ImageView)findViewById(R.id.signup_btn);
        logo=(ImageView)findViewById(R.id.signup_logo);
        signup=(ImageView)findViewById(R.id.signup_btn);


        //Setup Status Bar for OS
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }




        //Set image language for logo and Sign in button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            logo.setImageResource(R.drawable.logoarabic);
            signup.setImageResource(R.drawable.signupbuttonarabic);
        }else{
            logo.setImageResource(R.drawable.logo);
            signup.setImageResource(R.drawable.signupbtnen);
        }


        //Animate Sign up Layout
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.toptodown);
        LinearLayout loginpanel=(LinearLayout)findViewById(R.id.signuplayout);
        loginpanel.clearAnimation();
        loginpanel.setAnimation(anim);



        //Sign Up Button Action
        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            signupbtn.setBackgroundResource(R.drawable.ripple);
        }
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if there is Empty filed
                if(fname.getText().toString().isEmpty()||
                        lname.getText().toString().isEmpty()||
                        phonenum.getText().toString().isEmpty()||
                        password.getText().toString().isEmpty()){

                    //Alert Dialog for indicate that there is Empty Filed
                    final AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                    builder.setMessage(R.string.dontleavefildes)
                            .setCancelable(false)
                            .setIcon(R.drawable.erroricon)
                            .setTitle(R.string.ErrorDialog)
                            .setPositiveButton(R.string.Dissmiss, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();


                }else {
                    //Loading Dialog
                    final ProgressDialog mProgressDialog = new ProgressDialog(SignUp.this,R.style.AppCompatAlertDialogStyle);
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setIcon(R.drawable.loadicon);
                    mProgressDialog.setTitle(R.string.loadingdialog);
                    mProgressDialog.setMessage(Html.fromHtml("<font color=#FFFFFF><big>Loading ...</big></font>"));
                    mProgressDialog.show();

                    //Post Data To Server

                    registrationData.setFirstName(fname.getText().toString() + "");
                    registrationData.setLastName(lname.getText().toString() + "");
                    registrationData.setMobileNo(phonenum.getText().toString() + "");
                    registrationData.setNewPassword(password.getText().toString()+"");
                    registrationData.setAccountType("2");
                    registrationData.setRestaurantID("11");


                    Genrator.createService(ClintAppApi.class).SignUp(registrationData).enqueue(new Callback<Msg>() {
                        @Override
                        public void onResponse(Call<Msg> call, Response<Msg> response) {


                            if (response.isSuccessful()) {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                String code=response.body().getCode()+"";


                                //Check If Post Success Or Not
                                if (!code.equals("0")) {
                                    SignUp.this.finish();
                                    Toast.makeText(getApplicationContext(), R.string.RegSucusess, Toast.LENGTH_LONG).show();
                                } else {
                                    final AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                    builder.setMessage(response.body().getMessage() + "")
                                            .setIcon(R.drawable.erroricon)
                                            .setCancelable(false)
                                            .setTitle(R.string.sysMsg)
                                            .setPositiveButton(R.string.Dissmiss, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }

                            } else {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                                final AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                builder.setMessage(R.string.Responsenotsucusess)
                                        .setCancelable(false)
                                        .setIcon(R.drawable.erroricon)
                                        .setTitle(R.string.communicationerorr)
                                        .setPositiveButton(R.string.Dissmiss, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.dismiss();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();

                            }

                        }

                        @Override
                        public void onFailure(Call<Msg> call, Throwable t) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            final AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                            builder.setMessage(t.getMessage().toString()+"")
                                    .setCancelable(false)
                                    .setIcon(R.drawable.erroricon)
                                    .setTitle(R.string.connectionerorr)
                                    .setPositiveButton(R.string.Dissmiss, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.dismiss();
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }
                    });


                }



            }
        });






















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

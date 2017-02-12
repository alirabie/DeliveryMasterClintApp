package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import app.appsmatic.com.deliverymasterclintapp.API.Models.LoginData;
import app.appsmatic.com.deliverymasterclintapp.API.Models.Msg;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogLogin extends AppCompatActivity {

    private ImageView logo,signinbtn;
    private EditText phonenum,password;
    private TextView forgetpass,signup;
    private LoginData loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.toptodown, R.anim.alpha);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog_login);

        loginData=new LoginData();
        phonenum=(EditText)findViewById(R.id.dialog_phonenum_login);
        password=(EditText)findViewById(R.id.dialog_password_login);
        signinbtn=(ImageView)findViewById(R.id.dialog_loginbtn);
        forgetpass=(TextView)findViewById(R.id.dialog_tv_forgetpass);
        signup=(TextView)findViewById(R.id.dialog_tv_newaccount);

        //Set image language for logo and login button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            signinbtn.setImageResource(R.drawable.signinbuttonnar);
        }else{
            signinbtn.setImageResource(R.drawable.signinbtn);
        }


        //Sign in button Action
        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            signinbtn.setBackgroundResource(R.drawable.ripple);
            forgetpass.setBackgroundResource(R.drawable.ripple);
            signup.setBackgroundResource(R.drawable.ripple);
        }


        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if there is Empty Filed
                if(phonenum.getText().toString().equals("")||password.getText().toString().equals("")){

                    final AlertDialog.Builder builder = new AlertDialog.Builder(DialogLogin.this);
                    builder.setMessage(R.string.dontleavefildes)
                            .setCancelable(false)
                            .setIcon(R.drawable.erroricon)
                            .setTitle(R.string.sysMsg)
                            .setPositiveButton(R.string.Dissmiss, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();


                }else{



                    //Loading Dialog
                    final ProgressDialog mProgressDialog = new ProgressDialog(DialogLogin.this,R.style.AppCompatAlertDialogStyle);
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setIcon(R.drawable.loadicon);
                    mProgressDialog.setTitle(R.string.loadingdialog);
                    mProgressDialog.setMessage(Html.fromHtml("<font color=#FFFFFF><big>Loading ...</big></font>"));
                    mProgressDialog.show();

                    //Post Data Object Json to server
                    loginData.setUserName(phonenum.getText().toString() + "");
                    loginData.setPassword(password.getText().toString() + "");

                    Genrator.createService(ClintAppApi.class).Login(loginData).enqueue(new Callback<Msg>() {
                        @Override
                        public void onResponse(Call<Msg> call, Response<Msg> response) {


                            //If Server response successfully move to home Activity
                            if(response.isSuccessful()){

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                                //Check if Message response have String Success move to Home Activity and send user ID
                                String code=response.body().getCode()+"";
                                if(!code.equals("0")){

                                    String fulltext=response.body().getUserid()+"";
                                    SaveSharedPreference.setOwnerId(DialogLogin.this,fulltext.substring(fulltext.indexOf(",")+1, fulltext.length()));
                                    Toast.makeText(DialogLogin.this,"OwnerId : "+SaveSharedPreference.getOwnerId(DialogLogin.this)+"",Toast.LENGTH_LONG).show();
                                    Home.logoutbtn.setVisibility(View.VISIBLE);
                                    DialogLogin.this.finish();

                                }else{



                                    if (mProgressDialog.isShowing())
                                        mProgressDialog.dismiss();
                                    final AlertDialog.Builder builder = new AlertDialog.Builder(DialogLogin.this);
                                    builder.setMessage(response.body().getMessage()+"")
                                            .setCancelable(false)
                                            .setIcon(R.drawable.erroricon)
                                            .setTitle(R.string.sysMsg)
                                            .setPositiveButton(R.string.Dissmiss, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();


                                }


                            }else{

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();


                                final AlertDialog.Builder builder = new AlertDialog.Builder(DialogLogin.this);
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
                            final AlertDialog.Builder builder = new AlertDialog.Builder(DialogLogin.this);
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

}

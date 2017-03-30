package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResOrderConfirmation;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResPickupTime;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.Adabters.ConfirmOrdersAdb;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import app.appsmatic.com.deliverymasterclintapp.Tools.ResturantId;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Confirmation extends AppCompatActivity {

    private TextView timetxt;
    private int  mHour, mMinute;
    static SimpleDateFormat sdf;
    private static String locationId;
    private int serviceType;
    private EditText comments;
    private ImageView confirmBtn;
    private RecyclerView orderList;
    private TextView total;
    private ConfirmOrdersAdb confirmOrdersAdb;
    private TextView toolbartitle;
    private Date d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_confirmation);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }



        locationId=getIntent().getStringExtra("locationId");
        serviceType=getIntent().getIntExtra("servicetype", 0);

        timetxt=(TextView)findViewById(R.id.time_tv);
        timetxt.setText("00:00:00");
        comments=(EditText)findViewById(R.id.confirm_comment);
        confirmBtn=(ImageView)findViewById(R.id.confirmbtn);
        orderList=(RecyclerView)findViewById(R.id.confirmorderlist);
        total=(TextView)findViewById(R.id.totprice);
        toolbartitle=(TextView)findViewById(R.id.toptit);

        //touche hide keyboard
        comments.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        //Get pickUp Time
        if(serviceType==1) {
            HashMap data = new HashMap();
            data.put("restaurantid", ResturantId.resId);
            data.put("cartid", SaveSharedPreference.getCartId(Confirmation.this));
            data.put("servicetype", serviceType);
            data.put("locationid", locationId);
            Genrator.createService(ClintAppApi.class).getPickUpTime(data).enqueue(new Callback<ResPickupTime>() {
                @Override
                public void onResponse(Call<ResPickupTime> call, Response<ResPickupTime> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() != 0) {

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            try {
                                d = sdf.parse(response.body().getMessage()+"");
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Calendar serverdate = Calendar.getInstance();
                            serverdate.setTime(d);
                            String am_pm2 = "";
                            if (serverdate.get(Calendar.AM_PM) == Calendar.AM)
                                am_pm2 = "AM";
                            else if (serverdate.get(Calendar.AM_PM) == Calendar.PM)
                                am_pm2 = "PM";
                            String strHrsToShow2 = (serverdate.get(Calendar.HOUR) == 0) ? "12" : serverdate.get(Calendar.HOUR) + "";
                            timetxt.setText(strHrsToShow2 + ":" + serverdate.get(Calendar.MINUTE) + " " + am_pm2);

                        } else {
                            timetxt.setText("Error 0 from Time");
                        }
                    } else {
                        timetxt.setText("Time No response");
                    }
                }

                @Override
                public void onFailure(Call<ResPickupTime> call, Throwable t) {
                    timetxt.setText("Connection Error Time");
                }
            });

            //Delivery service Time
        }else if(serviceType==2){




        }












        Typeface face=Typeface.createFromAsset(getAssets(), "arabicfont.ttf");
        toolbartitle.setTypeface(face);
        toolbartitle.setText(R.string.toptitleconfirom);

        sdf = new SimpleDateFormat("hh:mm aa");
        timetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                if(serviceType==1) {
                    c.setTime(d);
                }

                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(Confirmation.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String am_pm = "";
                                Calendar datetime = Calendar.getInstance();
                                datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                datetime.set(Calendar.MINUTE, minute);


                                if(datetime.before(c)){
                                   timetxt.setText(getResources().getString(R.string.timeerorr));
                                }else {
                                    if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                                        am_pm = "AM";
                                    else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                                        am_pm = "PM";
                                    String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime.get(Calendar.HOUR) + "";
                                    timetxt.setText(strHrsToShow + ":" + datetime.get(Calendar.MINUTE) + " " + am_pm);
                                    Log.e("t/est ", timetxt.getText() + " " + locationId + " " + SaveSharedPreference.getOwnerId(getApplicationContext()) + "  " + SaveSharedPreference.getCartId(getApplicationContext()));
                                }
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });




        confirmOrdersAdb=new ConfirmOrdersAdb(Home.cartMeals, getApplicationContext());
        orderList.setAdapter(confirmOrdersAdb);
        orderList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        total.setText(confirmOrdersAdb.getTotalAll() + " "+getResources().getString(R.string.rs));


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Loading Dialog
                final ProgressDialog mProgressDialog = new ProgressDialog(Confirmation.this);
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setMessage(Html.fromHtml("<font color=#00000><big>"+getApplicationContext().getResources().getString(R.string.conf)+"</big></font>"));
                mProgressDialog.show();



                if (timetxt.getText().toString().equals(getResources().getString(R.string.timeerorr))) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Confirmation.this);
                    builder.setMessage(R.string.selecttime)
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

                } else {

                    //Invoke Send order to server method
                    Home.sendOrderToServer(Confirmation.this);

                    //wait 2 seconds while order sending to server
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // this code will be executed after 2 seconds
                            HashMap data = new HashMap();
                            data.put("restaurantid", ResturantId.resId);
                            data.put("owner", SaveSharedPreference.getOwnerId(getApplicationContext()));
                            data.put("cartid", SaveSharedPreference.getCartId(getApplicationContext()));
                            data.put("servicetype", serviceType);
                            data.put("comment", comments.getText() + "");
                            data.put("locationid", locationId);
                            data.put("timetorecieve", timetxt.getText() + "");

                            Log.e("Confrm :",data.toString());
                            Genrator.createService(ClintAppApi.class).confirmOrder(data).enqueue(new Callback<ResOrderConfirmation>() {
                                @Override
                                public void onResponse(Call<ResOrderConfirmation> call, Response<ResOrderConfirmation> response) {

                                    if (response.isSuccessful()) {
                                        if (mProgressDialog.isShowing())
                                            mProgressDialog.dismiss();
                                        if (response.body().getCode() != 0) {
                                            Toast.makeText(getApplicationContext(), response.body().getMessage() + getApplicationContext().getResources().getString(R.string.confirmdone), Toast.LENGTH_LONG).show();
                                            Home.cartMeals.clear();
                                            Home.icon = (LayerDrawable) Home.itemCart.getIcon();
                                            Home.setBadgeCount(getBaseContext(), Home.icon, "");
                                            Home.setBadgeCount(getBaseContext(), Home.icon, Home.cartMeals.size() + "");
                                            SaveSharedPreference.setCartId(getApplicationContext(), "");
                                            SaveSharedPreference.setCartOrders(Confirmation.this, Home.cartMeals);
                                            Confirmation.this.finish();


                                        } else {
                                            if (mProgressDialog.isShowing())
                                                mProgressDialog.dismiss();
                                            final AlertDialog.Builder builder = new AlertDialog.Builder(Confirmation.this);
                                            builder.setMessage(response.body().getMessage() + "")
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


                                    } else {

                                        if (mProgressDialog.isShowing())
                                            mProgressDialog.dismiss();
                                        final AlertDialog.Builder builder = new AlertDialog.Builder(Confirmation.this);
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
                                public void onFailure(Call<ResOrderConfirmation> call, Throwable t) {
                                    if (mProgressDialog.isShowing())
                                        mProgressDialog.dismiss();
                                    final AlertDialog.Builder builder = new AlertDialog.Builder(Confirmation.this);
                                    builder.setMessage(t.getMessage().toString() + "")
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
                    }, 10000);

                }
            }
        });


        //Set image language for confirm button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            confirmBtn.setImageResource(R.drawable.confirmbtnar);
        }else{
            confirmBtn.setImageResource(R.drawable.confirmbtnen);
        }

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}



















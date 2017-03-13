package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResOrderConfirmation;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        Typeface face=Typeface.createFromAsset(getAssets(), "arabicfont.ttf");
        toolbartitle.setTypeface(face);
        toolbartitle.setText(R.string.toptitleconfirom);

        sdf = new SimpleDateFormat("hh:mm aa");
        timetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
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
                                if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                                    am_pm = "AM";
                                else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                                    am_pm = "PM";
                                String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime.get(Calendar.HOUR) + "";
                                timetxt.setText(strHrsToShow + ":" + datetime.get(Calendar.MINUTE) + " " + am_pm);
                                Log.e("t/est ", timetxt.getText() + " " + locationId + " " + SaveSharedPreference.getOwnerId(getApplicationContext()) + "  " + SaveSharedPreference.getCartId(getApplicationContext()));
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });




        confirmOrdersAdb=new ConfirmOrdersAdb(Home.cartMeals, getApplicationContext());
        orderList.setAdapter(confirmOrdersAdb);
        orderList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        total.setText(confirmOrdersAdb.getTotalAll() + " SR");




        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Loading Dialog
                final ProgressDialog mProgressDialog = new ProgressDialog(Confirmation.this,R.style.AppCompatAlertDialogStyle);
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setIcon(R.drawable.loadicon);
                mProgressDialog.setTitle(R.string.loadingdialog);
                mProgressDialog.setMessage(Html.fromHtml("<font color=#FFFFFF><big>Loading ...</big></font>"));
                mProgressDialog.show();

                if(timetxt.getText().toString().equals("00:00:00")){
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

                }else {

                    HashMap data=new HashMap();
                    data.put("restaurantid", ResturantId.resId);
                    data.put("owner",SaveSharedPreference.getOwnerId(getApplicationContext()));
                    data.put("cartid", SaveSharedPreference.getCartId(getApplicationContext()));
                    data.put("servicetype", serviceType);
                    data.put("comment", comments.getText() + "");
                    data.put("locationid", locationId);
                    data.put("timetorecieve", timetxt.getText() + "");


                    Genrator.createService(ClintAppApi.class).confirmOrder(data).enqueue(new Callback<ResOrderConfirmation>() {
                        @Override
                        public void onResponse(Call<ResOrderConfirmation> call, Response<ResOrderConfirmation> response) {

                            if(response.isSuccessful()){
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                                if(response.body().getCode()!=0){
                                    Toast.makeText(getApplicationContext(),response.body().getMessage()+getApplicationContext().getResources().getString(R.string.confirmdone),Toast.LENGTH_LONG).show();
                                    Home.cartMeals.clear();
                                    Home.icon = (LayerDrawable)Home.itemCart.getIcon();
                                    Home.setBadgeCount(getBaseContext(), Home.icon, "");
                                    Home.setBadgeCount(getBaseContext(), Home.icon, Home.cartMeals.size() + "");
                                    SaveSharedPreference.setCartId(getApplicationContext(), "");
                                    Confirmation.this.finish();


                                }else {
                                    if (mProgressDialog.isShowing())
                                        mProgressDialog.dismiss();
                                    final AlertDialog.Builder builder = new AlertDialog.Builder(Confirmation.this);
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


                            }else {

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





        //Set image language for confirm button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            confirmBtn.setImageResource(R.drawable.confirmbtnar);
        }else{
            confirmBtn.setImageResource(R.drawable.confirmbtnen);
        }

    }
}

package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import app.appsmatic.com.deliverymasterclintapp.Adabters.ConfirmOrdersAdb;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;

public class Confirmation extends AppCompatActivity {

    private TextView timetxt;
    private int  mHour, mMinute;
    static SimpleDateFormat sdf;
    private static String locationId;
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
        locationId=getIntent().getStringExtra("locationId");
        timetxt=(TextView)findViewById(R.id.time_tv);
        comments=(EditText)findViewById(R.id.confirm_comment);
        confirmBtn=(ImageView)findViewById(R.id.confirmbtn);
        orderList=(RecyclerView)findViewById(R.id.confirmorderlist);
        total=(TextView)findViewById(R.id.totprice);


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
        total.setText(confirmOrdersAdb.getTotalAll()+" SR");













    }
}

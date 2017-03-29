package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResOrderDetails;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.Adabters.OrderDetailsAdb;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import app.appsmatic.com.deliverymasterclintapp.Tools.ResturantId;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetails extends AppCompatActivity {

    private TextView orderNo,orderDate,totalAll;
    private RecyclerView currentordersList;
    private ImageView call,sms,reorder;
    private int orderNum=0;
    private String orderDateTv="";
    private String orderType="";
    private OrderDetailsAdb orderDetailsAdb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.toptodown, R.anim.alpha);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_current_order_details);

        //Setup view items
        orderNo=(TextView)findViewById(R.id.order_details_id);
        orderDate=(TextView)findViewById(R.id.order_details_date);
        call=(ImageView)findViewById(R.id.order_details_meal_call_btn);
        sms=(ImageView)findViewById(R.id.order_details_meal_sms_btn);
        reorder=(ImageView)findViewById(R.id.order_details_meal_reorderbtn);
        totalAll=(TextView)findViewById(R.id.order_details_tottal_all);

        //set buttons language
        if(SaveSharedPreference.getLangId(this).equals("ar")){

            call.setImageResource(R.drawable.calliconar);
            sms.setImageResource(R.drawable.smsiconar);
            reorder.setImageResource(R.drawable.reorderbtnar);

        }else{
            call.setImageResource(R.drawable.callicon);
            sms.setImageResource(R.drawable.smsicon);
            reorder.setImageResource(R.drawable.reorderbtn);
        }




        //hide all buttons
        call.setVisibility(View.INVISIBLE);
        sms.setVisibility(View.INVISIBLE);
        reorder.setVisibility(View.INVISIBLE);




        //Receive data from Adb
        orderDateTv= getIntent().getStringExtra("date");
        orderNum= getIntent().getIntExtra("id", 0);
        orderType=getIntent().getStringExtra("flag");
        orderNo.setText(getResources().getString(R.string.ordernoorderdetails)+orderNum + "");
        orderDate.setText(orderDateTv + "");



/*
        //Switch current order and previous orders buttons
        if(orderType.equals("c"))
        {
            call.setVisibility(View.VISIBLE);
            sms.setVisibility(View.VISIBLE);
            reorder.setVisibility(View.INVISIBLE);
        }else if(orderType.equals("p"))
        {
            call.setVisibility(View.INVISIBLE);
            sms.setVisibility(View.INVISIBLE);
            reorder.setVisibility(View.VISIBLE);
        }

*/


        HashMap data=new HashMap();
        data.put("orderid", orderNum);
        data.put("restaurantid", ResturantId.resId);
        Genrator.createService(ClintAppApi.class).getOrderDetails(data).enqueue(new Callback<ResOrderDetails>() {
            @Override
            public void onResponse(Call<ResOrderDetails> call, Response<ResOrderDetails> response) {

                if (response.isSuccessful()) {
                    if (response.body().getCode() != 0) {
                        orderDetailsAdb=new OrderDetailsAdb(response.body().getMessage(), OrderDetails.this);
                        currentordersList = (RecyclerView) findViewById(R.id.order_details_orders_list);
                        currentordersList.setAdapter(orderDetailsAdb);
                        currentordersList.setLayoutManager(new LinearLayoutManager(OrderDetails.this));
                        //Putting total order price
                        totalAll.setText(orderDetailsAdb.sumTotall()+" "+getResources().getString(R.string.rs));

                    } else {
                        Toast.makeText(OrderDetails.this, "Code 0 from Order Details", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(OrderDetails.this, "Response Not successful from Order Details !", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResOrderDetails> call, Throwable t) {

                Toast.makeText(OrderDetails.this, t.getMessage() + " Connection error from Order Details !", Toast.LENGTH_LONG).show();
            }
        });







    }

}

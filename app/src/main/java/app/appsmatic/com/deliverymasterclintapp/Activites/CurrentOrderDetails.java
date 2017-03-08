package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.appsmatic.com.deliverymasterclintapp.R;

public class CurrentOrderDetails extends AppCompatActivity {

    private TextView orderNo,orderDate;
    private RecyclerView currentordersList;
    private ImageView call,sms;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order_details);

        //Setup view items
        orderNo=(TextView)findViewById(R.id.current_order_no_tv);
        orderDate=(TextView)findViewById(R.id.current_order_date_tv);
        currentordersList=(RecyclerView)findViewById(R.id.current_order_list);
        call=(ImageView)findViewById(R.id.current_order_details_meal_call_btn);
        sms=(ImageView)findViewById(R.id.current_order_details_meal_sms_btn);



    }

}

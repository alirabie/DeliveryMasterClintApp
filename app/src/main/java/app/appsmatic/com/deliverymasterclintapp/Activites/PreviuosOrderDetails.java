package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.content.pm.ActivityInfo;
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

public class PreviuosOrderDetails extends AppCompatActivity {

    private TextView orderNo,orderDate;
    private RecyclerView currentordersList;
    private ImageView reorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_previuos_order_details);

        //Setup view items
        orderNo=(TextView)findViewById(R.id.prev_order_details_meal_number);
        orderDate=(TextView)findViewById(R.id.prev_order_details_meal_date);
        currentordersList=(RecyclerView)findViewById(R.id.prev_details_list_orders);
        reorder=(ImageView)findViewById(R.id.prev_order_details_meal_reorder_btn);


    }

}

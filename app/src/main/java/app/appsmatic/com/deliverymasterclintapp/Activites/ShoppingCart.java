package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;

public class ShoppingCart extends AppCompatActivity {

    private ImageView pickuBtn,deleviryBtn;
    private RecyclerView mealslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        pickuBtn=(ImageView)findViewById(R.id.cart_pickup_btn);
        deleviryBtn=(ImageView)findViewById(R.id.cart_delvery_btn);
        mealslist=(RecyclerView)findViewById(R.id.cart_meals_list);


        //Set image language for pickup and delivery button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
           pickuBtn.setImageResource(R.drawable.cart_pickup_btn_arabic);
           deleviryBtn.setImageResource(R.drawable.cart_delivery_btn_arabic);
        }else{
            pickuBtn.setImageResource(R.drawable.cart_picup_btn_english);
            deleviryBtn.setImageResource(R.drawable.cart_delivery_btn_english);
        }






    }
}

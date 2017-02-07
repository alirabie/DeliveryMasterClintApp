package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import app.appsmatic.com.deliverymasterclintapp.API.Models.CartData;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCreateCart;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.Adabters.CartAdb;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingCart extends AppCompatActivity {

    private ImageView pickuBtn,deleviryBtn;
    private RecyclerView mealslist;
    private CartData cartData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.toptodown);
        setContentView(R.layout.activity_shopping_cart);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }


        mealslist=(RecyclerView)findViewById(R.id.cart_meals_list);
        mealslist.setAdapter(new CartAdb(Home.cartMeals,this));
        mealslist.setLayoutManager(new LinearLayoutManager(this));























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


        //Pick Uo action button
        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            pickuBtn.setBackgroundResource(R.drawable.ripple);
            deleviryBtn.setBackgroundResource(R.drawable.ripple);
        }
        pickuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(ShoppingCart.this,PickUpService.class));

                //test create shopping cart and send all orders to server
                //Create Cart Place


                if(Home.ownerCode==null){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCart.this);
                    builder.setMessage("You Should Login before confirm order !!")
                            .setCancelable(false)
                            .setIcon(R.drawable.erroricon)
                            .setTitle(R.string.sysMsg)
                            .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                   startActivity(new Intent(ShoppingCart.this,SignIn.class));
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                }else {


                    //Create new cart
                    cartData=new CartData();
                    cartData.setOwner(Home.ownerCode);
                    cartData.setRestaurantid("11");
                    cartData.setSource("3");
                    Genrator.createService(ClintAppApi.class).cereateShoppingCart(cartData).enqueue(new Callback<ResCreateCart>() {
                        @Override
                        public void onResponse(Call<ResCreateCart> call, Response<ResCreateCart> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getCode().equals("0")) {
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    if (SaveSharedPreference.getCartId(getApplicationContext()).equals("")) {
                                        SaveSharedPreference.setCartId(getApplicationContext(), response.body().getMessage() + "");
                                        Toast.makeText(getApplicationContext(), "New Cart Created", Toast.LENGTH_SHORT).show();
                                    } else {

                                    }
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<ResCreateCart> call, Throwable t) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCart.this);
                            builder.setMessage(t.getMessage() + "")
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
                    });




                    //Send order to server
                    Home.serverCart.setCartid(SaveSharedPreference.getCartId(getApplicationContext()) + "");
                    Home.serverCart.setOrder(Home.cartMeals);
                    Genrator.createService(ClintAppApi.class).addtocart(Home.serverCart).enqueue(new Callback<ResCreateCart>() {
                        @Override
                        public void onResponse(Call<ResCreateCart> call, Response<ResCreateCart> response) {

                            //if response is successful
                            if (response.isSuccessful()) {

                                //if Orders failed to added
                                if(response.body().getCode()==0){

                                    final AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCart.this);
                                    builder.setMessage(response.body().getMessage()+"")
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
                                }else{
                                    //if Orders added successfully
                                    Toast.makeText(ShoppingCart.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                                }




                            }
                        }

                        @Override
                        public void onFailure(Call<ResCreateCart> call, Throwable t) {
                            Toast.makeText(ShoppingCart.this,t.getMessage() + "", Toast.LENGTH_LONG).show();
                        }
                    });

                    Gson gson = new Gson();
                    String dataJson=gson.toJson(Home.serverCart);
                    Log.e("dataJson : ", dataJson);


                }

//end








            }
        });


        //Delivery action button
        deleviryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShoppingCart.this,DeliveryService.class));
            }
        });





    }
}

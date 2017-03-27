package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import app.appsmatic.com.deliverymasterclintapp.API.Models.CartData;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCreateCart;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.Adabters.CartAdb;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import app.appsmatic.com.deliverymasterclintapp.Tools.ResturantId;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingCart extends AppCompatActivity {

    private ImageView pickuBtn,deleviryBtn;
    private RecyclerView mealslist;
    private CartData cartData;
    private TextView cartEmpty;
    public static TextView cartTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slidetop, R.anim.slidetop);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_shopping_cart);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }




        cartTotalPrice=(TextView)findViewById(R.id.cart_price_tv);
        cartEmpty=(TextView)findViewById(R.id.tv_cart_empty);
        cartEmpty.setVisibility(View.INVISIBLE);
        mealslist=(RecyclerView)findViewById(R.id.cart_meals_list);
        mealslist.setAdapter(new CartAdb(Home.cartMeals, ShoppingCart.this));
        mealslist.setLayoutManager(new LinearLayoutManager(this));


        // Show cart Empty TV if cart is empty
        if(Home.cartMeals.isEmpty()){
            cartEmpty.setVisibility(View.VISIBLE);
        }else {
            cartEmpty.setVisibility(View.INVISIBLE);
        }

        pickuBtn=(ImageView)findViewById(R.id.cart_pickup_btn);
        deleviryBtn=(ImageView)findViewById(R.id.cart_delvery_btn);
        mealslist=(RecyclerView)findViewById(R.id.cart_meals_list);


        // cart total price
        updateCartPrice(ShoppingCart.this);




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
                //test create shopping cart and send all orders to server
                //Create Cart Place
               //check if user logged in or guest
                //Check GPS status
                final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCart.this);
                    builder.setMessage(R.string.gpson)
                            .setCancelable(false)
                            .setPositiveButton(R.string.gogps, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Home.turnLocationOn(ShoppingCart.this);
                                }
                            }).setIcon(android.R.drawable.alert_light_frame);
                    AlertDialog alert = builder.create();
                    alert.show();

                } else if(SaveSharedPreference.getOwnerId(ShoppingCart.this).isEmpty()){
                    startActivity(new Intent(ShoppingCart.this,DialogLogin.class));
                }else {

                    // Check If Cart Is Empty Or not and inform user
                    if (Home.cartMeals.isEmpty()) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCart.this);
                        builder.setMessage(R.string.emptycartdialog)
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
                        //if cart is full
                        //Create new cart on server
                        cartData=new CartData();
                        cartData.setOwner(SaveSharedPreference.getOwnerId(ShoppingCart.this));
                        cartData.setRestaurantid(ResturantId.resId);
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
                                            Toast.makeText(getApplicationContext(),SaveSharedPreference.getCartId(getApplicationContext())+" :  Cart Id still", Toast.LENGTH_SHORT).show();
                                        }
                                    }


                                    startActivity(new Intent(ShoppingCart.this, PickUpService.class));
                                    finish();
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


                    }


                }


//end








            }
        });


        //Delivery action button
        deleviryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //test create shopping cart and send all orders to server
                //Create Cart Place
                //Check if user logged in or guest
                //Check GPS status
                final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCart.this);
                    builder.setMessage(R.string.gpson)
                            .setCancelable(false)
                            .setPositiveButton(R.string.gogps, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Home.turnLocationOn(ShoppingCart.this);
                                }
                            }).setIcon(android.R.drawable.alert_light_frame);
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if (SaveSharedPreference.getOwnerId(ShoppingCart.this).isEmpty()) {
                    startActivity(new Intent(ShoppingCart.this, DialogLogin.class));
                } else {

                    // Check If Cart Is Empty Or not and inform user
                    if (Home.cartMeals.isEmpty()) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCart.this);
                        builder.setMessage(R.string.emptycartdialog)
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
                        //if cart is full
                        //Create new cart on server
                        cartData = new CartData();
                        cartData.setOwner(SaveSharedPreference.getOwnerId(ShoppingCart.this));
                        cartData.setRestaurantid(ResturantId.resId);
                        cartData.setSource("3");
                        Genrator.createService(ClintAppApi.class).cereateShoppingCart(cartData).enqueue(new Callback<ResCreateCart>() {
                            @Override
                            public void onResponse(Call<ResCreateCart> call, Response<ResCreateCart> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getCode().equals("0")) {
                                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    } else {

                                        //Check if cart Id empty if that add new cart id if not empty inform user
                                        if (SaveSharedPreference.getCartId(getApplicationContext()).equals("")) {
                                            SaveSharedPreference.setCartId(getApplicationContext(), response.body().getMessage() + "");
                                            Toast.makeText(getApplicationContext(), "New Cart Created", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(),SaveSharedPreference.getCartId(getApplicationContext())+" :  Cart Id still", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    startActivity(new Intent(ShoppingCart.this, DeliveryService.class));
                                    finish();
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


                    }
                }
            }

            });





    }

    //update cart price
    public static void updateCartPrice(Context context){
        ShoppingCart.cartTotalPrice.setText(context.getResources().getString(R.string.totalpricecart)+" "+Home.calcCartPrice()+" "+context.getResources().getString(R.string.rs));
    }


}

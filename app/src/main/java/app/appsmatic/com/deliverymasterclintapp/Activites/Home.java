package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.Meal;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCreateCart;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ServerCartModel.Order;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ServerCartModel.ServerCart;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.CartMeal;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.CartOrders;
import app.appsmatic.com.deliverymasterclintapp.Fragments.CurrentOrder;
import app.appsmatic.com.deliverymasterclintapp.Fragments.FoodMenu;
import app.appsmatic.com.deliverymasterclintapp.Fragments.Info;
import app.appsmatic.com.deliverymasterclintapp.Fragments.MyAccount;
import app.appsmatic.com.deliverymasterclintapp.Fragments.PrevOrders;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.Fragments.Settings;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static int cartNumber=0;
    public static String userId="";
    private ImageView logoutbtn;
    private TextView toolbartitle;
    public static List<CartMeal>cartMeals=new ArrayList<>();
    public static ServerCart serverCart=new ServerCart();
    public static List<Order> orders=new ArrayList<>();

    private ImageView shoppingCart;

    //Fragments
    private FoodMenu foodMenu;
    private CurrentOrder currentOrder;
    private PrevOrders prevOrders;
    private MyAccount myAccount;
    private Settings settings;
    private Info info;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //Receive user id from login operation
        userId=getIntent().getStringExtra("UserId");



        //Add Start Fragment Food menu
        foodMenu=new FoodMenu();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcontener, foodMenu);
        fragmentTransaction.commit();




        //logout button implementation
        logoutbtn=(ImageView)findViewById(R.id.logoutbtn);
            //Set image language for logout button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            logoutbtn.setImageResource(R.drawable.logoutbtnar);
        }else{
           logoutbtn.setImageResource(R.drawable.logoutbtnen);
        }

        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            logoutbtn.setBackgroundResource(R.drawable.ripple);
        }
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home.this.finish();
                startActivity(new Intent(getApplicationContext(), SignIn.class));
            }
        });








        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbartitle=(TextView)findViewById(R.id.main_toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);





        //setup shopping Cart

        shoppingCart=(ImageView)findViewById(R.id.cartbtn);
        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            shoppingCart.setBackgroundResource(R.drawable.ripple);
        }
        shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Home.this,ShoppingCart.class));



                //test send all orders to server
                Home.serverCart.setCartid(SaveSharedPreference.getCartId(getApplicationContext()) + "");
                Home.serverCart.setOrder(Home.orders);
                Genrator.createService(ClintAppApi.class).addtocart(Home.serverCart).enqueue(new Callback<ResCreateCart>() {
                    @Override
                    public void onResponse(Call<ResCreateCart> call, Response<ResCreateCart> response) {
                        if (response.isSuccessful()) {
                            Log.e("Response", "Sucsesss  : " + response.body().getCode() + "");
                            Log.e("Response", response.body().getMessage() + "");
                        } else {

                            Log.e("Response", "Not Sucsesss");

                        }
                    }

                    @Override
                    public void onFailure(Call<ResCreateCart> call, Throwable t) {

                        Log.e("Response", t.getMessage());
                    }
                });


                Gson gson = new Gson();
                String dataJson=gson.toJson(Home.serverCart);
                Log.e("dataJson : ", dataJson);







                /*
                for(int i=0;i<Home.cartMeals.size();i++){
                    Log.e("Meal name : ",Home.cartMeals.get(i).getMealName()+"  Count :"+Home.cartMeals.get(i).getMealCount()+"");
                    for(int x=0;x<Home.cartMeals.get(i).getMealAdditions().size();x++){
                        Log.e("Addition Name :",Home.cartMeals.get(i).getMealAdditions().get(x).getAdditionName()+" Count :"+Home.cartMeals.get(i).getMealAdditions().get(x).getAddCount()+"");
                    }
                }
                */

            }
        });





    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myaccout) {
            myAccount=new MyAccount();
            toolbartitle.setText(R.string.item1myaccount);
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontener, myAccount);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_foodmun) {
            foodMenu=new FoodMenu();
            toolbartitle.setText(R.string.item2foodm);
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontener, foodMenu);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_corder) {
           currentOrder=new CurrentOrder();
            toolbartitle.setText(R.string.item3currntorder);
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontener, currentOrder);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_porder) {
            prevOrders=new PrevOrders();
            toolbartitle.setText(R.string.item4pervorders);
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontener, prevOrders);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_settings) {
            settings=new Settings();
            toolbartitle.setText(R.string.item5settngs);
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontener, settings);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_info) {
            info=new Info();
            toolbartitle.setText(R.string.item6info);
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontener, info);
            fragmentTransaction.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }














}

package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.readystatesoftware.viewbadger.BadgeView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ServerCartModel.ServerCart;
import app.appsmatic.com.deliverymasterclintapp.BadgeDrawable;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.CartMeal;
import app.appsmatic.com.deliverymasterclintapp.Fragments.CurrentOrder;
import app.appsmatic.com.deliverymasterclintapp.Fragments.FoodMenu;
import app.appsmatic.com.deliverymasterclintapp.Fragments.Info;
import app.appsmatic.com.deliverymasterclintapp.Fragments.MyAccount;
import app.appsmatic.com.deliverymasterclintapp.Fragments.PrevOrders;
import app.appsmatic.com.deliverymasterclintapp.Fragments.Search;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.Fragments.Settings;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static ImageView logoutbtn;
    private TextView toolbartitle;
    private CircleImageView userPhoto;
    private TextView userNameTv;
    public static List<CartMeal>cartMeals=new ArrayList<>();
    public static ServerCart serverCart=new ServerCart();
    public static BadgeView badgeView;
    public static LayerDrawable icon;
    public static MenuItem itemCart;
    public static  Context context;


    private ImageView shoppingCart;

    //Fragments
    private FoodMenu foodMenu;
    private CurrentOrder currentOrder;
    private PrevOrders prevOrders;
    private MyAccount myAccount;
    private Settings settings;
    private Info info;
    private Search search;




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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

        Log.e("OwnerId : ", SaveSharedPreference.getOwnerId(this));
        //Receive user id from login operation and store it in Shared prefs
        //SaveSharedPreference.setOwnerId(Home.this,getIntent().getStringExtra("UserId"));


        //Add Start Fragment Food menu
        foodMenu = new FoodMenu();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcontener, foodMenu);
        fragmentTransaction.commit();


        //logout button implementation

        logoutbtn = (ImageView) findViewById(R.id.logoutbtn);
        //Set image language for logout button
        if (SaveSharedPreference.getLangId(this).equals("ar")) {
            logoutbtn.setImageResource(R.drawable.logoutbtnar);
        } else {
            logoutbtn.setImageResource(R.drawable.logoutbtnen);
        }

        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            logoutbtn.setBackgroundResource(R.drawable.ripple);
        }


        if (SaveSharedPreference.getOwnerId(Home.this).isEmpty()) {
            logoutbtn.setVisibility(View.INVISIBLE);
        } else {
            logoutbtn.setVisibility(View.VISIBLE);
        }


        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home.this.finish();
                SaveSharedPreference.setOwnerId(Home.this, "");
                logoutbtn.setVisibility(View.INVISIBLE);
                startActivity(new Intent(getApplicationContext(), SignIn.class));
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbartitle = (TextView) findViewById(R.id.main_toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


        //Setup Side menu User Photo and User Name TV
        View hView = navigationView.getHeaderView(0);
        userPhoto = (CircleImageView) hView.findViewById(R.id.sidemenuicon);
        userNameTv = (TextView) hView.findViewById(R.id.user_name_tv);
        Picasso.with(getApplicationContext()).load(R.drawable.guesticon).fit().placeholder(R.drawable.guesticon).into(userPhoto);
        userNameTv.setText("Gust");


    }


    /* badgeView=new BadgeView(this,icon);
        badgeView.setText(cartMeals.size()+"");
        badgeView.show();

        if(cartMeals.isEmpty()){
            //don't show any numbers on cart icon
        }else {
            badgeView.setText(cartMeals.size()+"");
            badgeView.show();
        }
        */











    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);


           itemCart = menu.findItem(R.id.action_carticon);
           icon = (LayerDrawable) itemCart.getIcon();
        if(cartMeals.isEmpty()){
            //don't show any numbers on cart icon
        }else {
            setBadgeCount(getBaseContext(), icon, cartMeals.size() + "");
        }




        //Setup Search view listener >>>

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0) {

                    search = new Search();
                    Bundle args = new Bundle();
                    args.putString("query_string",query);
                    search.setArguments(args);
                    toolbartitle.setText(R.string.searchfrag);
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontener,search);
                    fragmentTransaction.commit();



                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0) {
                    search = new Search();
                    Bundle args = new Bundle();
                    args.putString("query_string",newText);
                    search.setArguments(args);
                    toolbartitle.setText(R.string.searchfrag);
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontener,search);
                    fragmentTransaction.commit();
                }
                return false;
            }
        });




        return true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        //Create cart icon meals count

        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_carticon:
                this.startActivity(new Intent(this, ShoppingCart.class));
                Gson gson = new Gson();
                String dataJson = gson.toJson(Home.serverCart);
                Log.e("dataJson : ", dataJson);
                break;
            // action with ID action_settings was selected
            case R.id.action_search:


                break;
            default: return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
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
            if(SaveSharedPreference.getOwnerId(Home.this).isEmpty()){
                startActivity(new Intent(Home.this,DialogLogin.class));
            }else {
                myAccount=new MyAccount();
                toolbartitle.setText(R.string.item1myaccount);
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, myAccount);
                fragmentTransaction.commit();
            }


        } else if (id == R.id.nav_foodmun) {
            foodMenu=new FoodMenu();
            toolbartitle.setText(R.string.item2foodm);
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontener, foodMenu);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_corder) {

            if(SaveSharedPreference.getOwnerId(Home.this).isEmpty()){
                startActivity(new Intent(Home.this,DialogLogin.class));
            }else {
                currentOrder=new CurrentOrder();
                toolbartitle.setText(R.string.item3currntorder);
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, currentOrder);
                fragmentTransaction.commit();
            }


        } else if (id == R.id.nav_porder) {
            if(SaveSharedPreference.getOwnerId(Home.this).isEmpty()){
                startActivity(new Intent(Home.this,DialogLogin.class));
            }else {
                prevOrders = new PrevOrders();
                toolbartitle.setText(R.string.item4pervorders);
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, prevOrders);
                fragmentTransaction.commit();
            }


        } else if (id == R.id.nav_settings) {
                settings = new Settings();
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






    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }


}












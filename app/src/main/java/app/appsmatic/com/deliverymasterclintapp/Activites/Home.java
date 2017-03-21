package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.Gson;
import com.readystatesoftware.viewbadger.BadgeView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCreateCart;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResProfileInfo;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ServerCartModel.ServerCart;
import app.appsmatic.com.deliverymasterclintapp.API.Models.UserProfile;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.Tools.BadgeDrawable;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static ImageView logoutbtn;
    private TextView toolbartitle;
    private CircleImageView userPhoto;
    public static TextView userNameTv;
    public static List<CartMeal>cartMeals=new ArrayList<>();
    public static ServerCart serverCart=new ServerCart();
    public static LayerDrawable icon;
    public static MenuItem itemCart;
    public static  Context context;
    public static UserProfile userProfile;



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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        userProfile=new UserProfile();
        setUserProfileInfo(Home.this);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //Check location permissions for Marshmallow
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
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


        //Animate tool bar
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_animation);
        toolbar.clearAnimation();
        toolbar.setAnimation(anim);



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


       if(SaveSharedPreference.getOwnerId(Home.this).equals("")){
           userNameTv.setText(R.string.gust);
       }

        Typeface face=Typeface.createFromAsset(getAssets(), "arabicfont.ttf");
        toolbartitle.setTypeface(face);
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







    //Set Cart Badge count
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




    //Turn GPS ON Method
    public static void turnLocationOn(final Context ctx){

        isGooglePlayServicesAvailable((Activity)ctx);

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(ctx)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i("TurnLocationOn", "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i("TurnLocationOn", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult((Activity) ctx, 0x1);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i("TurnLocationOn", "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("TurnLocationOn", "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }



    //Check Google Service
    public static boolean isGooglePlayServicesAvailable(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if(status != ConnectionResult.SUCCESS) {
            if(googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
            }
            return false;
        }
        return true;
    }


    //Send order to server method : if cart id still send updated order
    // to server and when user confirm order clear cart id and cart items
    public static void sendOrderToServer(final Context context){
        //Send order to server code
        Home.serverCart.setCartid(SaveSharedPreference.getCartId(context) + "");
        Home.serverCart.setOrder(Home.cartMeals);
        Genrator.createService(ClintAppApi.class).addtocart(Home.serverCart).enqueue(new Callback<ResCreateCart>() {
            @Override
            public void onResponse(Call<ResCreateCart> call, Response<ResCreateCart> response) {
                //if response is successful
                if (response.isSuccessful()) {
                    //if Orders failed to added
                    if (response.body().getCode() == 0) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage(response.body().getMessage() +"")
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
                    } else {
                        //if Orders added successfully
                        Toast.makeText(context, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                        //Clear Cart Id
                        // SaveSharedPreference.setCartId(context, "");
                    }
                } else {
                    Toast.makeText(context, "Response not success from sending order to server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResCreateCart> call, Throwable t) {
                Toast.makeText(context, t.getMessage() + "", Toast.LENGTH_LONG).show();
            }
        });
        Gson gson = new Gson();
        String dataJson=gson.toJson(Home.serverCart);
        Log.e("dataJson : ", dataJson);
    }





    // Set User Profile
    public static void setUserProfileInfo(final Context context) {
        if (SaveSharedPreference.getOwnerId(context).equals("")) {
            //Don't do any thing
        } else {
            HashMap data = new HashMap();
            data.put("owner", SaveSharedPreference.getOwnerId(context).toString());
            Genrator.createService(ClintAppApi.class).getProfileInfo(data).enqueue(new Callback<ResProfileInfo>() {
                @Override
                public void onResponse(Call<ResProfileInfo> call, Response<ResProfileInfo> response) {

                    if (response.isSuccessful()) {
                        if (response.body().getCode() != 0) {
                            userProfile.setFirstName(response.body().getMessage().get(0).getFirstName());
                            userProfile.setLastName(response.body().getMessage().get(0).getLastName());
                            userProfile.setMobileNo(response.body().getMessage().get(0).getMobileNo());
                            userNameTv.setText(userProfile.getFirstName() + " " + userProfile.getLastName());
                        } else {
                            Toast.makeText(context, "Code 0 from Profile", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(context, "Response not success from Profile", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResProfileInfo> call, Throwable t) {
                    Toast.makeText(context,t.getMessage()+ "  : Connection Error from profile !", Toast.LENGTH_LONG).show();
                }
            });
        }
    }



}

























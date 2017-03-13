package app.appsmatic.com.deliverymasterclintapp.Activites;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import app.appsmatic.com.deliverymasterclintapp.API.Models.NewLocaton;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResLocations;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResNewLocation;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.Adabters.BuranchesPickupAdb;
import app.appsmatic.com.deliverymasterclintapp.Adabters.DeliveryBrunchesAdb;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DeliveryService extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView deliverybtn;
    private EditText addressInput;
    private Marker marker;
    private RecyclerView locationsList;
    private LinearLayout userlocationsbox;
    private Double lat,lang;
    private EditText commentInput,streetAddressInput;
    private NewLocaton newLocaton;
    private TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_service);
        lat=0.0;
        lang=0.0;
        //Invoke Send order to server method
        Home.sendOrderToServer(DeliveryService.this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        deliverybtn=(ImageView)findViewById(R.id.delivery_btn);
        userlocationsbox=(LinearLayout)findViewById(R.id.locationsbox);
        commentInput=(EditText)findViewById(R.id.comments_input);
        addressInput=(EditText)findViewById(R.id.streetname_input);
        titleTv=(TextView)findViewById(R.id.deli_title);
        //put title font style
        Typeface face=Typeface.createFromAsset(getAssets(), "arabicfont.ttf");
        titleTv.setTypeface(face);



        ///Set image language for Delivery button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            deliverybtn.setImageResource(R.drawable.send_address_btn_ar);
        }else{
            deliverybtn.setImageResource(R.drawable.send_address_btn_en);
        }

        //set action for delivery button

        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            deliverybtn.setBackgroundResource(R.drawable.ripple);
        }


























        //User Delivery Locations List Setup

        HashMap data=new HashMap();
        data.put("userid",SaveSharedPreference.getOwnerId(DeliveryService.this));
        Genrator.createService(ClintAppApi.class).getDeleviryLocatons(data).enqueue(new Callback<ResLocations>() {
            @Override
            public void onResponse(Call<ResLocations> call, Response<ResLocations> response) {
                if(response.isSuccessful()){

                    if(response.body().getCode()==0){
                        Toast.makeText(getApplicationContext(),"Code from delivery locations is : "+response.body().getCode(),Toast.LENGTH_LONG).show();
                    }else {
                        if(response.body().getMessage().isEmpty()){
                            userlocationsbox.setVisibility(View.INVISIBLE);
                        }else {
                            userlocationsbox.setVisibility(View.VISIBLE);
                            locationsList=(RecyclerView)findViewById(R.id.deleviry_locatiions_list);
                            locationsList.setAdapter(new DeliveryBrunchesAdb(DeliveryService.this,response.body()));
                            locationsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            //put locations on map
                            for (int i = 0; i < response.body().getMessage().size(); i++) {
                                LatLng sydney = new LatLng(response.body().getMessage().get(i).getLatitude(), response.body().getMessage().get(i).getLongtitude());
                                mMap.addMarker(new MarkerOptions().position(sydney).title(response.body().getMessage().get(i).getBranchName()));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                                float zoomLevel = (float) 10.0; //This goes up to 21
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
                            }
                        }
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"response Not Success from get delivery Locations",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResLocations> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });





        //Delivery button action
        deliverybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lat == 0.0 & lang == 0.0) {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(DeliveryService.this);
                    builder.setMessage("Please Pick Your Location From Map ! ")
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

                    //Select new Location from map
                    HashMap data = new HashMap();
                    newLocaton = new NewLocaton();
                    newLocaton.setStreetAddress(addressInput.getText() + "");
                    newLocaton.setComment(commentInput.getText() + "");
                    newLocaton.setLongtitude(lang);
                    newLocaton.setLatitude(lat);
                    data.put("userid", SaveSharedPreference.getOwnerId(getApplicationContext()));
                    data.put("location", newLocaton);

                    Genrator.createService(ClintAppApi.class).addNewLocation(data).enqueue(new Callback<ResNewLocation>() {
                        @Override
                        public void onResponse(Call<ResNewLocation> call, Response<ResNewLocation> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getCode() == 0) {
                                    Toast.makeText(getApplicationContext(), "code 0 from delivery locations addition", Toast.LENGTH_LONG).show();

                                } else {

                                    startActivity(new Intent(DeliveryService.this, Confirmation.class)
                                            .putExtra("locationId", response.body().getMessage().getLocationID() + "")
                                            .putExtra("servicetype", 2));
                                    Toast.makeText(getApplicationContext(), "Your Location Id : " + response.body().getMessage().getLocationID() + "", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "no response from locations addition", Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResNewLocation> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });


                /*
                Gson gson = new Gson();
                String data=gson.toJson(sentNewLocation);
                Log.e("dataNewLocation : ", data);
                */


                }


            }



        });






    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (marker != null) {
                    marker.remove();
                }
                marker = mMap.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(latLng.latitude, latLng.longitude))
                        .draggable(true).visible(true).title("Your Place"));
                lat = latLng.latitude;
                lang = latLng.longitude;
                Toast.makeText(getApplicationContext(), lat + " " + lang + "", Toast.LENGTH_SHORT).show();
            }


        });


    }
}

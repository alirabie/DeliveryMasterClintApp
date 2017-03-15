package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.internal.overlay.zzo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;

public class LocationDetails extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView address,comment;
    private Double lat,lang;
    private ImageView pickupbtn;
    private String locationId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.toptodown, R.anim.alpha);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_location_details);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);





        address=(TextView)findViewById(R.id.dialog_location_details_street_address);
        comment=(TextView)findViewById(R.id.dialog_location_details_comment);
        pickupbtn=(ImageView)findViewById(R.id.pickup_btn);



        address.setText(getIntent().getStringExtra("locationName"));
        comment.setText(getIntent().getStringExtra("locationAddress") + "");
        locationId=getIntent().getStringExtra("locationId");
        lat=Double.parseDouble(getIntent().getStringExtra("lat"));
        lang=Double.parseDouble(getIntent().getStringExtra("long"));

        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            pickupbtn.setBackgroundResource(R.drawable.ripple);
        }
        //Set image language for pickup button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            pickupbtn.setImageResource(R.drawable.selectbranchbtn_ar);
        }else{
            pickupbtn.setImageResource(R.drawable.selectbranchbtn_en);
        }







        pickupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LocationDetails.this,"Location Id : "+locationId+" has been selected ",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LocationDetails.this, Confirmation.class).putExtra("locationId", locationId).putExtra("servicetype",1));
                finish();



              /*
              //Check if address input empty or not and inform user
               if(addressInput.getText().toString().isEmpty()){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(DeliveryService.this);
                    builder.setMessage(R.string.dontleavefildes)
                            .setCancelable(false)
                            .setIcon(R.drawable.erroricon)
                            .setTitle(R.string.ErrorDialog)
                            .setPositiveButton(R.string.Dissmiss, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                }else{

                    //do

                }
*/

/*
                //Send order to server code
                Home.serverCart.setCartid(SaveSharedPreference.getCartId(getApplicationContext()) + "");
                Home.serverCart.setOrder(Home.cartMeals);

                    Genrator.createService(ClintAppApi.class).addtocart(Home.serverCart).enqueue(new Callback<ResCreateCart>() {
                        @Override
                        public void onResponse(Call<ResCreateCart> call, Response<ResCreateCart> response) {

                            //if response is successful
                            if (response.isSuccessful()) {

                                //if Orders failed to added
                                if (response.body().getCode() == 0) {

                                    final AlertDialog.Builder builder = new AlertDialog.Builder(DeliveryService.this);
                                    builder.setMessage(response.body().getMessage() + "")
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
                                    Toast.makeText(DeliveryService.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                                    //Clear Cart Id
                                    SaveSharedPreference.setCartId(DeliveryService.this, "");
                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<ResCreateCart> call, Throwable t) {
                            Toast.makeText(DeliveryService.this, t.getMessage() + "", Toast.LENGTH_LONG).show();
                        }
                    });

                    Gson gson = new Gson();
                    String dataJson=gson.toJson(Home.serverCart);
                    Log.e("dataJson : ", dataJson);

                    */





            }
        });





















    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat,lang);
        mMap.addMarker(new MarkerOptions().position(sydney).title(address.getText()+""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        float zoomLevel = (float) 15.0; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
    }
}

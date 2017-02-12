package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCreateCart;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryService extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView deliverybtn;
    private EditText addressInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_service);
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
        addressInput=(EditText)findViewById(R.id.full_address_input);


        //Set image language for Delivery button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            deliverybtn.setImageResource(R.drawable.send_address_btn_ar);
        }else{
            deliverybtn.setImageResource(R.drawable.selectbranchbtn_en);
        }

        //set action for delivery button

        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            deliverybtn.setBackgroundResource(R.drawable.ripple);
        }


        //Delivery button action
        deliverybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                }






        });






    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        LatLng latLng = new LatLng(-33,155);
        mMap.addMarker(new MarkerOptions().position(latLng).title("egy"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        float zoomLevel = (float) 5.0; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
    }
}

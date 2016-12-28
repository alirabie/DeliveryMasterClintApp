package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;

public class DeliveryService extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView deliverybtn;
    private EditText addressInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_service);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        deliverybtn=(ImageView)findViewById(R.id.delivery_btn);
        addressInput=(EditText)findViewById(R.id.full_address_input);


        //Set image language for Delivery button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            deliverybtn.setImageResource(R.drawable.send_address_btn_ar);
        }else{
            deliverybtn.setImageResource(R.drawable.selectbranchbtn_en);
        }

        //set action for delivery button
        deliverybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do
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

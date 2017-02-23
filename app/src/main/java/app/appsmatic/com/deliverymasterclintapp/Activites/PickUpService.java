package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResLocations;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.Adabters.BuranchesPickupAdb;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import app.appsmatic.com.deliverymasterclintapp.Tools.ResturantId;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickUpService extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView pickupbtn;
    private RecyclerView brunchesList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitypickupserv);
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


        //get pickup branches :
        HashMap data=new HashMap();
        data.put("restaurantid", ResturantId.resId);
        Genrator.createService(ClintAppApi.class).getPicupBranches(data).enqueue(new Callback<ResLocations>() {
            @Override
            public void onResponse(Call<ResLocations> call, Response<ResLocations> response) {
                //if response success
                if (response.isSuccessful()) {
                    //if code from server not 0
                    if (!response.body().getCode().equals(0)) {
                        //if locations empty
                        if (response.body().getMessage().isEmpty()) {
                            //locations Empty
                        } else {

                            //setup locations list
                            brunchesList = (RecyclerView) findViewById(R.id.branches_list_pickup);
                            brunchesList.setAdapter(new BuranchesPickupAdb(getApplicationContext(), response.body()));
                            brunchesList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                            //put locations on map
                            for (int i = 0; i < response.body().getMessage().size(); i++) {
                                LatLng sydney = new LatLng(response.body().getMessage().get(i).getLatitude(), response.body().getMessage().get(i).getLongtitude());
                                mMap.addMarker(new MarkerOptions().position(sydney).title(response.body().getMessage().get(i).getBranchName()));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                                float zoomLevel = (float) 10.0; //This goes up to 21
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
                            }
                        }

                    } else {

                        //Code 0 error message

                    }

                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(PickUpService.this);
                    builder.setMessage(R.string.Responsenotsucusess)
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

                }
            }

            @Override
            public void onFailure(Call<ResLocations> call, Throwable t) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(PickUpService.this);
                builder.setMessage(t.getMessage().toString() + "")
                        .setCancelable(false)
                        .setIcon(R.drawable.erroricon)
                        .setTitle(R.string.connectionerorr)
                        .setPositiveButton(R.string.Dissmiss, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });















        // Add a marker in Sydney and move the camera

/*
        for(int i=0;i<locatioNList.size();i++){
            LatLng sydney = new LatLng(locatioNList.get(i).getLatitude(),locatioNList.get(i).getLongtitude());
            mMap.addMarker(new MarkerOptions().position(sydney).title(locatioNList.get(i).getBranchName()));
        }
*/

       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
       // float zoomLevel = (float) 5.0; //This goes up to 21
       //  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
    }
}

package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.CartData;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResAdditions;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCreateCart;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCustomizations;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.Adabters.AdditionsAdb;
import app.appsmatic.com.deliverymasterclintapp.Adabters.CustomizationCatsAdb;
import app.appsmatic.com.deliverymasterclintapp.Adabters.CustomizationsAdb;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.CartMeal;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.MealAddition;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.MealCustomization;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import app.appsmatic.com.deliverymasterclintapp.Tools.ResturantId;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Customization extends AppCompatActivity {

    private TextView priceTv;
    private TextView countTv;
    private int count =0;
    private ImageView up;
    private ImageView down;
    private int mealId;
    private Double price;
    private RecyclerView additionsList;
    private RecyclerView customizationsList;
    private ImageView addCart;
    private List<MealAddition> mealAdditionList =new ArrayList<>();
    private List<MealCustomization>mealCustomizations=new ArrayList<>();
    private CartMeal cartMeal=new CartMeal();
    private String mealName="";
    private String mealDec;
    private String mealImg;
    private LinearLayout additionsBox,customizationsBox;

    AdditionsAdb adb;
    CustomizationCatsAdb cAdb;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.toptodown, R.anim.alpha);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_customization);














        //Receive Data from Meals Adapter
        price=getIntent().getDoubleExtra("price", 1.0);
        mealId=getIntent().getIntExtra("mealId", 0);
        mealName=getIntent().getStringExtra("mealname");
        mealDec=getIntent().getStringExtra("mealdec");
        mealImg=getIntent().getStringExtra("mealspic");


        //Setup Items
        up=(ImageView)findViewById(R.id.inc);
        down=(ImageView)findViewById(R.id.dec);
        priceTv=(TextView)findViewById(R.id.custome_price_tv);
        countTv=(TextView)findViewById(R.id.value_tv);
        additionsList=(RecyclerView)findViewById(R.id.addisions_list);
        customizationsList=(RecyclerView)findViewById(R.id.customizations_list);
        addCart=(ImageView)findViewById(R.id.addcartbtn);
        additionsBox=(LinearLayout)findViewById(R.id.additionsContaner);
        customizationsBox=(LinearLayout)findViewById(R.id.customizatonsContaner);
        //Set image language for logo and login button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            addCart.setImageResource(R.drawable.addbtnarabic);
        }else{
            addCart.setImageResource(R.drawable.addbtnenglish);
        }

        //Increment Button
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                countTv.setText(count+"");
            }
        });

        //Decrement Button
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0)
                    return;
                count--;
                countTv.setText(count + "");
            }
        });

        //Set Price on Screen
        priceTv.setText(price + " SR");




        //Get Customizations By Restaurant Id and Meal Id >>>>>>>>>>>>>>>>

        HashMap mealDataForCustomizations = new HashMap();
        mealDataForCustomizations.put("RestaurantID", ResturantId.resId);
        mealDataForCustomizations.put("MealID",mealId);

        Genrator.createService(ClintAppApi.class).GetCustomizations(mealDataForCustomizations).enqueue(new Callback<ResCustomizations>() {
            @Override
            public void onResponse(Call<ResCustomizations> call, Response<ResCustomizations> response) {

                if (response.isSuccessful()) {
                    String code = response.body().getCode() + "";
                    //Check if response from server is not 0
                    if (!code.equals("0")) {

                        //Check if no additions hide additions box
                        if (response.body().getMessage().isEmpty()) {
                            customizationsBox.setVisibility(View.INVISIBLE);
                        } else {
                            customizationsList.setAdapter(cAdb = new CustomizationCatsAdb(response.body().getMessage(),getApplicationContext()));
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                            customizationsList.setLayoutManager(layoutManager);
                        }


                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                    }


                }else {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(Customization.this);
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
            public void onFailure(Call<ResCustomizations> call, Throwable t) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
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



























        //Get Additions by meal id >>>>>>>>>>>>>>>>>>>>>>

        HashMap mealData = new HashMap();
        //NOTE :::: Remember to add received meal id  not 3074
        mealData.put("MealID", mealId);
        //Toast.makeText(getApplicationContext(), mealId+"", Toast.LENGTH_SHORT).show();

        Genrator.createService(ClintAppApi.class).GetAdditions(mealData).enqueue(new Callback<ResAdditions>() {
            @Override
            public void onResponse(Call<ResAdditions> call, Response<ResAdditions> response) {
                if (response.isSuccessful()) {
                    String code = response.body().getCode() + "";
                    //Check if response from server is not 0
                    if (!code.equals("0")) {

                        //Check if no additions hide additions box
                        if (response.body().getMessage().isEmpty()) {
                            additionsBox.setVisibility(View.INVISIBLE);
                        } else {
                            additionsList.setAdapter(adb = new AdditionsAdb(response.body(), getApplicationContext()));
                            additionsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }


                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(Customization.this);
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
            public void onFailure(Call<ResAdditions> call, Throwable t) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(Customization.this);
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





        //AddCart Button Action >>>>>>


             //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           addCart.setBackgroundResource(R.drawable.ripple);
        }

            addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //Check meals count if 0 don't add any thing to cart !
                    if (count == 0) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(Customization.this);
                        builder.setMessage(R.string.emptyerorr)
                                .setCancelable(false)
                                .setIcon(R.drawable.erroricon)
                                .setTitle(R.string.sysMsg)
                                .setPositiveButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {

                        //Fill addition List from list Adapter
                        int i;
                        for (i = 0; i < adb.mealAdditions.size(); i++) {
                            if (adb.mealAdditions.get(i).getAddCount() != 0) {
                                mealAdditionList.add(adb.mealAdditions.get(i));
                            }
                        }

                        //Fill customizations from List adapter

                        int j;
                        for (j = 0;j < cAdb.catsAdb.mealCustomizations.size(); j++) {
                            if (cAdb.catsAdb.mealCustomizations.get(j).getCustomizationCount() != 0) {
                                mealCustomizations.add(cAdb.catsAdb.mealCustomizations.get(j));
                            }
                        }

                        //add data to local storage and server
                        cartMeal.setMealItemID(mealId);
                        cartMeal.setMealAdditions(mealAdditionList);
                        if(mealCustomizations.isEmpty()){
                            cartMeal.setCustomization(null);
                        }else {
                            cartMeal.setCustomization(mealCustomizations);
                        }
                        cartMeal.setMealCount(count);
                        cartMeal.setMealPrice(price);
                        cartMeal.setMealName(mealName);
                        cartMeal.setMealDecription(mealDec);
                        cartMeal.setMealPic(mealImg);


                        Home.cartMeals.add(cartMeal);
                        adb.mealAdditions.clear();
                        cAdb.catsAdb.mealCustomizations.clear();
                        //Increment Cart tv number
                        Home.icon = (LayerDrawable)Home.itemCart.getIcon();
                        Home.setBadgeCount(getBaseContext(), Home.icon,"");
                        Home.setBadgeCount(getBaseContext(), Home.icon, Home.cartMeals.size() + "");
                        Customization.this.finish();







                        /*
                        Log.e("Meal Name : ", cartMeal.getMealName());
                        Log.e("Items Count : ", cartMeal.getMealCount() + "");
                        for (int x = 0; x < cartMeal.getMealAdditions().size(); x++) {
                            Log.e("additions : ", cartMeal.getMealAdditions().get(x).getAdditionName() + "  Count : " + cartMeal.getMealAdditions().get(x).getAddCount() + "");

                        }
                         */

                    }
                }
            });



    }

}

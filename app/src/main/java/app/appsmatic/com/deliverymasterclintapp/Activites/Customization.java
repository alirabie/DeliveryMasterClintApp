package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResAdditions;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.Adabters.AdditionsAdb;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.CartMeal;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.MealAddition;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Customization extends AppCompatActivity {

    private TextView priceTv;
    private TextView countTv;
    private int count =0;
    private ImageView up;
    private ImageView down;
    private String mealId;
    private Double price;
    private RecyclerView additionsList;
    private ImageView addCart;
    private List<MealAddition> mealAdditionList =new ArrayList<>();
    private CartMeal cartMeal=new CartMeal();
    private String mealName="";
    private String mealDec;
    private String mealImg;
    AdditionsAdb adb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);


        //Receive Data from Meals Adapter
        price=getIntent().getDoubleExtra("price", 1.0);
        mealId=getIntent().getStringExtra("mealId");
        mealName=getIntent().getStringExtra("mealname");
        mealDec=getIntent().getStringExtra("mealdec");
        mealImg=getIntent().getStringExtra("mealspic");


        //Setup Items
        up=(ImageView)findViewById(R.id.inc);
        down=(ImageView)findViewById(R.id.dec);
        priceTv=(TextView)findViewById(R.id.custome_price_tv);
        countTv=(TextView)findViewById(R.id.value_tv);
        additionsList=(RecyclerView)findViewById(R.id.addisions_list);
        addCart=(ImageView)findViewById(R.id.addcartbtn);
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
                if(count==0)
                    return;
                count--;
                countTv.setText(count+"");
            }
        });

        //Set Price on Screen
        priceTv.setText(price + " SR");



        //Get Additions by meal id

        HashMap mealData = new HashMap();
        mealData.put("MealID", "3074");

        Genrator.createService(ClintAppApi.class).GetAdditions(mealData).enqueue(new Callback<ResAdditions>() {
            @Override
            public void onResponse(Call<ResAdditions> call, Response<ResAdditions> response) {
                if (response.isSuccessful()) {
                    String code = response.body().getCode() + "";
                    if (!code.equals("0")) {
                        additionsList.setAdapter(adb=new AdditionsAdb(response.body(), getApplicationContext()));
                        additionsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    } else {

                        //Fail 0 Error
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
                builder.setMessage(t.getMessage().toString()+"")
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



        //AddCart Button Action
             //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           addCart.setBackgroundResource(R.drawable.ripple);
        }





            addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Check meals count if 0 don't add any thing to cart !
                    if(count==0){
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
                    }else {

                        //Fill addition List from list Adapter
                        int i;
                        for (i = 0; i < adb.mealAdditions.size(); i++) {
                            if (adb.mealAdditions.get(i).getAddCount() != 0) {
                                mealAdditionList.add(adb.mealAdditions.get(i));
                            }
                        }


                        cartMeal.setMealName(mealName);
                        cartMeal.setMealCount(count);
                        cartMeal.setMealAdditions(mealAdditionList);
                        cartMeal.setMealDecription(mealDec);
                        cartMeal.setMealPic(mealImg);
                        cartMeal.setMealPrice(price);

                        Log.e("Meal Name : ", cartMeal.getMealName());
                        Log.e("Items Count : ", cartMeal.getMealCount() + "");
                        for (int x = 0; x < cartMeal.getMealAdditions().size(); x++) {
                            Log.e("additions : ", cartMeal.getMealAdditions().get(x).getAdditionName() + "  Count : " + cartMeal.getMealAdditions().get(x).getAddCount() + "");

                        }

                        Home.cartMeals.add(cartMeal);
                        adb.mealAdditions.clear();
                        Customization.this.finish();


                    }
                }
            });



    }

}

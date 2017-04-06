package app.appsmatic.com.deliverymasterclintapp.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;


import java.util.HashMap;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResMeals;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.Adabters.MealsAdb;
import app.appsmatic.com.deliverymasterclintapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MealsFrag extends android.app.Fragment {
    private RecyclerView mealsList;
    private MealsAdb mealsAdb;
    private String codeid="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Bundle b = this.getArguments();
        String id = b.getString("catid");
        HashMap catDate = new HashMap();
        catDate.put("RestaurantID", "11");
        catDate.put("CategoryID", id);


            //Loading Dialog
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity(),R.style.AppCompatAlertDialogStyle);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage(Html.fromHtml("<font color=#FFFFFF><big>"+getResources().getString(R.string.mealloding)+"</big></font>"));
            mProgressDialog.show();



        //freeze screen when progress dialog on
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Genrator.createService(ClintAppApi.class).GetMeals(catDate).enqueue(new Callback<ResMeals>() {
            @Override
            public void onResponse(Call<ResMeals> call, Response<ResMeals> response) {
                if (response.isSuccessful()) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    try {
                        //un freeze screen
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        //get message code
                        codeid = response.body().getCode() + "";

                        //Check If message Success Or Not
                        if (!codeid.equals("0")) {
                            mealsAdb = new MealsAdb(getActivity(), response.body());
                            mealsList = (RecyclerView) getActivity().findViewById(R.id.mealsList);
                            mealsList.setLayoutManager(new LinearLayoutManager(getActivity()));
                            mealsList.setAdapter(mealsAdb);
                            mealsAdb.notifyDataSetChanged();

                        } else {


                            //message code  0 failed
                        }

                    }catch (Exception e){}
                } else{


                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
            public void onFailure(Call<ResMeals> call, Throwable t) {


                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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





























    }
}

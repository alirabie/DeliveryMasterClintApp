package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCats;
import app.appsmatic.com.deliverymasterclintapp.Fragments.MealsFrag;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import app.appsmatic.com.deliverymasterclintapp.URLS.BaseURL;
import app.appsmatic.com.deliverymasterclintapp.R;

/**
 * Created by Mido PC on 12/19/2016.
 */
public class CategoriesAdb extends RecyclerView.Adapter<CategoriesAdb.vh> {

    private Context context;
    private ResCats resCats;
    private MealsFrag mealsFrag;
    private String transId;
    private Bundle bundle;
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";

    public CategoriesAdb(Context context, ResCats resCats) {
        this.context = context;
        this.resCats = resCats;
    }

    @Override
    public CategoriesAdb.vh onCreateViewHolder(ViewGroup parent, int viewType) {
        return new vh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_topslider_home,parent,false));
    }

    @Override
    public void onBindViewHolder(final CategoriesAdb.vh holder, final int position) {

        holder.catLbl.setText(resCats.getMessage().get(position).getName() + "");
        Typeface face=Typeface.createFromAsset(context.getAssets(), "arabicfont.ttf");
        holder.catLbl.setTypeface(face);

        //Encoding Img URl
        String url = Uri.encode(BaseURL.IMGS+resCats.getMessage().get(position).getIcon().toString(),ALLOWED_URI_CHARS);


        //Check Settings For Load images
        if(SaveSharedPreference.getImgLoadingSatatus(context)){
        Picasso.with(context)
                .load(url)
                .fit()
                .placeholder(R.drawable.rotat)
                .into(holder.catImg);
        }else{
            Picasso.with(context)
                    .load(R.drawable.catsplaceholder)
                    .fit()
                    .into(holder.catImg);
        }


        //Category img button to meals
        holder.catImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealsFrag = new MealsFrag();
                bundle = new Bundle();
                transId = resCats.getMessage().get(position).getID() + "";
                bundle.putString("catid", transId);
                mealsFrag.setArguments(bundle);
                FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.foodmealsfragmentcontener, mealsFrag).commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return resCats.getMessage().size();
    }








    public static class vh extends RecyclerView.ViewHolder{
        ImageView catImg;
        TextView catLbl;
        public vh(View itemView) {
            super(itemView);
            catImg=(ImageView)itemView.findViewById(R.id.top_slider_image_item_layout);
            catLbl=(TextView)itemView.findViewById(R.id.top_slider_tv_item_layout_name);

        }
    }



    }



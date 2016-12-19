package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCats;
import app.appsmatic.com.deliverymasterclintapp.Fragments.MealsFrag;
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
    final String IMGURL ="https://deliverymaster.co";
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

        String url = Uri.encode(IMGURL+resCats.getMessage().get(position).getIcon().toString(),ALLOWED_URI_CHARS);
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.itemplaceholder)
                .into(holder.catImg);


        holder.catImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealsFrag =new MealsFrag();
                bundle = new Bundle();
                transId = resCats.getMessage().get(position).getID()+"";
                bundle.putString("catid", transId);
                mealsFrag.setArguments(bundle);
                FragmentManager fragmentManager = ((Activity)context).getFragmentManager();
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







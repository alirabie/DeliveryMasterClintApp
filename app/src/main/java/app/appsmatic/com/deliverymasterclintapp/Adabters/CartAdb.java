package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.Activites.Home;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.CartMeal;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import app.appsmatic.com.deliverymasterclintapp.URLS.BaseURL;

/**
 * Created by Mido PC on 1/21/2017.
 */
public class CartAdb extends RecyclerView.Adapter<CartAdb.VH1001> {

    private List<CartMeal>cartMeals;
    private Context context;
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";

    public CartAdb(List<CartMeal> cartMeals, Context context) {
        this.cartMeals = cartMeals;
        this.context = context;
    }

    @Override
    public VH1001 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH1001(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_order_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(final VH1001 holder, final int position) {

        holder.name.setText(cartMeals.get(position).getMealName()+"");
        holder.mealcount.setText(cartMeals.get(position).getMealCount()+"");
        holder.mealdec.setText(cartMeals.get(position).getMealDecription());
        //Calc meal Count * price and Show it
        holder.mealprice.setText(cartMeals.get(position).getMealPrice()*cartMeals.get(position).getMealCount()+"");

        //Encoding Img URL
        String url = Uri.encode(BaseURL.IMGS + cartMeals.get(position).getMealPic().toString(), ALLOWED_URI_CHARS);
        //Check Settings For Load images
        if(SaveSharedPreference.getImgLoadingSatatus(context)){
            Picasso.with(context)
                    .load(url)
                    .placeholder(R.drawable.rotat)
                    .fit()
                    .into(holder.mealpic);
        }else {

            Picasso.with(context)
                    .load(R.drawable.mealsplaceholder)
                    .fit()
                    .into(holder.mealpic);
        }

        //Setup Addition list
        holder.additinsList.setAdapter(new CartMealsAdditionsAdb(cartMeals.get(position).getMealAdditions(), context));
        holder.additinsList.setLayoutManager(new LinearLayoutManager(context));

        //Setup Customizations List

        holder.customizatinsList.setAdapter(new CartCustomizationsAdb(cartMeals.get(position).getCustomization(),context));
        holder.customizatinsList.setLayoutManager(new LinearLayoutManager(context));

        //Increment Count of meal
        holder.upcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get Current count
                int num=cartMeals.get(position).getMealCount();
                //Increment It
                num++;
                //Multiply to meal price and show it
                holder.mealprice.setText(cartMeals.get(position).getMealPrice() * num + "");
                //show count
                holder.mealcount.setText(num + "");
                //update cart with new values
                cartMeals.get(position).setMealCount(num);

            }
        });

        //Decrement Count Of meal
        holder.downcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get Current count
                int num=cartMeals.get(position).getMealCount();
                //Decrement It
                num--;
                //Multiply to meal price and show it
                holder.mealprice.setText(cartMeals.get(position).getMealPrice()*num+"");
                //update cart with new values
                cartMeals.get(position).setMealCount(num);
                //show count
                holder.mealcount.setText(num+"");
                //Check if count 0 delete meal from cart
                if(num==0){
                    cartMeals.remove(position);
                    notifyDataSetChanged();
                }
            }
        });


        //Delete Meal button Action
        holder.deleteMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.deletemeal)
                        .setCancelable(false)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                cartMeals.remove(position);
                                notifyDataSetChanged();
                                //update cart badge count
                                if(Home.cartMeals.isEmpty()){
                                    Home.badgeView.hide();
                                }else {
                                    Home.badgeView.setText(Home.cartMeals.size() + "");
                                    Home.badgeView.show();
                                }

                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }).setIcon(android.R.drawable.alert_light_frame);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });





    }

    @Override
    public int getItemCount() {
        return cartMeals.size();
    }

    public static class VH1001 extends RecyclerView.ViewHolder{

        private TextView name,mealdec,mealprice,mealcount;
        private ImageView upcount,downcount,mealpic,deleteMeal;
        private RecyclerView additinsList;
        private RecyclerView customizatinsList;

        public VH1001(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.cart_meal_name);
            mealdec=(TextView)itemView.findViewById(R.id.cart_tv_meal_info);
            mealprice=(TextView)itemView.findViewById(R.id.cart_tv_meal_price);
            mealcount=(TextView)itemView.findViewById(R.id.cart_meal_count);

            upcount=(ImageView)itemView.findViewById(R.id.cart_meal_inc);
            downcount=(ImageView)itemView.findViewById(R.id.cart_meal_dec);
            mealpic=(ImageView)itemView.findViewById(R.id.cart_meal_pic);
            deleteMeal=(ImageView)itemView.findViewById(R.id.delete_meal_btn);

            additinsList=(RecyclerView)itemView.findViewById(R.id.cart_additions_list);
            customizatinsList=(RecyclerView)itemView.findViewById(R.id.cart_customization_list);


        }
    }
}

package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.CustomizationM;
import app.appsmatic.com.deliverymasterclintapp.Activites.Home;
import app.appsmatic.com.deliverymasterclintapp.Activites.ShoppingCart;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.MealCustomization;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;

/**
 * Created by Mido PC on 2/8/2017.
 */
public class CartCustomizationsAdb extends RecyclerView.Adapter<CartCustomizationsAdb.viewholder> {


    private List<MealCustomization>customizations=new ArrayList<>();
    private Context context;


    public CartCustomizationsAdb(List<MealCustomization> customizations, Context context) {
        this.customizations = customizations;
        this.context = context;
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_cart_castomization,parent,false));
    }

    @Override
    public void onBindViewHolder(final viewholder holder, final int position) {


        holder.name.setText(customizations.get(position).getCustomizationName()+"");
//        holder.count.setText(customizations.get(position).getCustomizationCount()+"");

        //Calc Count price
        holder.price.setText(customizations.get(position).getCustomizationPrice()*customizations.get(position).getCustomizationCount()+"");

/*
        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get Current count
                int num = customizations.get(position).getCustomizationCount();
                //Increment It
                num++;
                //Multiply to addition price and show it
                holder.price.setText(customizations.get(position).getCustomizationPrice() * num + "");
                //show count
                holder.count.setText(num + "");
                //update cart with new values
                customizations.get(position).setCustomizationCount(num);
                ShoppingCart.updateCartPrice(context);
                SaveSharedPreference.setCartOrders(context, Home.cartMeals);

            }
        });


        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get Current count
                int num=customizations.get(position).getCustomizationCount();
                //Decrement It
                num--;
                //Multiply to addition price and show it
                holder.price.setText(customizations.get(position).getCustomizationPrice()*num+"");
                //update cart with new values
                customizations.get(position).setCustomizationCount(num);
                //show count
                holder.count.setText(num + "");
                //Check if count 0 delete addition from cart
                if(num==0){
                    customizations.remove(position);
                    notifyDataSetChanged();
                }
                ShoppingCart.updateCartPrice(context);
                SaveSharedPreference.setCartOrders(context, Home.cartMeals);
            }
        });

*/

        //Delete Addition button action
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.dialogmessagedeleteconfirmation)
                        .setCancelable(false)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                customizations.remove(position);
                                notifyDataSetChanged();
                                ShoppingCart.updateCartPrice(context);
                                SaveSharedPreference.setCartOrders(context, Home.cartMeals);
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
        return customizations.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder{

        private TextView name,count,price;
        private ImageView up,down,delete;

        public viewholder(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.cart_additions_add_tv_name);
            count=(TextView)itemView.findViewById(R.id.cart_additions_value_tv);
            price=(TextView)itemView.findViewById(R.id.cart_additions_add_tv_prive);
         //     up=(ImageView)itemView.findViewById(R.id.cart_additions_add_up_btn);
         //   down=(ImageView)itemView.findViewById(R.id.cart_additions_add_down_btn);
            delete=(ImageView)itemView.findViewById(R.id.cart_additions_delete_btn);
        }
    }
}

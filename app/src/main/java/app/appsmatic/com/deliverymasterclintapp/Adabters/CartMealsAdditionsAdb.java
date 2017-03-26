package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.Activites.Home;
import app.appsmatic.com.deliverymasterclintapp.Activites.ShoppingCart;
import app.appsmatic.com.deliverymasterclintapp.Activites.SignIn;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.MealAddition;
import app.appsmatic.com.deliverymasterclintapp.R;

/**
 * Created by Mido PC on 1/21/2017.
 */
public class CartMealsAdditionsAdb extends RecyclerView.Adapter<CartMealsAdditionsAdb.VH2000> {

    private List<MealAddition>mealAdditions;
    private Context context;

    public CartMealsAdditionsAdb(List<MealAddition> mealAdditions, Context context) {
        this.mealAdditions = mealAdditions;
        this.context = context;
    }

    @Override
    public VH2000 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH2000(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_cart_additions,parent,false));
    }

    @Override
    public void onBindViewHolder(final VH2000 holder, final int position) {

        holder.name.setText(mealAdditions.get(position).getAdditionName() + "");
        holder.count.setText(mealAdditions.get(position).getAddCount() + "");
        //Calc Count price
        holder.price.setText(mealAdditions.get(position).getAddprice()*mealAdditions.get(position).getAddCount()+"");






        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get Current count
                int num=mealAdditions.get(position).getAddCount();
                //Increment It
                num++;
                //Multiply to addition price and show it
                holder.price.setText(mealAdditions.get(position).getAddprice()*num+"");
                //show count
                holder.count.setText(num+"");
                //update cart with new values
                mealAdditions.get(position).setAddCount(num);
                ShoppingCart.updateCartPrice(context);

            }
        });


        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get Current count
                int num=mealAdditions.get(position).getAddCount();
                //Decrement It
                num--;
                //Multiply to addition price and show it
                holder.price.setText(mealAdditions.get(position).getAddprice()*num+"");
                //update cart with new values
                mealAdditions.get(position).setAddCount(num);
                //show count
                holder.count.setText(num+"");
                //Check if count 0 delete addition from cart
                if(num==0){
                    mealAdditions.remove(position);
                    notifyDataSetChanged();
                }
                ShoppingCart.updateCartPrice(context);

            }
        });







        //Delete Addition button action
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.dialogmessagedeleteconfirmation)
                        .setCancelable(false)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mealAdditions.remove(position);
                                notifyDataSetChanged();
                                ShoppingCart.updateCartPrice(context);
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
        return mealAdditions.size();
    }

    public static class VH2000 extends RecyclerView.ViewHolder{

        private TextView name,count,price;
        private ImageView up,down,delete;


        public VH2000(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.cart_additions_add_tv_name);
            count=(TextView)itemView.findViewById(R.id.cart_additions_value_tv);
            price=(TextView)itemView.findViewById(R.id.cart_additions_add_tv_prive);

            up=(ImageView)itemView.findViewById(R.id.cart_additions_add_up_btn);
            down=(ImageView)itemView.findViewById(R.id.cart_additions_add_down_btn);
            delete=(ImageView)itemView.findViewById(R.id.cart_additions_delete_btn);



        }

    }
}

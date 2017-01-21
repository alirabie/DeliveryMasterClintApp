package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.CartStructure.CartMeal;
import app.appsmatic.com.deliverymasterclintapp.R;

/**
 * Created by Mido PC on 1/21/2017.
 */
public class CartAdb extends RecyclerView.Adapter<CartAdb.VH1001> {

    private List<CartMeal>cartMeals;
    private Context context;

    public CartAdb(List<CartMeal> cartMeals, Context context) {
        this.cartMeals = cartMeals;
        this.context = context;
    }

    @Override
    public VH1001 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH1001(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_order_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(VH1001 holder, int position) {

        holder.name.setText(cartMeals.get(position).getMealName()+"");
        holder.mealcount.setText(cartMeals.get(position).getMealCount()+"");

        holder.additinsList.setAdapter(new CartMealsAdditionsAdb(cartMeals.get(position).getMealAdditions(), context));
        holder.additinsList.setLayoutManager(new LinearLayoutManager(context));





    }

    @Override
    public int getItemCount() {
        return cartMeals.size();
    }

    public static class VH1001 extends RecyclerView.ViewHolder{

        private TextView name,mealdec,mealprice,mealcount;
        private ImageView upcount,downcount,mealpic;
        private RecyclerView additinsList;

        public VH1001(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.cart_meal_name);
            mealdec=(TextView)itemView.findViewById(R.id.cart_tv_meal_info);
            mealprice=(TextView)itemView.findViewById(R.id.cart_tv_meal_price);
            mealcount=(TextView)itemView.findViewById(R.id.cart_meal_count);

            upcount=(ImageView)itemView.findViewById(R.id.cart_meal_inc);
            downcount=(ImageView)itemView.findViewById(R.id.cart_meal_dec);
            mealpic=(ImageView)itemView.findViewById(R.id.cart_meal_pic);

            additinsList=(RecyclerView)itemView.findViewById(R.id.cart_additions_list);


        }
    }
}

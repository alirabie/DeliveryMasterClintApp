package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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
    public void onBindViewHolder(VH2000 holder, int position) {

        holder.name.setText(mealAdditions.get(position).getAdditionName()+"");
        holder.count.setText(mealAdditions.get(position).getAddCount()+"");
    }

    @Override
    public int getItemCount() {
        return mealAdditions.size();
    }

    public static class VH2000 extends RecyclerView.ViewHolder{

        private TextView name,count,price;


        public VH2000(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.cart_additions_add_tv_name);
            count=(TextView)itemView.findViewById(R.id.cart_additions_value_tv);
            price=(TextView)itemView.findViewById(R.id.cart_additions_add_tv_prive);
        }

    }
}

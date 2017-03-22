package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.OrderDetailsItem;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResOrderDetails;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.CartMeal;
import app.appsmatic.com.deliverymasterclintapp.R;

/**
 * Created by Mido PC on 3/20/2017.
 */
public class OrderDetailsAdb extends RecyclerView.Adapter<OrderDetailsAdb.Vh309> {

    private List<OrderDetailsItem> orderDetailsItems;
    private Context context;

    public OrderDetailsAdb(List<OrderDetailsItem> orderDetailsItems, Context context) {
        this.orderDetailsItems = orderDetailsItems;
        this.context = context;
    }

    @Override
    public Vh309 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Vh309(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_confirm_list,parent,false));
    }

    @Override
    public void onBindViewHolder(Vh309 holder, int position) {

        animate(holder);
        holder.mealName.setText(orderDetailsItems.get(position).getName()+"");
        holder.mealCount.setText(orderDetailsItems.get(position).getQuantity()+"");
        holder.mealTotalPrice.setText(orderDetailsItems.get(position).getTotalPrice()+" SR");
        holder.mealAddCust.setText(getAllAdditionsAndCust(orderDetailsItems.get(position))+"");
    }

    @Override
    public int getItemCount() {
        return orderDetailsItems.size();
    }






// Calc total price
    public double sumTotall(){
        double x=0.0;
        for(int i=0;i<orderDetailsItems.size();i++){
            x=x+orderDetailsItems.get(i).getTotalPrice();
        }
        return x;
    }



    //collect all additions and customizations

    public String getAllAdditionsAndCust (OrderDetailsItem orderDetails){
        String result = " ";
        StringBuilder st = new StringBuilder();

        //Collect customizations
        if (orderDetails.getCustomization() != null) {
            for (int i = 0; i < orderDetails.getCustomization().size(); i++) {
                st.append(" # " + orderDetails.getCustomization().get(i).getCustomizationName() + "   "
                        + orderDetails.getCustomization().get(i).getCustomizationQuantity() + "   "
                        + orderDetails.getCustomization().get(i).getCustomizationPrice() + " SR " + "\n");
            }

        }

        //collect additions
        if (orderDetails.getAdditions() != null) {
            for (int x = 0; x < orderDetails.getAdditions().size(); x++) {
                st.append(" # " + orderDetails.getAdditions().get(x).getAdditionName() + "  "
                        + orderDetails.getAdditions().get(x).getAdditionQuantity() + "  "
                        + orderDetails.getAdditions().get(x).getAdditionPrice() + " SR " + "\n");
            }
        }

        result = st.toString();
        return result;

    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }


    public static class Vh309 extends RecyclerView.ViewHolder{
        private TextView mealName,mealCount,mealTotalPrice,mealAddCust;

        public Vh309(View itemView) {
            super(itemView);
            mealName=(TextView)itemView.findViewById(R.id.c_meal_name);
            mealCount=(TextView)itemView.findViewById(R.id.c_meal_count);
            mealTotalPrice=(TextView)itemView.findViewById(R.id.c_meal_total_price);
            mealAddCust=(TextView)itemView.findViewById(R.id.c_meal_add_cust);
        }
    }


}

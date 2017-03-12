package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.CartStructure.CartMeal;
import app.appsmatic.com.deliverymasterclintapp.R;

/**
 * Created by Mido PC on 3/12/2017.
 */
public class ConfirmOrdersAdb extends RecyclerView.Adapter<ConfirmOrdersAdb.Vio> {
    private List<CartMeal> cartMeals=new ArrayList<>();
    private Context context;


    public ConfirmOrdersAdb(List<CartMeal> cartMeals, Context context) {
        this.cartMeals = cartMeals;
        this.context = context;
    }

    @Override
    public Vio onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Vio(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_confirm_list,parent,false));
    }

    @Override
    public void onBindViewHolder(Vio holder, int position) {
        holder.mealName.setText(cartMeals.get(position).getMealName()+"");
        holder.mealCount.setText(cartMeals.get(position).getMealCount()+"");
        holder.mealTotalPrice.setText(getTotalPrice(cartMeals.get(position))+" SR");
        holder.mealAddCust.setText(getAllAdditionsAndCust(cartMeals.get(position))+"");

    }

    @Override
    public int getItemCount() {
        return cartMeals.size();
    }


    public double getTotalAll(){

        double total=0.0;
        //calc all
        for (int i=0;i<cartMeals.size();i++){
            total=total+getTotalPrice(cartMeals.get(i));
        }
        return total;
    }


    //Calc order total price method

    public double getTotalPrice(CartMeal meal) {
        Double total = 0.0;
        total = meal.getMealPrice() * meal.getMealCount();
        
        if (meal.getCustomization()!=null) {
            //calc customization price total
            for (int i = 0; i < meal.getCustomization().size(); i++) {
                total = total + meal.getCustomization().get(i).getCustomizationCount() * meal.getCustomization().get(i).getCustomizationPrice();
            }
        }
        if (meal.getMealAdditions()!=null) {
            //calc additions price total
            for (int x = 0; x < meal.getMealAdditions().size(); x++) {
                total = total + meal.getMealAdditions().get(x).getAddprice() * meal.getMealAdditions().get(x).getAddCount();
            }
        }
            return total;
        }


    //collect all additions and customizations

    public String getAllAdditionsAndCust(CartMeal meal2 ){

        String result=" ";
        StringBuilder st=new StringBuilder();

        //Collect customizations
       if(meal2.getCustomization()!=null) {
           for (int i = 0; i < meal2.getCustomization().size(); i++) {
               st.append(" # "+meal2.getCustomization().get(i).getCustomizationName() + "   "
                            + meal2.getCustomization().get(i).getCustomizationCount() + "   "
                            + meal2.getCustomization().get(i).getCustomizationPrice() + " SR "+"\n");
           }

       }

        //collect additions
        if(!meal2.getMealAdditions().isEmpty()){
            for (int x=0;x<meal2.getMealAdditions().size();x++){
                st.append(" # "+meal2.getMealAdditions().get(x).getAdditionName()+"  "
                             +meal2.getMealAdditions().get(x).getAddCount()+"  "
                             +meal2.getMealAdditions().get(x).getAddprice()+" SR "+"\n");

            }
        }

        result=st.toString();
        return result;

    }







    public static class Vio extends RecyclerView.ViewHolder{
        private TextView mealName,mealCount,mealTotalPrice,mealAddCust;
        public Vio(View itemView) {
            super(itemView);
            mealName=(TextView)itemView.findViewById(R.id.c_meal_name);
            mealCount=(TextView)itemView.findViewById(R.id.c_meal_count);
            mealTotalPrice=(TextView)itemView.findViewById(R.id.c_meal_total_price);
            mealAddCust=(TextView)itemView.findViewById(R.id.c_meal_add_cust);
        }
    }










}

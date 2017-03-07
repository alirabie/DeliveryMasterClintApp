package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.Meal;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResAdditions;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.MealAddition;
import app.appsmatic.com.deliverymasterclintapp.R;

/**
 * Created by Mido PC on 12/26/2016.
 */
public class AdditionsAdb extends RecyclerView.Adapter<AdditionsAdb.vh00> {

    private ResAdditions additions;
    private Context context;
    public static List<MealAddition>mealAdditions =new ArrayList<>();
    private List<Integer>counts=new ArrayList<>();





    public AdditionsAdb(ResAdditions additions, Context context) {
        this.additions = additions;
        this.context = context;
    }






    @Override
    public vh00 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new vh00(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_addisions,parent,false));
    }

    @Override
    public void onBindViewHolder(final vh00 holder, final int position) {


        animate(holder);
        holder.addName.setText(additions.getMessage().get(position).getName() + "");
        holder.price.setText(additions.getMessage().get(position).getPrice() + " SR");

        //set first item in counts list
        counts.add(position, 0);

        //Fill mealAdditions List with default values
        for (int i=0;i<additions.getMessage().size();i++){
            mealAdditions.add(position,new MealAddition());
        }



        //Increment Additions Count
        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counts.set(position, counts.get(position) + 1);
                holder.count.setText("" + counts.get(position));

                //when set count 0 replace addition item with default value
                if (counts.get(position) == 0) {

                    mealAdditions.set(position, new MealAddition());

                }else{

                    //fill additions list with additions and counts
                    MealAddition mealAddition = new MealAddition();
                    mealAddition.setAdditionName(additions.getMessage().get(position).getName() + "");
                    mealAddition.setAddprice(additions.getMessage().get(position).getPrice());
                    mealAddition.setAddCount(counts.get(position));
                    mealAddition.setiD(additions.getMessage().get(position).getID());
                    mealAdditions.set(position,mealAddition);

                }




            }
        });




        //Decrement Additions Count
        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (counts.get(position) == 0)
                    return;
                counts.set(position, counts.get(position) - 1);
                holder.count.setText("" + counts.get(position));

                if (counts.get(position) == 0) {

                    mealAdditions.set(position, new MealAddition());

                }else{
                    MealAddition mealAddition = new MealAddition();
                    mealAddition.setAdditionName(additions.getMessage().get(position).getName() + "");
                    mealAddition.setAddprice(additions.getMessage().get(position).getPrice());
                    mealAddition.setAddCount(counts.get(position));
                    mealAdditions.set(position, mealAddition);

                }

            }
        });





}






    @Override
    public int getItemCount() {
        return additions.getMessage().size();

    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }


    public static class vh00 extends RecyclerView.ViewHolder{


        private TextView addName,price,count;
        private ImageView up,down;


        public vh00(View itemView) {
            super(itemView);

            up=(ImageView)itemView.findViewById(R.id.add_up_btn);
            down=(ImageView)itemView.findViewById(R.id.add_down_btn);
            addName=(TextView)itemView.findViewById(R.id.add_tv_name);
            price=(TextView)itemView.findViewById(R.id.add_tv_prive);
            count=(TextView)itemView.findViewById(R.id.value_tv);





        }
    }
}

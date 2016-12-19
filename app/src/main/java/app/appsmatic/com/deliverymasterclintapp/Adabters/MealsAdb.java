package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResMeals;
import app.appsmatic.com.deliverymasterclintapp.R;

/**
 * Created by Mido PC on 12/19/2016.
 */
public class MealsAdb extends RecyclerView.Adapter<MealsAdb.vh2> {

    private ResMeals meals;

    public MealsAdb(Context context, ResMeals meals) {
        this.context = context;
        this.meals = meals;
    }

    private Context context;
    final String IMGURL ="https://deliverymaster.co";
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
    @Override
    public vh2 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new vh2(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_meal,parent,false));
    }

    @Override
    public void onBindViewHolder(vh2 holder, int position) {
        holder.title.setText(meals.getMessage().get(position).getName()+"");
        holder.details.setText(meals.getMessage().get(position).getDescription()+"");
        holder.price.setText(meals.getMessage().get(position).getPrice()+" SR");

        String url = Uri.encode(IMGURL+meals.getMessage().get(position).getImagePreview().toString(), ALLOWED_URI_CHARS);
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.itemplaceholder)
                .into(holder.img);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });






    }

    @Override
    public int getItemCount() {
        return meals.getMessage().size();
    }

    public static class vh2 extends RecyclerView.ViewHolder{

        private TextView title,details,price;
        private ImageView img,btn;
        public vh2(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tv_meal_home_mealname);
            details=(TextView)itemView.findViewById(R.id.tv_meal_home_maildetails);
            price=(TextView)itemView.findViewById(R.id.tv_meal_home_price);
            img=(ImageView)itemView.findViewById(R.id.meal_home_mealpic);
            btn=(ImageView)itemView.findViewById(R.id.addmeal_home_meals_btn);
        }
    }
}

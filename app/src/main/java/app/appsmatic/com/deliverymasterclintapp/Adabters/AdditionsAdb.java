package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResAdditions;
import app.appsmatic.com.deliverymasterclintapp.R;

/**
 * Created by Mido PC on 12/26/2016.
 */
public class AdditionsAdb extends RecyclerView.Adapter<AdditionsAdb.vh00> {

    private ResAdditions additions;
    private Context context;

    public AdditionsAdb(ResAdditions additions, Context context) {
        this.additions = additions;
        this.context = context;
    }





    @Override
    public vh00 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new vh00(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_addisions,parent,false));
    }

    @Override
    public void onBindViewHolder(vh00 holder, int position) {

        holder.addName.setText(additions.getMessage().get(position).getName()+"");
        holder.price.setText(additions.getMessage().get(position).getPrice()+" SR");








    }

    @Override
    public int getItemCount() {
        return additions.getMessage().size();
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

package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCurrentOrders;
import app.appsmatic.com.deliverymasterclintapp.R;

/**
 * Created by Mido PC on 3/14/2017.
 */
public class CurrentOrdersAdb extends RecyclerView.Adapter<CurrentOrdersAdb.vh501> {


    private ResCurrentOrders resCurrentOrders;
    private Context context;

    public CurrentOrdersAdb(Context context, ResCurrentOrders resCurrentOrders) {
        this.context = context;
        this.resCurrentOrders = resCurrentOrders;
    }

    @Override
    public vh501 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new vh501(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_current_previous_order,parent,false));
    }

    @Override
    public void onBindViewHolder(vh501 holder, int position) {

        animate(holder);
        holder.no.setText("OrderNo . "+resCurrentOrders.getMessage().get(position).getOrderID()+"");
        if(resCurrentOrders.getMessage().get(position).getPickupBranch()==null) {
            holder.brunch.setText("Address : " + resCurrentOrders.getMessage().get(position).getDeliveryBranch());
        }else {
            holder.brunch.setText("Brunch : "+resCurrentOrders.getMessage().get(position).getPickupBranch());
        }
        holder.status.setText("Status : "+resCurrentOrders.getMessage().get(position).getStatus()+"");
        holder.type.setText("Type : "+resCurrentOrders.getMessage().get(position).getOrderType());
        holder.date.setText(resCurrentOrders.getMessage().get(position).getOrderDate()+"");
    }

    @Override
    public int getItemCount() {
        return resCurrentOrders.getMessage().size();
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
    public static class vh501 extends RecyclerView.ViewHolder{
        private TextView no,brunch,status,type,date,price;
        private ImageView detailsBtn;
        public vh501(View itemView) {
            super(itemView);

            no=(TextView)itemView.findViewById(R.id.curntorder_itemlayout_ordernum);
            brunch=(TextView)itemView.findViewById(R.id.brunchtv);
            status=(TextView)itemView.findViewById(R.id.status_tv);
            type=(TextView)itemView.findViewById(R.id.type_tv);
            date=(TextView)itemView.findViewById(R.id.curntorder_itemlayout_date);
            price=(TextView)itemView.findViewById(R.id.curntorder_itemlayout_price);
            detailsBtn=(ImageView)itemView.findViewById(R.id.current_order_details_btn);
        }
    }
}

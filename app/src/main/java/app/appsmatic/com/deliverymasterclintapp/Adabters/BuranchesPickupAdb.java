package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResLocations;
import app.appsmatic.com.deliverymasterclintapp.R;

/**
 * Created by Mido PC on 2/19/2017.
 */
public class BuranchesPickupAdb extends RecyclerView.Adapter<BuranchesPickupAdb.Vholder> {

    private ResLocations locations;
    private Context context;

    public BuranchesPickupAdb(Context context, ResLocations locations) {
        this.context = context;
        this.locations = locations;
    }

    @Override
    public Vholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Vholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_branch_pickup,parent,false));
    }

    @Override
    public void onBindViewHolder(Vholder holder, int position) {
        holder.branchNameTv.setText(locations.getMessage().get(position).getBranchName());
        holder.branchAddressTv.setText(locations.getMessage().get(position).getStreetAddress());
    }

    @Override
    public int getItemCount() {
        return locations.getMessage().size();
    }

    public static class Vholder extends RecyclerView.ViewHolder{
        private TextView branchNameTv,branchAddressTv;

        public Vholder(View itemView) {
            super(itemView);
            branchNameTv=(TextView)itemView.findViewById(R.id.b_name_pickup_item);
            branchAddressTv=(TextView)itemView.findViewById(R.id.b_address_picup_item);



        }
    }
}

package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResLocations;
import app.appsmatic.com.deliverymasterclintapp.Activites.DeliveryService;
import app.appsmatic.com.deliverymasterclintapp.Activites.LocationDetails;
import app.appsmatic.com.deliverymasterclintapp.R;

/**
 * Created by Mido PC on 2/24/2017.
 */
public class DeliveryBrunchesAdb extends RecyclerView.Adapter<DeliveryBrunchesAdb.Vholder> {
    private ResLocations locations;
    private Context context;

    public DeliveryBrunchesAdb(Context context, ResLocations locations) {
        this.context = context;
        this.locations = locations;
    }

    @Override
    public Vholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Vholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_branch_pickup,parent,false));
    }

    @Override
    public void onBindViewHolder(Vholder holder, final int position) {
        holder.branchNameTv.setText(locations.getMessage().get(position).getBranchName());
        holder.branchAddressTv.setText(locations.getMessage().get(position).getStreetAddress());
        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.clckLayout.setBackgroundResource(R.drawable.ripple);
        }
        holder.clckLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                context.startActivity(new Intent(context, LocationDetails.class)
                        .putExtra("locationId",locations.getMessage().get(position).getLocationID()+"")
                        .putExtra("locationName",locations.getMessage().get(position).getBranchName()+"")
                        .putExtra("locationAddress",locations.getMessage().get(position).getStreetAddress())
                        .putExtra("lat",locations.getMessage().get(position).getLatitude()+"")
                        .putExtra("long",locations.getMessage().get(position).getLongtitude()+"")
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));*/
                ((Activity)context).finish();

                Toast.makeText(context,"Your Location Id : "+locations.getMessage().get(position).getLocationID()+"",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.getMessage().size();
    }

    public static class Vholder extends RecyclerView.ViewHolder{
        private TextView branchNameTv,branchAddressTv;
        private LinearLayout clckLayout;

        public Vholder(View itemView) {
            super(itemView);
            branchNameTv=(TextView)itemView.findViewById(R.id.b_name_pickup_item);
            branchAddressTv=(TextView)itemView.findViewById(R.id.b_address_picup_item);
            clckLayout=(LinearLayout)itemView.findViewById(R.id.layoutbutton);



        }
    }
}

package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResLocations;
import app.appsmatic.com.deliverymasterclintapp.Activites.Confirmation;
import app.appsmatic.com.deliverymasterclintapp.Activites.LocationDetails;
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
    public void onBindViewHolder(Vholder holder, final int position) {
        animate(holder);
        holder.branchNameTv.setText(locations.getMessage().get(position).getBranchName());
        //put title font style
        Typeface face=Typeface.createFromAsset(context.getAssets(), "bbcfont.ttf");
        holder.branchNameTv.setTypeface(face);
        holder.branchAddressTv.setText(locations.getMessage().get(position).getStreetAddress());
        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.clckLayout.setBackgroundResource(R.drawable.ripple);
        }
        holder.clckLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.selectloc)
                        .setCancelable(false)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                context.startActivity(new Intent(context, Confirmation.class)
                                        .putExtra("locationId", locations.getMessage().get(position).getLocationID() + "")
                                        .putExtra("servicetype", 1).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                ((Activity)context).finish();
                                Toast.makeText(context, context.getResources().getString(R.string.addresssent) +" Id : "+ locations.getMessage().get(position).getLocationID() + "", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }).setIcon(android.R.drawable.alert_light_frame);
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.getMessage().size();
    }


    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
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

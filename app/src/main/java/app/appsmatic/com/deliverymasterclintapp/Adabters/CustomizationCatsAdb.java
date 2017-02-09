package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.CustomizationMessage;
import app.appsmatic.com.deliverymasterclintapp.R;

/**
 * Created by Mido PC on 2/8/2017.
 */
public class CustomizationCatsAdb extends RecyclerView.Adapter<CustomizationCatsAdb.V> {


    private List<CustomizationMessage>customizationMessages;
    private Context context;
    public static CustomizationsAdb catsAdb;

    public CustomizationCatsAdb(List<CustomizationMessage> customizationMessages, Context context) {
        this.customizationMessages = customizationMessages;
        this.context = context;
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        return new V(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_item_customization,parent,false));
    }

    @Override
    public void onBindViewHolder(final V holder, final int position) {


        holder.catName.setText(customizationMessages.get(position).getCustomizationType() + "");
        holder.subCats.setAdapter(catsAdb = new CustomizationsAdb(context, customizationMessages.get(position).getCustomizations()));
        holder.subCats.setLayoutManager(new LinearLayoutManager(context));











    }

    @Override
    public int getItemCount() {
        return customizationMessages.size();
    }

    public static class V extends RecyclerView.ViewHolder{

        private TextView catName;
        private RecyclerView subCats;

        public V(View itemView) {
            super(itemView);

            catName=(TextView)itemView.findViewById(R.id.tv_customization_tap_type);
            subCats=(RecyclerView)itemView.findViewById(R.id.customization_sub_itemes_list);
        }
    }
}

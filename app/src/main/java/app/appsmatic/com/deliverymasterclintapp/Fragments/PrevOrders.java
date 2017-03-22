package app.appsmatic.com.deliverymasterclintapp.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.UserOrder;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResUserOrders;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.Adabters.CurrentOrdersAdb;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PrevOrders extends Fragment {

    private RecyclerView prevList;
    private TextView emptyFlag;
    private List<UserOrder>prevOrders=new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prev_orders, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emptyFlag=(TextView)view.findViewById(R.id.prev_empty_flag);
        emptyFlag.setVisibility(View.INVISIBLE);


        //Loading Dialog
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext(),R.style.AppCompatAlertDialogStyle);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(Html.fromHtml("<font color=#FFFFFF><big>"+getResources().getString(R.string.pordersload)+"</big></font>"));
        mProgressDialog.show();

        HashMap data=new HashMap();
        data.put("owner", SaveSharedPreference.getOwnerId(getContext()));

        Genrator.createService(ClintAppApi.class).getCurrentOrders(data).enqueue(new Callback<ResUserOrders>() {
            @Override
            public void onResponse(Call<ResUserOrders> call, Response<ResUserOrders> response) {

                if (response.isSuccessful()) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if (response.body().getCode() != 0) {

                        //filter user orders list select prev orders
                        for (int i = 0; i < response.body().getMessage().size(); i++) {
                            if (
                                    response.body().getMessage().get(i).getStatus().toString().equals("تم التسليم")||
                                            response.body().getMessage().get(i).getStatus().toString().equals("لم يتم التسليم")) {
                                prevOrders.add(response.body().getMessage().get(i));
                            }
                        }




                        if (prevOrders.isEmpty()) {
                           emptyFlag.setVisibility(View.VISIBLE);
                        } else {
                            emptyFlag.setVisibility(View.INVISIBLE);

                            //Setup List
                            prevList=(RecyclerView)getView().findViewById(R.id.fragment_prev_orders_list);
                            prevList.setAdapter(new CurrentOrdersAdb(getContext(),prevOrders, "p"));
                            prevList.setLayoutManager(new LinearLayoutManager(getContext()));
                        }


                    } else {
                        Toast.makeText(getContext(), " user orders prev 0 erorr ", Toast.LENGTH_LONG).show();
                    }

                } else {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    Toast.makeText(getContext(), "Response Not Success from user orders prev !", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResUserOrders> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Toast.makeText(getContext(), " user orders prev : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });











    }
}

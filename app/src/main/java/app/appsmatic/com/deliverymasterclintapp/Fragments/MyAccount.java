package app.appsmatic.com.deliverymasterclintapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResProfileInfo;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.Activites.Home;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyAccount extends Fragment {

    private TextView customerNameTv,phoneNumber;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_account, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Home.setUserProfileInfo(getContext());
        customerNameTv=(TextView)view.findViewById(R.id.cuustomer_tv_name);
        phoneNumber=(TextView)view.findViewById(R.id.account_tv_phone);
        customerNameTv.setText(Home.userProfile.getFirstName()+" "+Home.userProfile.getLastName());
        phoneNumber.setText(Home.userProfile.getMobileNo()+"");


    }
}

package app.appsmatic.com.deliverymasterclintapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.appsmatic.com.deliverymasterclintapp.R;


public class Info extends Fragment {

    private ImageView infoReslogo;
    private TextView infoResname
                    ,infoResaddress
                    ,infoResphone
                    ,infoRestimeopen
                    ,infoResminimumm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        infoReslogo=(ImageView)getActivity().findViewById(R.id.info_res_logo);
        infoResname=(TextView)getActivity().findViewById(R.id.info_tv_resname);
        infoResaddress=(TextView)getActivity().findViewById(R.id.info_tv_address);
        infoResphone=(TextView)getActivity().findViewById(R.id.info_tv_phone);
        infoRestimeopen=(TextView)getActivity().findViewById(R.id.info_tv_timeopen);
        infoResminimumm=(TextView)getActivity().findViewById(R.id.info_tv_mcharge);
    }
}

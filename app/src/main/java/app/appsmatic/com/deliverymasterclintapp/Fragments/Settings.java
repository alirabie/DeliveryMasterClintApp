package app.appsmatic.com.deliverymasterclintapp.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.Activites.Splash;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;


public class Settings extends Fragment {


    private Spinner lang,serprefs,loadimages;
    private ImageView savebtn;
    private int langflag=0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Lang dropdown list
        lang=(Spinner)getActivity().findViewById(R.id.langlist);
        String[] items = new String[]{"العربية", "English"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        lang.setAdapter(adapter);
        lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Set Lang Flag
               if(position==0){
                    langflag=0;
                }else if(position==1){
                    langflag=1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });








        //Services Prefs dropdwon list
        serprefs=(Spinner)getActivity().findViewById(R.id.prefs);



        //load pics dropdwonlist
        loadimages=(Spinner)getActivity().findViewById(R.id.pics);








        //Save button
        savebtn=(ImageView)getActivity().findViewById(R.id.savebtn);
        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            savebtn.setBackgroundResource(R.drawable.ripple);
        }
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Save Lang Selection depended on lang flag
                switch (langflag){
                    case 0:
                        SaveSharedPreference.setLangId(getActivity().getApplicationContext(), "ar");
                        break;
                    case 1:
                        SaveSharedPreference.setLangId(getActivity().getApplicationContext(), "en");
                        break;



                }

                //Restart App
                getActivity().finish();
                startActivity(new Intent(getContext(), Splash.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });





        //Set save button lang
        if(SaveSharedPreference.getLangId(getActivity().getApplicationContext()).equals("ar")){
            savebtn.setImageResource(R.drawable.savebtnar2);
        }else{
            savebtn.setImageResource(R.drawable.savebtnenn);
        }


    }
}

package com.epicodus.weatherpit.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.epicodus.weatherpit.Constants;
import com.epicodus.weatherpit.R;
import com.epicodus.weatherpit.models.HistoricForecast;
import com.epicodus.weatherpit.services.HistoricForecastService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mCityCoordinatesReference;
    private ValueEventListener mCityCoordinatesReferenceListener;

    @Bind(R.id.getWeatherButton) Button mGetWeatherButton;
    @Bind(R.id.aboutAppButton) Button mAboutAppButton;
    @Bind(R.id.selectCityTextView) TextView mSelectCityTextView;
    @Bind(R.id.titleTextView) TextView mTitleTextView;
    @Bind(R.id.cityListSpinner) Spinner mCityListSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mCityCoordinatesReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_CITY_COORDINATES);

        mCityCoordinatesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot cityCoordinatesSnapshot : dataSnapshot.getChildren()) {
                    String cityStateInfo = cityCoordinatesSnapshot.getValue().toString();
                    Log.d("cities: ", cityStateInfo);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface oswaldFont = Typeface.createFromAsset(getAssets(), "fonts/Oswald-Bold.ttf");
        mTitleTextView.setTypeface(oswaldFont);


        mGetWeatherButton.setOnClickListener(this);
        mAboutAppButton.setOnClickListener(this);

        //Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cityList, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCityListSpinner.setAdapter(adapter);
    }



    //Breaks code when UpArrow clicked
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mCityCoordinatesReference.removeEventListener(mCityCoordinatesReferenceListener);
//    }



    @Override
    public void onClick(View view) {

        if(view == mGetWeatherButton) {
            int city = mCityListSpinner.getSelectedItemPosition();
            Query query = FirebaseDatabase.getInstance().getReference("cities").child(city + "");
            Log.d("Query: ", query.toString());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("dataSnapshot: ", dataSnapshot.toString());
                    double lat = dataSnapshot.child("latitude").getValue(Double.class);
                    double lng = dataSnapshot.child("longitude").getValue(Double.class);
                    Log.d("lat and lng: ", lat + " " + lng);

                    Intent intent = new Intent(MainActivity.this, CurrentHistoricWeatherActivity.class);
                    intent.putExtra("lat", lat);
                    intent.putExtra("lng", lng);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if(view == mAboutAppButton) {
            Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
            startActivity(intent);
        }
    }
}

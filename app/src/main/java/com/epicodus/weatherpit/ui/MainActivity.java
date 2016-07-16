package com.epicodus.weatherpit.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.epicodus.weatherpit.Constants;
import com.epicodus.weatherpit.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.getWeatherButton) Button mGetWeatherButton;
    @Bind(R.id.aboutAppButton) Button mAboutAppButton;
    @Bind(R.id.userInputEditText) EditText mUserInputEditText;
    @Bind(R.id.titleTextView) TextView mTitleTextView;
    @Bind(R.id.cityListSpinner) Spinner mCityListSpinner;

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface oswaldFont = Typeface.createFromAsset(getAssets(), "fonts/Oswald-Bold.ttf");
        mTitleTextView.setTypeface(oswaldFont);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mGetWeatherButton.setOnClickListener(this);
        mAboutAppButton.setOnClickListener(this);

        //Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cityList, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCityListSpinner.setAdapter(adapter);
    }




    @Override
    public void onClick(View view) {
        final String cityState = mUserInputEditText.getText().toString().trim();

        if(cityState.equals("")){
//            addToSharedPreferences(cityState);
            mUserInputEditText.setError("Please enter a city and state (i.e. Portland, Oregon)");
        }
        else if(view == mGetWeatherButton) {
            Intent intent = new Intent(MainActivity.this, CurrentHistoricWeatherActivity.class);
            intent.putExtra("cityState", cityState);
            startActivity(intent);
        }
        if(view == mAboutAppButton) {
            Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
            startActivity(intent);
        }
    }

//    private void addToSharedPreferences(String cityState) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, cityState).apply();
//    }
}

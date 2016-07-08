package com.epicodus.weatherpit.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.epicodus.weatherpit.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.getWeatherButton) Button mGetWeatherButton;
    @Bind(R.id.aboutAppButton) Button mAboutAppButton;
    @Bind(R.id.userInputEditText) EditText mUserInputEditText;
    @Bind(R.id.titleTextView) TextView mTitleTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface oswaldFont = Typeface.createFromAsset(getAssets(), "fonts/Oswald-Bold.ttf");
        mTitleTextView.setTypeface(oswaldFont);
        mGetWeatherButton.setOnClickListener(this);
        mAboutAppButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mGetWeatherButton) {
            String cityState = mUserInputEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, CurrentHistoricWeatherActivity.class);
            intent.putExtra("cityState", cityState);
            startActivity(intent);
        }
        if(view == mAboutAppButton) {
            Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
            startActivity(intent);
        }
    }
}

package com.epicodus.weatherpit;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mGetWeatherButton;
    private Button mAboutAppButton;
    private EditText mUserInputEditText;
    private TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitleTextView = (TextView) findViewById(R.id.titleTextView);
        Typeface oswaldFont = Typeface.createFromAsset(getAssets(), "fonts/Oswald-Bold.ttf");
        mTitleTextView.setTypeface(oswaldFont);
        mUserInputEditText = (EditText) findViewById(R.id.userInputEditText);
        mGetWeatherButton = (Button) findViewById(R.id.getWeatherButton);
        mAboutAppButton = (Button) findViewById(R.id.aboutAppButton);
        mGetWeatherButton.setOnClickListener(this);
        mAboutAppButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mGetWeatherButton) {
            String cityState = mUserInputEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, CurrentHistoricWeatherGraphs.class);
            intent.putExtra("cityState", cityState);
            startActivity(intent);
        }
        if(view == mAboutAppButton) {
            Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
            startActivity(intent);
        }
    }
}

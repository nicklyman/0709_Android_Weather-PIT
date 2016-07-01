package com.epicodus.weatherpit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mGetWeatherButton;
    private EditText mUserInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserInputEditText = (EditText) findViewById(R.id.userInputEditText);
        mGetWeatherButton = (Button) findViewById(R.id.getWeatherButton);
        mGetWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityState = mUserInputEditText.getText().toString();
                Log.d(TAG, cityState);
                Intent intent = new Intent(MainActivity.this, CurrentHistoricWeatherGraphs.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private TextView mWeatherDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mWeatherDetails = (TextView) findViewById(R.id.tv_weather_details);

        Intent intent = getIntent();

        if (intent.hasExtra(getString(R.string.weather_extra))){
            mWeatherDetails.setText(intent.getStringExtra(getString(R.string.weather_extra)));
        }

    }
}
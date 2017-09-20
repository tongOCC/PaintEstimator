package com.example.tong.paintestimator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
private TextView mEstimatedGallons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mEstimatedGallons= (TextView) findViewById(R.id.EstimateGallonsTextView) ;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

    }
    public void returnToMainOnClick(View v)
    {
        this.finish();
    }
}

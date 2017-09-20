package com.example.tong.paintestimator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mlengthEditText;
    private EditText mWidthEditText;
    private EditText mHeightEditText;

    private EditText mDoorEditText;
    private EditText mWindowEditText;

    private TextView mGallonsTextView;
    //tie in model
    private InteriorRoom mRoom=new InteriorRoom();
    // shared prefs

    private SharedPreferences mPref;

    private void initializeViews()
    {
        mlengthEditText= (EditText) findViewById(R.id.LengthTextView);
        mWidthEditText= (EditText) findViewById(R.id.WidthTextView);
        mHeightEditText=(EditText) findViewById(R.id.LengthTextView);
        mDoorEditText=(EditText) findViewById(R.id.DoorsTextView);
        mWindowEditText=(EditText) findViewById(R.id.WindowsTextView);
        mGallonsTextView=(EditText) findViewById(R.id.gallonsTextView);
    }
    private void loadPrefs()
    {
      mPref=getSharedPreferences("edu.ora",MODE_PRIVATE);
              if(mPref!=null)
              {
                  //load all the room information
                  mRoom.setDoors(mPref.getInt("doors",0));
                  mDoorEditText.setText(String.valueOf(mRoom.getDoors()));

                  mRoom.setHeight(mPref.getFloat("height",0.0f));
                  mWindowEditText.setText(String.valueOf(mRoom.getWindows()));

                  mRoom.setWidth(mPref.getFloat("width",0.0f));
                  mWidthEditText.setText(String.valueOf(mRoom.getWidth()));

                  mRoom.setLength(mPref.getFloat("length",0.0f));
                  mlengthEditText.setText(mRoom.getDoors());

                  mRoom.setWindows(mPref.getInt("windows",0));
                  mWindowEditText.setText(mRoom.getWindows());


                  //mEditText.setText(mRoom.getDoors());
              }
    }
    public void savePrefs()
    {
        SharedPreferences.Editor editor= mPref.edit();
        editor.clear();
        editor.putFloat("length",mRoom.getLength());
        editor.putFloat("height",mRoom.getHeight());
        editor.putFloat("width",mRoom.getWidth());
        editor.putInt("doors",mRoom.getDoors());
        editor.putInt("windows",mRoom.getWindows());
        editor.commit();
    }

// dont need reference to button since its hooked up to method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
    loadPrefs();
    }

    public void computeGallons(View v)
    {
        mRoom.setLength(Float.parseFloat(mlengthEditText.getText().toString()));
        mRoom.setWidth(Float.parseFloat(mWidthEditText.getText().toString()));
        mRoom.setHeight(Float.parseFloat(mHeightEditText.getText().toString()));
        mRoom.setDoors(Integer.parseInt(mDoorEditText.getText().toString()));
        savePrefs();
        mGallonsTextView.setText(getString(R.string.interior_surface_area)+mRoom.totalSurfaceArea());
        //mgallons needed text
    }
    protected void goToHelp(View v)
    {
        // Intent constructor specify where to start context and where we're goin

        Intent helpActivity=new Intent(this, HelpActivity.class);
        helpActivity.putExtra("gallons",mRoom.gallonsOfPaintRequired());
        startActivity(helpActivity);
    }
}

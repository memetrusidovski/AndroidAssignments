package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "In onCreate()");
    }

    public void openList(View v){
        Intent i = new Intent(this, ListItemsActivity.class);
        int LAUNCH_SECOND_ACTIVITY = 10;
        startActivityForResult(i, LAUNCH_SECOND_ACTIVITY);
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent data){
        super.onActivityResult(requestCode,responseCode,data);

        String messagePassed = data.getStringExtra("Response");

        toaster(messagePassed);

        if(requestCode == 10 && responseCode == Activity.RESULT_OK)
            Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");
        else
            Log.i(ACTIVITY_NAME, "Code is wrong");
    }

    public void toaster(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT)
            .show();
    }

    public void startChat(View v){
        Log.i(ACTIVITY_NAME, "User clicked Start Chat");

        Intent intent = new Intent(MainActivity.this, ChatWindow.class);
        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}

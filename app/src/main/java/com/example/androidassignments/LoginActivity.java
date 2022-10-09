package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        SharedPreferences sharedPref = getPreferences( Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "email@domain.com");

        ((EditText) findViewById(R.id.emailBox)).setText(email);

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

    public void onLogin(View v){
        String s = ((TextView)findViewById(R.id.emailBox)).getText().toString();
        String p = ((TextView)findViewById(R.id.passwordBox)).getText().toString();
        Log.i("login",s);

        if(isValidEmail(s) && !p.isEmpty()){
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("email", ((EditText) findViewById(R.id.emailBox)).getText().toString());
            editor.apply();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            ((TextView)findViewById(R.id.textView)).setText("There a problem with your email or password!");
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isEmpty(String s){
        return s.isEmpty();
    }
}

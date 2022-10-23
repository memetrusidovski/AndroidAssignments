package com.example.androidassignments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.androidassignments.databinding.ActivityTestToolbarBinding;

public class TestToolbar extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityTestToolbarBinding binding;

    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;
    }

    public void dialogBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View iv = inflater.inflate(R.layout.dialog_signin, null);


        builder.setTitle(R.string.pick_color);
        builder.setView(iv);
        EditText t = (EditText) iv.findViewById(R.id.new_message_text);

        builder
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button


                String s = t.getText().toString();
                snacker( findViewById(R.id.fab),s );
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public boolean onOptionsItemSelected(MenuItem mi){
        int num = mi.getItemId();

        switch (num) {
            case R.id.action_one:
                Snackbar.make( findViewById(R.id.fab) , "Selected one", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
                break;
            case R.id.action_two:
                Log.d("Toolbar", "Option 2selected");
                dialogBox();
                break;
            case R.id.action_three:
                Log.d("Toolbar", "Option 3selected");
                snacker( findViewById(R.id.fab), "Option 3 selected" );
                break;
            case R.id.action_four:
                toaster("Version 1.0, by Memet Rusidovski");
                break;
            case R.id.fab:
                Log.d("Snackbar", "Snackbar was pressed");
                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*binding = ActivityTestToolbarBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        //setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.fab);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        */

    }

    public void snackbar(View v){
        Snackbar.make(v, "This is my app for CP470!", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
    }

    public void snacker(View v, String s){
        Snackbar.make(v, s, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fab);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
            || super.onSupportNavigateUp();

    }

    public void toaster(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT)
            .show();
    }
}

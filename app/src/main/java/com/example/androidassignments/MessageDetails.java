package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MessageDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        Intent intent = getIntent();

        Long id = intent.getLongExtra("id", 0);
        String message = intent.getStringExtra("message");

        TextView tx = (TextView)findViewById(R.id.messageShow);
        TextView tz = (TextView)findViewById(R.id.messageID);

        tx.setText(message);
        tz.setText(Long.toString(id));

    }

    public void deleteButton(View v){
        Intent resultIntent = new Intent(  );
        resultIntent.putExtra("Responser", "Here is my response");
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}

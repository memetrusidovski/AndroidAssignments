package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

public class ChatWindow extends AppCompatActivity {

    ListView listView = null;
    EditText messageBox = null;
    Button send = null;
    SQLiteDatabase db;
    ChatDatabaseHelper CDH;
    boolean isOpen = false;
    long i = 0;

    ArrayList<String> chat = new ArrayList<String>();
    ChatAdapter adapter ;// new ChatAdapter(this, R.layout.activity_chat_window, chat);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        Log.d("Chat", "Open");
        FrameLayout frame = (FrameLayout)findViewById(R.id.frame);
        if(frame != null) isOpen = true;

        listView = (ListView) findViewById(R.id.messageList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(isOpen){

                }else{
                    i = getItemId(position);

                    Cursor cursor = db.rawQuery("SELECT * FROM demo", null);

                    cursor.moveToPosition((int)i);
                    int x = cursor.getColumnIndex("KEY_MESSAGE");


                    Log.d("test", "->" + cursor.getString(x));

                    Intent intent = new Intent(ChatWindow.this, MessageDetails.class);
                    intent.putExtra("message", cursor.getString(x));
                    intent.putExtra("id", i);

                    int LAUNCH_SECOND_ACTIVITY = 10;
                    startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY);

                    //startActivity(intent);
                }
                Log.i("test", "clicked");

            }


        });

        messageBox = (EditText) findViewById(R.id.messageBox);
        send = (Button) findViewById(R.id.send);

        //in this case, “this” is the ChatWindow, which is-A Context object
        ChatAdapter messageAdapter = new ChatAdapter(this, 0, chat);
        adapter = messageAdapter;
        listView.setAdapter (messageAdapter);

        CDH = new ChatDatabaseHelper(this);

        db = CDH.getReadableDatabase();


        //db.execSQL("INSERT INTO demo (KEY_MESSAGE) VALUES (\"message\");");
        Cursor cursor = db.rawQuery("SELECT * FROM demo", null);

        cursor.moveToFirst();
        while(cursor.moveToNext() ) {
            int x = cursor.getColumnIndex("KEY_MESSAGE");
            String s = cursor.getString(x);
            chat.add(s);
            Log.d("Database", s);
        }

        Log.i("Database", "Cursor’s  column count =" + cursor.getColumnCount() );


    }


    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent data){
        super.onActivityResult(requestCode,responseCode,data);

        String messagePassed = data.getStringExtra("Response");


        if(requestCode == 10 && responseCode == Activity.RESULT_OK)
        {
            String item = (String) adapter.getItem((int)i -1);
            adapter.remove(item);
        }

    }

    public long getItemId(int position){
        long i = 0;

        Cursor cursor = db.rawQuery("SELECT * FROM demo", null);

        cursor.moveToPosition(position);
        i = cursor.getLong(0);

        return i;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        CDH.close();
    }

    public void send(View v){
        String s =  messageBox.getText().toString();
        chat.add(s);
        db.execSQL("INSERT INTO demo (key_message) VALUES (\"" + s +"\");");
        adapter.notifyDataSetChanged(); //this restarts the process of

        Log.d("Chat", chat.get(0).toString());
        //adapter.getView(adapter.getCount(), null ,listView );


        messageBox.setText("");
    }


    public class ChatAdapter extends ArrayAdapter<String>{

        private ArrayList<String> lst;
        private int source;
        private Context context;

        public ChatAdapter(Context ctx, int resource, ArrayList<String> l) {
            super(ctx,resource, l );
            context =ctx;
            lst = l;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){

            LayoutInflater inflater = LayoutInflater.from(context);
            //convertView = inflater.inflate(source, parent, false);

            View result = null ;

            if(position%2 == 0)

                result = inflater.inflate(R.layout.chat_row_incoming, null);

            else

                result = inflater.inflate(R.layout.chat_row_outgoing, null);


            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(   getItem(position)  ); // get the string at position
            return result;

        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        public String getItem(int position){
            return lst.get(position);
        }
    }



}

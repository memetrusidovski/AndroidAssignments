package com.example.androidassignments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public ChatDatabaseHelper(Context ctx) {
        super(ctx, "Messages.db", null, 4);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"demo\" (\n" +
            "\t\"KEY_ID\"\t INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"KEY_MESSAGE\"\t INTEGER);"
        );

        Log.d("Database", "Made The Table");
        Log.i("ChatDatabaseHelper", "Calling onCreate");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String TABLE_NAME = "demo";
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        db.execSQL("CREATE TABLE \"demo\" (\n" +
            "\t\"KEY_ID\"\t INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"KEY_MESSAGE\"\t INTEGER);"
        );


        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + i + " newVersion=" + i1);
        Log.d("Database", "Upgrade The Table");
    }
}

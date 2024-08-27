package com.example.immune_keeper2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telephony.SmsManager;
import android.widget.Toast;

public class ConnectionHelper extends SQLiteOpenHelper {

    public ConnectionHelper(Context context) {
        super(context, "child.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db ) {
        db.execSQL("create table users(name varchar(20),fname varchar(20),mname varchar(20),dob varchar(20),phone varchar(20) UNIQUE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }

    public long saveNewUserData(String name, String fname, String mname, String dob, String phone, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("fname", fname);
        cv.put("mname", mname);
        cv.put("dob", dob);
        cv.put("phone", phone);
        long recordid = db.insert("users", null, cv);
        db.close(); // Close the database connection

        // After inserting data, send a message
        if (recordid > 0) {
            sendSMS(context, phone, "Your child has been enrolled to Immune Keeper successfully!");
        }

        return recordid;
    }
    private void sendSMS(Context context, String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(context, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        } catch (SecurityException e) {
            // Handle permission-related errors
            Toast.makeText(context, "Permission denied to send SMS", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other exceptions
            Toast.makeText(context, "SMS failed to send", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    public Cursor readalldata() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", null, null, null, null, null, null);
        return cursor;
    }

}

package com.example.sqlitepractice.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "shelter.dp";

    PetContract.petEntry petEntry= new PetContract.petEntry();



    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + petEntry.TABLE_NAME + " ("
                + petEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + petEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
                + petEntry.COLUMN_PET_BREED + " TEXT, "
                + petEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL, "
                + petEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_PETS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

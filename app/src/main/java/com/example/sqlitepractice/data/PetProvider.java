package com.example.sqlitepractice.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class PetProvider extends ContentProvider {


    DbHelper mDbHelper;
    @Override
    public boolean onCreate() {
        mDbHelper = new DbHelper(getContext());
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }


    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        return 0;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


}

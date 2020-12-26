package com.example.sqlitepractice;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlitepractice.data.DbHelper;
import com.example.sqlitepractice.data.PetContract.petEntry;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    ListView list;
    FloatingActionButton fab;
    public DbHelper mDbHelper;
    ArrayList<PetAttribute> attributes;
    PetAdapter petAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MaterialToolbar toolbar =(MaterialToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        attributes= new ArrayList<PetAttribute>();
        list=(ListView)findViewById(R.id.list_view);
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        petAdapter= new PetAdapter(this,attributes);
        list.setAdapter(petAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFAB();
            }
        });


        mDbHelper= new DbHelper(this);
    }

    public void openFAB()
    {
        Intent intent;
        intent = new Intent(this, EditorActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.first_menu, menu);
        return true;
    }


    private void insertPet() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(petEntry.COLUMN_PET_NAME, "Toto");
        values.put(petEntry.COLUMN_PET_BREED, "Terrier");
        values.put(petEntry.COLUMN_PET_GENDER, petEntry.GENDER_MALE);
        values.put(petEntry.COLUMN_PET_WEIGHT, 7);

        long newRowId = db.insert(petEntry.TABLE_NAME, null, values);
        String rid = String.valueOf(newRowId);
        Toast.makeText(this, rid , Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.dummydata:
                insertPet();

                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.delete_all:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }


    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                petEntry._ID,
                petEntry.COLUMN_PET_NAME,
                petEntry.COLUMN_PET_BREED,
                petEntry.COLUMN_PET_GENDER,
                petEntry.COLUMN_PET_WEIGHT };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                petEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order



        try {

            int idColumnIndex = cursor.getColumnIndex(petEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(petEntry.COLUMN_PET_NAME);
            int breedColumnIndex = cursor.getColumnIndex(petEntry.COLUMN_PET_BREED);
            int genderColumnIndex = cursor.getColumnIndex(petEntry.COLUMN_PET_GENDER);
            int weightColumnIndex = cursor.getColumnIndex(petEntry.COLUMN_PET_WEIGHT);

            String g;

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentBreed = cursor.getString(breedColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                int currentWeight = cursor.getInt(weightColumnIndex);

                if(currentGender==0)
                {
                    g="Unknown Gender";
                }
                else if(currentGender==1)
                {
                    g="Male";
                }
                else
                {
                    g="Female";
                }

                attributes.add(new PetAttribute(currentID,currentName,currentBreed,g,currentWeight));
            }
        } finally {

            cursor.close();
        }
    }


}
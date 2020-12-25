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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MaterialToolbar toolbar =(MaterialToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        attributes= new ArrayList<PetAttribute>();
        list=(ListView)findViewById(R.id.list_view);
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);

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
                displayDatabaseInfo();
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

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
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
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.


            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(petEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(petEntry.COLUMN_PET_NAME);
            int breedColumnIndex = cursor.getColumnIndex(petEntry.COLUMN_PET_BREED);
            int genderColumnIndex = cursor.getColumnIndex(petEntry.COLUMN_PET_GENDER);
            int weightColumnIndex = cursor.getColumnIndex(petEntry.COLUMN_PET_WEIGHT);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentBreed = cursor.getString(breedColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                int currentWeight = cursor.getInt(weightColumnIndex);

                attributes.add(new PetAttribute(currentID,currentName,currentBreed,currentGender,currentWeight));
            }
        } finally {

            cursor.close();
        }
    }


}
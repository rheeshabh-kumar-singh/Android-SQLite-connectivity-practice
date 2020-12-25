package com.example.sqlitepractice;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sqlitepractice.data.DbHelper;
import com.example.sqlitepractice.data.PetContract.petEntry;
import com.google.android.material.appbar.MaterialToolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;



public class EditorActivity extends AppCompatActivity {


    private EditText mNameEditText;
    private EditText mBreedEditText;
    private EditText mWeightEditText;
    private Spinner mGenderSpinner;
    private int mGender = petEntry.GENDER_UNKNOWN;
    public DbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        MaterialToolbar toolbar =(MaterialToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mNameEditText = (EditText) findViewById(R.id.edit_pet_name);
        mBreedEditText = (EditText) findViewById(R.id.edit_pet_breed);
        mWeightEditText = (EditText) findViewById(R.id.edit_pet_weight);
        mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);
        mDbHelper= new DbHelper(this);

        setupSpinner();
    }


    private void setupSpinner() {
        ArrayAdapter<CharSequence> genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mGenderSpinner.setAdapter(genderSpinnerAdapter);



        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = petEntry.GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = petEntry.GENDER_FEMALE;
                    } else {
                        mGender = petEntry.GENDER_UNKNOWN;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = petEntry.GENDER_UNKNOWN;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);


        return true;

    }



    private void insertPet() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        String mName = mNameEditText.getText().toString().trim();
        String mBreed = mBreedEditText.getText().toString().trim();
        int mWeight = Integer.parseInt(mWeightEditText.getText().toString().trim());


        ContentValues values = new ContentValues();
        values.put(petEntry.COLUMN_PET_NAME, mName);
        values.put(petEntry.COLUMN_PET_BREED, mBreed);
        values.put(petEntry.COLUMN_PET_GENDER, mGender);
        values.put(petEntry.COLUMN_PET_WEIGHT, mWeight);

        long newRowId = db.insert(petEntry.TABLE_NAME, null, values);
        String rid = String.valueOf(newRowId);
        Toast.makeText(this, rid , Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.save:
                insertPet();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.delete_option:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
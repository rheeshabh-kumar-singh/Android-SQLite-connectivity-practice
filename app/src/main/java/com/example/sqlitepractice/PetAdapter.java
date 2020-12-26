package com.example.sqlitepractice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PetAdapter extends ArrayAdapter<PetAttribute> {
    public PetAdapter(@NonNull Context context, ArrayList<PetAttribute> attributes) {
        super(context,0, attributes);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.pet_view,parent,false);
        }

        PetAttribute current=getItem(position);

        TextView pet_id, pet_name, pet_gender, pet_weight, pet_breed;
        pet_id = (TextView) listItemView.findViewById(R.id.pet_id);
        pet_name = (TextView) listItemView.findViewById(R.id.pet_name);
        pet_gender = (TextView) listItemView.findViewById(R.id.pet_gender);
        pet_breed = (TextView) listItemView.findViewById(R.id.pet_breed);
        pet_weight = (TextView) listItemView.findViewById(R.id.pet_weight);
        Log.i("pet_id", String.valueOf(current.getpID()));

        pet_id.setText(String.valueOf(current.getpID()));
        pet_name.setText(current.getpName().toString());
        pet_gender.setText(current.getpGender().toString());
        pet_breed.setText(current.getpBreed().toString());
        pet_weight.setText(String.valueOf(current.getpWeight()));


        return listItemView;
    }

}

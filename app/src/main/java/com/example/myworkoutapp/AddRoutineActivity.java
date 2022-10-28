package com.example.myworkoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myworkoutapp.Models.RoutineModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRoutineActivity extends AppCompatActivity {

    String workoutPlanId;

    Button btnAdd;
    EditText edtRoutineName;
    Spinner spinRoutineDay;

    String days;
    boolean selected = true;

    // Firebase Variables
    // Database Ref
    FirebaseDatabase firebaseDatabase;
    // Firebase Ref
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);
        initViews();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Routines");

        Intent intent = getIntent();

        workoutPlanId = intent.getStringExtra("workoutPlanId");

        spinRoutineDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Select a Day for your Routine!")) {
                    selected = false;
                }
                else {
                    selected = true;
                    days = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(AddRoutineActivity.this, "" + days, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        btnAdd.setOnClickListener(view -> {
            validateData();
        });
    }

    private void validateData() {
        String name = edtRoutineName.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Enter a Name for your Routine!", Toast.LENGTH_SHORT).show();
        }
        else if (!selected) {
            Toast.makeText(this, "Select a Day for your Routine!", Toast.LENGTH_SHORT).show();
        }
        else {
            addRoutine(name);
        }
    }

    private void addRoutine(String name) {
        String timestamp = Long.toString(System.currentTimeMillis());

        String uploadID = "" + timestamp;

        RoutineModel routine = new RoutineModel(uploadID, name, days, workoutPlanId, timestamp, 0);

        databaseReference.child(uploadID)
                .setValue(routine)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(AddRoutineActivity.this, "Data Successfully Added!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(AddRoutineActivity.this, "Failed to Add Data Due to " + e + " !", Toast.LENGTH_SHORT).show());
    }

    private void initViews() {
        btnAdd = findViewById(R.id.btnAdd);
        edtRoutineName = findViewById(R.id.edtRoutineName);
        spinRoutineDay = findViewById(R.id.spinRoutineDay);

        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinRoutineDay.setAdapter(ad);
    }
}
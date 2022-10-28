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

import com.example.myworkoutapp.Models.ExerciseModel;
import com.example.myworkoutapp.Models.RoutineModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddExerciseActivity extends AppCompatActivity {

    EditText edtExerciseName, edtSets, edtReps, edtRest;
    Spinner spinMuscleGroup, spinEquipment;
    Button btnAdd;

    String muscleGroup, equipment;
    boolean muscleGroupSelected = true, equipmentSelected = true;

    // Firebase Variables
    // Database Ref
    FirebaseDatabase firebaseDatabase;
    // Firebase Ref
    DatabaseReference databaseReference;

    String routineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        initViews();

        Intent intent = getIntent();

        routineId = intent.getStringExtra("routineId");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Exercises");

        btnAdd.setOnClickListener(view -> validateData());

        spinMuscleGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Select a Muscle Group!")) {
                    muscleGroupSelected = false;
                }
                else {
                    muscleGroupSelected = true;
                    muscleGroup = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(AddExerciseActivity.this, "" + muscleGroup, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinEquipment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Select Equipment!")) {
                    equipmentSelected = false;
                }
                else {
                    equipmentSelected = true;
                    equipment = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(AddExerciseActivity.this, "" + equipment, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void validateData() {
        String name = edtExerciseName.getText().toString().trim();
        String sets = edtSets.getText().toString();
        String reps = edtReps.getText().toString();
        String rest = edtRest.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Enter a Name for your Exercise!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(sets)) {
            Toast.makeText(this, "Enter a Number of Sets!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(reps)) {
            Toast.makeText(this, "Enter a Number of Reps!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(rest)) {
            Toast.makeText(this, "Enter the Rest Time for Each Set!", Toast.LENGTH_SHORT).show();
        }
        else if (!muscleGroupSelected) {
            Toast.makeText(this, "Select a Muscle Group!", Toast.LENGTH_SHORT).show();
        }
        else if (!equipmentSelected) {
            Toast.makeText(this, "Select Equipment!", Toast.LENGTH_SHORT).show();
        }
        else {
            addRoutine(name, sets, reps, rest);
        }
    }

    private void addRoutine(String name, String sets, String reps, String rest) {

        String timestamp = Long.toString(System.currentTimeMillis());

        String uploadID = "" + timestamp;

        ExerciseModel exerciseModel = new ExerciseModel(name, muscleGroup, equipment, routineId, 0, Integer.parseInt(sets), Integer.parseInt(reps), Integer.parseInt(rest));

        databaseReference.child(uploadID)
                .setValue(exerciseModel)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(AddExerciseActivity.this, "Data Successfully Added!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(AddExerciseActivity.this, "Failed to Add Data Due to " + e + " !", Toast.LENGTH_SHORT).show());
    }

    private void initViews() {
        edtExerciseName = findViewById(R.id.edtExerciseName);
        edtSets = findViewById(R.id.edtSets);
        edtReps = findViewById(R.id.edtReps);
        edtRest = findViewById(R.id.edtRest);

        // Setup for Muscle Group Spinner
        spinMuscleGroup = findViewById(R.id.spinMuscleGroup);

        ArrayAdapter<CharSequence> spinMGAdapter = ArrayAdapter.createFromResource(this, R.array.muscleGroups, android.R.layout.simple_spinner_item);

        spinMGAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinMuscleGroup.setAdapter(spinMGAdapter);

        // Setup for Equipment Spinner
        spinEquipment = findViewById(R.id.spinEquipment);

        ArrayAdapter<CharSequence> spinEAdapter = ArrayAdapter.createFromResource(this, R.array.equipment, android.R.layout.simple_spinner_item);

        spinEAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinEquipment.setAdapter(spinEAdapter);

        btnAdd = findViewById(R.id.btnAdd);
    }
}
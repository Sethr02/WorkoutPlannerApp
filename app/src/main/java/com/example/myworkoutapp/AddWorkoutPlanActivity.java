package com.example.myworkoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myworkoutapp.Models.WorkoutPlanModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddWorkoutPlanActivity extends AppCompatActivity {

    // Variables for Views
    Button add;
    EditText edtRoutineName;

    // Firebase Variables
    // Database Ref
    FirebaseDatabase firebaseDatabase;
    // Firebase Ref
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout_plan);

        initViews();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("WorkoutPlans");

        add.setOnClickListener(view -> {
            validateData();
        });
    }

    private void validateData() {
        String name = edtRoutineName.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please Enter a Name for your Routine!", Toast.LENGTH_SHORT).show();
        } else {
            addRoutineToFirebase(name);
        }
    }

    private void addRoutineToFirebase(String name) {
        String timestamp = Long.toString(System.currentTimeMillis());

        String uploadID = "" + timestamp;

        WorkoutPlanModel workoutPlanModel = new WorkoutPlanModel(uploadID, name, timestamp, 0);

        databaseReference.child(uploadID)
                .setValue(workoutPlanModel)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(AddWorkoutPlanActivity.this, "Data Successfully Added!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(AddWorkoutPlanActivity.this, "Failed to Add Data Due to " + e + " !", Toast.LENGTH_SHORT).show());
    }

    private void initViews() {
        add = findViewById(R.id.btnAdd);
        edtRoutineName = findViewById(R.id.edtRoutineName);
    }
}
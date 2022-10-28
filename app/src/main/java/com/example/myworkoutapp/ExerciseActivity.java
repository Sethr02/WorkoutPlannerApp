package com.example.myworkoutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myworkoutapp.Adapters.ExerciseAdapter;
import com.example.myworkoutapp.Adapters.WorkoutPlanAdapter;
import com.example.myworkoutapp.Models.ExerciseModel;
import com.example.myworkoutapp.Models.WorkoutPlanModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {

    private ArrayList<ExerciseModel> exerciseArrayList;

    private ExerciseAdapter exerciseAdapter;

    Button btnStartWorkout, btnAddExercise;
    RecyclerView exerciseRv;

    String routineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        initViews();
        loadExercises();

        Intent intent = getIntent();

        routineId = intent.getStringExtra("routineId");

        btnStartWorkout.setOnClickListener(view -> Toast.makeText(ExerciseActivity.this, "Start Workout", Toast.LENGTH_SHORT).show());

        btnAddExercise.setOnClickListener(view -> {
            Intent AddExerciseIntent = new Intent(ExerciseActivity.this, AddExerciseActivity.class);

            AddExerciseIntent.putExtra("routineId", routineId);

            startActivity(AddExerciseIntent);
        });
    }

    private void loadExercises() {
        exerciseArrayList = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Exercises");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                exerciseArrayList.clear();

                for (DataSnapshot ds: snapshot.getChildren()) {
                    ExerciseModel model = ds.getValue(ExerciseModel.class);
                    exerciseArrayList.add(model);
                }
                exerciseRv.setLayoutManager(new LinearLayoutManager(ExerciseActivity.this, LinearLayoutManager.VERTICAL, false));
                exerciseAdapter = new ExerciseAdapter(ExerciseActivity.this, exerciseArrayList);
                exerciseRv.setAdapter(exerciseAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initViews() {
        btnStartWorkout = findViewById(R.id.btnStartWorkout);
        btnAddExercise = findViewById(R.id.btnAddExercise);
        exerciseRv = findViewById(R.id.exerciseRv);
    }
}
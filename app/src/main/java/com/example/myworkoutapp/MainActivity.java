package com.example.myworkoutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.myworkoutapp.Adapters.WorkoutPlanAdapter;
import com.example.myworkoutapp.Models.WorkoutPlanModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<WorkoutPlanModel> workoutPlanArrayList;

    private WorkoutPlanAdapter workoutPlanAdapter;

    Button addRoutine;
    RecyclerView workoutPlanRv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();

        loadWorkoutPlans();

        addRoutine.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AddWorkoutPlanActivity.class)));
    }

    private void loadWorkoutPlans() {
        workoutPlanArrayList = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("WorkoutPlans");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                workoutPlanArrayList.clear();

                for (DataSnapshot ds: snapshot.getChildren()) {
                    WorkoutPlanModel model = ds.getValue(WorkoutPlanModel.class);
                    workoutPlanArrayList.add(model);
                }
                workoutPlanRv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                workoutPlanAdapter = new WorkoutPlanAdapter(MainActivity.this, workoutPlanArrayList);
                workoutPlanRv.setAdapter(workoutPlanAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initViews() {
        addRoutine = findViewById(R.id.btnAddWorkoutPlan);
        workoutPlanRv = findViewById(R.id.workoutPlanRv);
    }
}
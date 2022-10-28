package com.example.myworkoutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.myworkoutapp.Adapters.RoutineAdapter;
import com.example.myworkoutapp.Models.RoutineModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RoutineActivity extends AppCompatActivity {

    private ArrayList<RoutineModel> routineArrayList;

    private RoutineAdapter routineAdapter;

    String workoutPlanId;

    Button btnAddRoutine;
    RecyclerView routineRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        initViews();

        Intent intent = getIntent();

        workoutPlanId = intent.getStringExtra("workoutPlanId");

        loadRoutines();

        btnAddRoutine.setOnClickListener(view -> {
            Intent AddRoutineIntent = new Intent(RoutineActivity.this, AddRoutineActivity.class);

            AddRoutineIntent.putExtra("workoutPlanId", workoutPlanId);

            startActivity(AddRoutineIntent);
        });
    }

    private void loadRoutines() {
        routineArrayList = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Routines");

        ref.orderByChild("workoutPlanId").equalTo(workoutPlanId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        routineArrayList.clear();

                        for (DataSnapshot ds : snapshot.getChildren()) {
                            RoutineModel model = ds.getValue(RoutineModel.class);
                            routineArrayList.add(model);
                        }
                        routineRv.setLayoutManager(new LinearLayoutManager(RoutineActivity.this, LinearLayoutManager.VERTICAL, false));
                        routineAdapter = new RoutineAdapter(RoutineActivity.this, routineArrayList);
                        routineRv.setAdapter(routineAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void initViews() {
        btnAddRoutine = findViewById(R.id.btnAddRoutine);
        routineRv = findViewById(R.id.routineRv);
    }
}
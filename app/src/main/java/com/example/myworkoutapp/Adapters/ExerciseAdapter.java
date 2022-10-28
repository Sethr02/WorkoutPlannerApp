package com.example.myworkoutapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myworkoutapp.ExerciseActivity;
import com.example.myworkoutapp.Models.ExerciseModel;
import com.example.myworkoutapp.Models.RoutineModel;
import com.example.myworkoutapp.R;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseHolder>{

    Context context;

    public ArrayList<ExerciseModel> exerciseArrayList;

    public ExerciseAdapter(Context context, ArrayList<ExerciseModel> exerciseArrayList) {
        this.context = context;
        this.exerciseArrayList = exerciseArrayList;
    }

    @NonNull
    @Override
    public ExerciseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_row, parent, false);
        return new ExerciseAdapter.ExerciseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseHolder holder, int position) {
        ExerciseModel model = exerciseArrayList.get(position);

        //String id = model.getId();

        String exerciseName = model.getExerciseName();
        String muscleGroup = model.getMuscleGroup();

        StringBuilder setsAndReps = new StringBuilder();

        setsAndReps.append(model.getSets())
                .append(" Sets ")
                .append(model.getReps())
                .append(" Reps");

        holder.exerciseNameTv.setText(exerciseName);
        holder.muscleGroupTv.setText(muscleGroup);
        holder.setsAndRepsTv.setText(setsAndReps);

        /*holder.itemView.setOnClickListener(view -> {
            Intent exerciseIntent = new Intent(context, ExerciseActivity.class);

            //exerciseIntent.putExtra("routineId", id);

            context.startActivity(exerciseIntent);
        });*/
    }

    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }

    class ExerciseHolder extends RecyclerView.ViewHolder{

        TextView exerciseNameTv, muscleGroupTv, setsAndRepsTv;

        public ExerciseHolder(@NonNull View itemView) {
            super(itemView);

            exerciseNameTv = itemView.findViewById(R.id.exerciseNameTv);
            muscleGroupTv = itemView.findViewById(R.id.muscleGroupTv);
            setsAndRepsTv = itemView.findViewById(R.id.setsAndRepsTv);
        }
    }
}

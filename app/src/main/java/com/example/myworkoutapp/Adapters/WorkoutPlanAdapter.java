package com.example.myworkoutapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myworkoutapp.R;
import com.example.myworkoutapp.RoutineActivity;
import com.example.myworkoutapp.Models.WorkoutPlanModel;

import java.util.ArrayList;

public class WorkoutPlanAdapter extends RecyclerView.Adapter<WorkoutPlanAdapter.WorkoutPlanHolder>{

    Context context;

    public ArrayList<WorkoutPlanModel> workoutPlanArrayList;

    public WorkoutPlanAdapter(Context context, ArrayList<WorkoutPlanModel> workoutPlanArrayList) {
        this.context = context;
        this.workoutPlanArrayList = workoutPlanArrayList;
    }

    @NonNull
    @Override
    public WorkoutPlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workoutplan_row, parent, false);
        return new WorkoutPlanAdapter.WorkoutPlanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutPlanHolder holder, int position) {
        WorkoutPlanModel model = workoutPlanArrayList.get(position);

        String id = model.getId();

        String workoutName = model.getRoutineName();

        String noOfWorkouts = String.valueOf(model.getNumOfWorkouts());

        holder.workoutNameTv.setText(workoutName);

        //holder.noOfWorkoutsTv.setText(noOfWorkouts);

        holder.itemView.setOnClickListener(view -> {
            Intent routineIntent = new Intent(context, RoutineActivity.class);

            routineIntent.putExtra("workoutPlanId", id);

            context.startActivity(routineIntent);
        });
    }

    @Override
    public int getItemCount() {
        return workoutPlanArrayList.size();
    }

    class WorkoutPlanHolder extends RecyclerView.ViewHolder{

        TextView workoutNameTv, noOfWorkoutsTv;

        public WorkoutPlanHolder(@NonNull View itemView) {
            super(itemView);

            workoutNameTv = itemView.findViewById(R.id.workoutNameTv);

            noOfWorkoutsTv = itemView.findViewById(R.id.noOfWorkoutsTv);
        }
    }
}

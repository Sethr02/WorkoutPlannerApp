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
import com.example.myworkoutapp.R;
import com.example.myworkoutapp.Models.RoutineModel;

import java.util.ArrayList;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineHolder> {

    Context context;

    public ArrayList<RoutineModel> routineArrayList;

    public RoutineAdapter(Context context, ArrayList<RoutineModel> routineArrayList) {
        this.context = context;
        this.routineArrayList = routineArrayList;
    }

    @NonNull
    @Override
    public RoutineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routine_row, parent, false);
        return new RoutineAdapter.RoutineHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineHolder holder, int position) {
        RoutineModel model = routineArrayList.get(position);

        String id = model.getId();

        String routineName = model.getRoutineName();
        String routineDay = model.getRoutineDay();

        holder.routineNameTv.setText(routineName);
        holder.routineDayTv.setText(routineDay.substring(0, 3));

        holder.itemView.setOnClickListener(view -> {
            Intent exerciseIntent = new Intent(context, ExerciseActivity.class);

            exerciseIntent.putExtra("routineId", id);

            context.startActivity(exerciseIntent);
        });
    }

    @Override
    public int getItemCount() {
        return routineArrayList.size();
    }

    class RoutineHolder extends RecyclerView.ViewHolder {

        TextView routineNameTv, routineDayTv;

        public RoutineHolder(@NonNull View itemView) {
            super(itemView);

            routineNameTv = itemView.findViewById(R.id.routineNameTv);

            routineDayTv = itemView.findViewById(R.id.routineDayTv);
        }
    }
}

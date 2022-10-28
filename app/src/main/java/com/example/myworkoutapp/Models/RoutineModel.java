package com.example.myworkoutapp.Models;

public class RoutineModel {
    private String id;
    private String routineName;
    private String routineDay;
    private String workoutPlanId;
    private String timestamp;
    private int numOfExercises;

    public RoutineModel() {
    }

    public RoutineModel(String id, String routineName, String routineDay, String workoutPlanId, String timestamp, int numOfExercises) {
        this.id = id;
        this.routineName = routineName;
        this.routineDay = routineDay;
        this.workoutPlanId = workoutPlanId;
        this.timestamp = timestamp;
        this.numOfExercises = numOfExercises;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public String getRoutineDay() {
        return routineDay;
    }

    public void setRoutineDay(String routineDay) {
        this.routineDay = routineDay;
    }

    public String getWorkoutPlanId() {
        return workoutPlanId;
    }

    public void setWorkoutPlanId(String workoutPlanId) {
        this.workoutPlanId = workoutPlanId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getNumOfExercises() {
        return numOfExercises;
    }

    public void setNumOfExercises(int numOfExercises) {
        this.numOfExercises = numOfExercises;
    }
}

package com.example.myworkoutapp.Models;

public class WorkoutPlanModel {
    private String id;
    private String workoutPlanName;
    private String timestamp;
    private int numOfWorkouts;

    public WorkoutPlanModel() {
    }

    public WorkoutPlanModel(String id, String workoutPlanName, String timestamp, int numOfWorkouts) {
        this.id = id;
        this.workoutPlanName = workoutPlanName;
        this.timestamp = timestamp;
        this.numOfWorkouts = numOfWorkouts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoutineName() {
        return workoutPlanName;
    }

    public void setRoutineName(String workoutPlanName) {
        this.workoutPlanName = workoutPlanName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getNumOfWorkouts() {
        return numOfWorkouts;
    }

    public void setNumOfWorkouts(int numOfWorkouts) {
        this.numOfWorkouts = numOfWorkouts;
    }
}

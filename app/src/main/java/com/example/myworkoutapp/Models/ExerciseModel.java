package com.example.myworkoutapp.Models;

public class ExerciseModel {
    private String exerciseName;
    private String muscleGroup;
    private String equipment;
    private String routineId;
    private int duration;
    private int sets;
    private int reps;
    private int rest;

    public ExerciseModel() {
    }

    public ExerciseModel(String exerciseName, String muscleGroup, String equipment, String routineId, int duration, int sets, int reps, int rest) {
        this.exerciseName = exerciseName;
        this.muscleGroup = muscleGroup;
        this.equipment = equipment;
        this.routineId = routineId;
        this.duration = duration;
        this.sets = sets;
        this.reps = reps;
        this.rest = rest;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getRoutineId() {
        return routineId;
    }

    public void setRoutineId(String routineId) {
        this.routineId = routineId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }
}

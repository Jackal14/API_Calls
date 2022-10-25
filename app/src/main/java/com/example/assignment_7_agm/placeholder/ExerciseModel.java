package com.example.assignment_7_agm.placeholder;

import com.google.gson.annotations.SerializedName;

public class ExerciseModel {
    //Strings to contain the data we'll be storing in our Json
    @SerializedName("bodyPart")
    private String bodyPartName;
    @SerializedName("equipment")
    private String equipmentNeeded;
    @SerializedName("gifUrl")
    private String gif;
    @SerializedName("id")
    private String idNum;
    @SerializedName("name")
    private String exerciseName;
    @SerializedName("target")
    private String targetBodyPart;


    //Initializer for model
    public ExerciseModel(final String bodyPart, final String equipment, final String gifUrl,final String id, final String name, final String target ) {
        this.bodyPartName = bodyPart;
        this.equipmentNeeded = equipment;
        this.gif = gifUrl;
        this.idNum = id;
        this.exerciseName = name;
        this.targetBodyPart = target;

    }

    //Getters
    public String getBodyPartName() {return bodyPartName;}
    public String getEquipmentNeeded() {return equipmentNeeded;}
    public String getExerciseName() {return exerciseName;}



    //Setters
    public void setBodyPartName(final String bodyPart) {this.bodyPartName = bodyPart;}

}

package com.goalup.madelinemerced;

import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Doug
 * @Assignment Name: com.madelinemerced.mlmxms
 * @Date: Mar 31, 2019
 * @Subclass Storage Description:
 */
//Imports
//Begin Subclass Storage
public class Storage implements Externalizable {

    private static ArrayList<Storage> goals;
    
    private String goal;
    private String points;

    public static void setGoals(ArrayList<Storage> goals) {
        Storage.goals = goals;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
    
    @Override
    public int getVersion(){
        return 1;
    }
    
    @Override
    public void externalize(DataOutputStream out) throws IOException{
        Util.writeUTF(goal, out);
        Util.writeUTF(points, out);
    }
    
    @Override
    public void internalize(int version, DataInputStream in) throws IOException {
        goal = Util.readUTF(in);
        points = Util.readUTF(in);
    }
     
    @Override
    public String getObjectId() {
        return "Storage";
    }
    
  public static ArrayList<Storage> getGoals() {
           if(goals == null) {
            goals = (ArrayList<Storage>)com.codename1.io.Storage.getInstance().readObject("storage");
            if(goals == null) {
                goals = new ArrayList<>();
            }
        }
        return goals;
    }
  
   public void saveNote() {
        if(!goals.contains(this)) {
            goals.add(this);
        }
        com.codename1.io.Storage.getInstance().writeObject("storage", goals);
    }
    
    public void clearStorage(){
        com.codename1.io.Storage.getInstance().clearStorage();
    }
} //End Subclass Storage

package com.goalup.madelinemerced;

import com.codename1.io.Externalizable;
import com.codename1.io.Storage;
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
//Begin Subclass MyObject
public class MyObject implements Externalizable {

    private static ArrayList<MyObject> goals;
        private static ArrayList<MyObject> points;

    private static ArrayList<MyObject> rewards;
    Storage s = new Storage();
    private String goal;
    private String gPoints;
    private String reward;
    private String rPoints;

    public static void setGoals(ArrayList<MyObject> goals) {
        MyObject.goals = goals;
    }

    public static void setRewards(ArrayList<MyObject> rewards) {
        MyObject.rewards = rewards;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getRPoints() {
        return rPoints;
    }

    public void setRPoints(String rPoints) {
        this.rPoints = rPoints;
    }

    public String getGPoints() {
        return gPoints;
    }

    public void setGPoints(String gPoints) {
        this.gPoints = gPoints;
    }

    @Override
    public int getVersion() {
        return 1;
    }

    public static ArrayList<MyObject> getGoals() {
        if (goals == null) {
            goals = (ArrayList<MyObject>) com.codename1.io.Storage.getInstance().readObject("storage");
            if (goals == null) {
                goals = new ArrayList<>();
            }
        }
        return goals;
    }
      public static ArrayList<MyObject> getPoints() {
        if (points == null) {
            points = (ArrayList<MyObject>) com.codename1.io.Storage.getInstance().readObject("storage");
            if (points == null) {
                points = new ArrayList<>();
            }
        }
        return goals;
    }

    public static ArrayList<MyObject> getRewards() {
        if (rewards == null) {
            rewards = (ArrayList<MyObject>) com.codename1.io.Storage.getInstance().readObject("rewards");
            if (rewards == null) {
                rewards = new ArrayList<>();
            }
        }
        return rewards;
    }

    public void saveGoals() {
        if (!goals.contains(this)) {
            goals.add(this);
        }
//        com.codename1.io.Storage.getInstance().writeObject("", goals);
    }

    public void saveRewards() {
        if (!rewards.contains(this)) {
            rewards.add(this);
        }
//        com.codename1.io.Storage.getInstance().writeObject("", rewards);
    }

    public void clearStorage() {
        com.codename1.io.Storage.getInstance().clearStorage();
    }

    @Override
    public void externalize(DataOutputStream out) throws IOException {
        Util.writeUTF(goal, out);
        Util.writeUTF(gPoints, out);
        Util.writeUTF(reward, out);
        Util.writeUTF(rPoints, out);
    }

    @Override
    public void internalize(int version, DataInputStream in) throws IOException {
        goal = Util.readUTF(in);
        gPoints = Util.readUTF(in);
        reward = Util.readUTF(in);
        rPoints = Util.readUTF(in);
    }

    @Override
    public String getObjectId() {
        return "" + "";
    }

} //End Subclass MyObject

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
 * @Author Name: Madeline Merced
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Apr 14, 2019
 * @Subclass MyObj Description: 
 */
//Imports

////Begin Subclass MyObj
public class MyObj implements Externalizable  {

    
    private static ArrayList<MyObj> points;
    private static ArrayList<MyObj> rewards;

    private String reward;
    private String rPoints;


    public static void setRewards(ArrayList<MyObj> rewards) {
        MyObj.rewards = rewards;
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

    @Override
    public int getVersion() {
        return 1;
    }

      public static ArrayList<MyObj> getPoints() {
        if (points == null) {
            points = (ArrayList<MyObj>) com.codename1.io.Storage.getInstance().readObject("rewards");
            if (points == null) {
                points = new ArrayList<>();
            }
        }
        return points;
    }

    public static ArrayList<MyObj> getRewards() {
        if (rewards == null) {
            rewards = (ArrayList<MyObj>) com.codename1.io.Storage.getInstance().readObject("rewards");
            if (rewards == null) {
                rewards = new ArrayList<>();
            }
        }
        return rewards;
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
        Util.writeUTF(reward, out);
        Util.writeUTF(rPoints, out);
    }

    @Override
    public void internalize(int version, DataInputStream in) throws IOException {
        reward = Util.readUTF(in);
        rPoints = Util.readUTF(in);
    }

    @Override
    public String getObjectId() {
        return "Rewards";
    }
}//End Subclass MyObj
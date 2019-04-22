package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass MyObject Description: Uses codenameone externalizable class to save
 * objects to persistent storage when called in subclasses
 */
//Imports
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.codename1.io.Externalizable;
import static com.codename1.io.Util.readUTF;
import static com.codename1.io.Util.writeUTF;
import static com.codename1.io.Util.readObject;
import static com.codename1.io.Util.writeObject;
import static com.codename1.io.Storage.getInstance;

//Begin MyObject Subclass
public class MyObject implements Externalizable {

    //ArrayLists for the three main storage types
    private static ArrayList<MyObject> goals;
    private static ArrayList<MyObject> rewards;
    private static ArrayList<MyObject> profile;

    //HashMap for goalPair storage
    private HashMap<String, String> goalPair;

    //Variables
    private String goal;
    private String type;
    private String reward;
    private String rPoints;
    private String gPoints;
    private String checkbox;

    /**
     * Method setGoals: Takes ArrayList to update goal variable
     *
     * @param goals
     */
    public static void setGoals(ArrayList<MyObject> goals) {
        goals = goals;
    }

    /**
     * Method getGoals: Reads goals variable values
     *
     * @return
     */
    public static ArrayList<MyObject> getGoals() {
        if (goals == null) {
            goals = (ArrayList<MyObject>) getInstance().readObject("storage");
            if (goals == null) {
                goals = new ArrayList<>();
            }
        }
        return goals;
    }

    /**
     * Method setRewards: Takes ArrayList to update rewards variable
     *
     * @param rewards
     */
    public static void setRewards(ArrayList<MyObject> rewards) {
        rewards = rewards;
    }

    /**
     * Method getRewards: Reads rewards variable values
     *
     * @return
     */
    public static ArrayList<MyObject> getRewards() {
        if (rewards == null) {
            rewards = (ArrayList<MyObject>) getInstance().readObject("rewards");
            if (rewards == null) {
                rewards = new ArrayList<>();
            }
        }
        return rewards;
    }

    /**
     * Method setProfile: Takes ArrayList to update profile variable
     *
     * @param profile
     */
    public static void setProfile(ArrayList<MyObject> profile) {
        profile = profile;
    }

    /**
     * Method getProfile: Reads profile storage object into profile arraylist
     *
     * @return
     */
    public static ArrayList<MyObject> getProfile() {
        if (profile == null) {
            profile = (ArrayList<MyObject>) getInstance().readObject("profile");
            if (profile == null) {
                profile = new ArrayList<>();
            }
        }
        return profile;
    }

    /**
     * Method setGoalPair: Takes ArrayList to update pair goalPair variable
     *
     * @param pair
     */
    public void setGoalPair(HashMap<String, String> pair) {
        this.goalPair = pair;
    }

    /**
     * Method getGoalPair: Reads goalPair variable values
     *
     * @return
     */
    public HashMap<String, String> getGoalPair() {
        return goalPair;
    }

    /**
     * Method setType: Takes string to update type variable
     *
     * @param t
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method getType: Reads type variable
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Method setGoal: Takes string to update goal variable
     *
     * @param goal
     */
    public void setGoal(String goal) {
        this.goal = goal;
    }

    /**
     * Method getGoal: Reads goal variable
     *
     * @return
     */
    public String getGoal() {
        return goal;
    }

    /**
     * Method setReward: Takes string to update reward variable
     *
     * @param reward
     */
    public void setReward(String reward) {
        this.reward = reward;
    }

    /**
     * Method getReward: Reads reward variable
     *
     * @return
     */
    public String getReward() {
        return reward;
    }

    /**
     * Method setRPoints: Takes string to update rPoints variable
     *
     * @param rPoints
     */
    public void setRPoints(String rPoints) {
        this.rPoints = rPoints;
    }

    /**
     * Method getRPoints: Reads rPoints variable
     *
     * @return
     */
    public String getRPoints() {
        return rPoints;
    }

    /**
     * Method setGPoints: Takes string to update gPoints variable
     *
     * @param gPoints
     */
    public void setGPoints(String gPoints) {
        this.gPoints = gPoints;
    }

    /**
     * Method getGPoints: Reads gPoints variable
     *
     * @return
     */
    public String getGPoints() {
        return gPoints;
    }

    /**
     * Method getVersion: Returns the version for the current persistence code
     *
     * @return
     */
    @Override
    public int getVersion() {
        return 1;
    }

    /**
     * Method saveGoals: Saves goals to goals list
     */
    public void saveGoals() {
        if (!goals.contains(this)) {
            goals.add(this);
        }
    }

    /**
     * Method saveRewards: Saves rewards to rewards list
     */
    public void saveRewards() {
        if (!rewards.contains(this)) {
            rewards.add(this);
        }
    }

    /**
     * Method saveProfile: Saves profile to profile list
     */
    public void saveProfile() {
        if (!profile.contains(this)) {
            profile.add(this);
        }
    }

    /**
     * Method clearStorage: Clears storage
     */
    public void clearStorage() {
        getInstance().clearStorage();
    }

    /**
     * Method externalize: Stores the listed object states
     *
     * @param out
     * @throws IOException
     */
    @Override
    public void externalize(DataOutputStream out) throws IOException {
        writeUTF(goal, out);
        writeUTF(gPoints, out);
        writeUTF(type, out);
        writeUTF(reward, out);
        writeUTF(rPoints, out);
        writeUTF(checkbox, out);
        writeObject(goalPair, out);
        writeObject(profile, out);
    }

    /**
     * Method internalize: Loads and allows the deserialization of the listed
     * objects from the input stream
     *
     * @param version
     * @param in
     * @throws IOException
     */
    @Override
    public void internalize(int version, DataInputStream in) throws IOException {
        goal = readUTF(in);
        gPoints = readUTF(in);
        type = readUTF(in);
        reward = readUTF(in);
        rPoints = readUTF(in);
        checkbox = readUTF(in);
        goalPair = (HashMap<String, String>) readObject(in);
        profile = (ArrayList<MyObject>) readObject(in);
    }

    /**
     * Method getObjectId: Identifies the object when loaded
     *
     * @return
     */
    @Override
    public String getObjectId() {
        return "Storage";
    }

}

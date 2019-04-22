package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass CreateFileGoal Description: Reads storage to return formatted files 
 * 
 */

//Imports
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStream;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Container;
import static com.codename1.io.Log.e;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.io.Util.readToString;
import static com.codename1.io.Storage.getInstance;
import static com.codename1.ui.layouts.BorderLayout.EAST;
import static com.codename1.ui.layouts.BoxLayout.encloseX;
import static com.codename1.ui.layouts.BorderLayout.center;

//Begin Subclass CreateFileGoal
public class CreateFileGoal {

    //Classwide variable
    int pointsValueInt;

    /**
     * Method CreateFileGoal: Reads storage for goal entries and returns them 
     * formatted to the UI
     * 
     * @param res
     * @param file
     * @param t
     * @param dailyTotal
     */
    public void CreateFileGoal(Form res, String file, String t, Label dailyTotal) {

        //Dashboard object
        Dashboard db = new Dashboard();

        //Label to hold file name
        Label goal = new Label(file);
        
        //Labels to hold points and temporary data
        Label points = new Label("");
        Label holder = new Label("");
        
        //Container for holding components
        Container content = center(holder);

        //Hashmap to hold keys and values
        HashMap<String, String> listGoals = new HashMap<>();
        
        //Strings for hashmap
        String entryKey;
        String entryValue = null;
        
        //Counter variable
        int i = 1;

        //Reads in the storage
        try (InputStream is = getInstance().createInputStream(file);) {
            String s = readToString(is, "UTF-8");
            if (s.contains("goal")) {
                if (s.substring(0, 1).contains("0")) {
                    listGoals.put(file, s.substring(1, 3));
                    points.setText(s.substring(1, 3) + " points");
                    if (s.substring(1, 2).contains("0")) {
                        listGoals.put(file, s.substring(2, 3));
                        points.setText(s.substring(2, 3) + " points");
                    }
                } else {
                    listGoals.put(file, s.substring(0, 3));
                    points.setText(s.substring(0, 3) + " points");
                }
                for (Map.Entry<String, String> entry : listGoals.entrySet()) {

                    entryValue = entry.getValue();
                    entryKey = entry.getKey();

                    db.setMap(entry);

                }

                content.add(CENTER, encloseX(goal));
                content.add(EAST, encloseX(points));

            }

        } catch (IOException err) {
            e(err);
        }

        //Adds storage contents with goals to main form
        res.add(content);
    }

} //End Subclass CreateFileGoal

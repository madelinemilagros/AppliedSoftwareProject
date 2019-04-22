package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass CreateFileReward Description: Reads storage to return reward
 * formatted files
 *
 */
//Imports
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStream;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.io.Storage;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import static com.codename1.io.Log.e;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.io.Util.readToString;
import static com.codename1.ui.FontImage.createMaterial;
import static com.codename1.ui.FontImage.MATERIAL_STAR;
import static com.codename1.ui.layouts.BorderLayout.EAST;
import static com.codename1.ui.layouts.BoxLayout.encloseX;
import static com.codename1.ui.layouts.BorderLayout.center;
import static com.codename1.ui.FontImage.MATERIAL_STAR_BORDER;

//Begin Subclass CreateFileReward
public class CreateFileReward {

    /**
     * Method CreateFileReward: Reads storage to return formatted reward files
     *
     * @param res
     * @param file
     * @param type
     */
    public void CreateFileReward(Form res, String file, String type) {

        //Dashboard object
        Dashboard db = new Dashboard();

        //Label to hold file name
        Label reward = new Label(file);

        //Labels to hold points and temporary data
        Label points = new Label("");
        Label holder = new Label("");

        //Create special checkbox with icon for reward entry
        CheckBox c = new CheckBox();
        c.setSelected(true);
        c.setToggle(true);
        c.setTextPosition(LEFT);
        c.setIcon(createMaterial(MATERIAL_STAR_BORDER, "UncheckedIcon", 4));
        c.setPressedIcon(createMaterial(MATERIAL_STAR, "CheckedIcon", 4));

        //Hashmap to hold keys and values
        HashMap<String, String> listRewards = new HashMap<>();

        //Strings for hashmap
        String entryKey;
        String entryValue = null;

        //Container for holding components
        Container content = center(holder);

        //Reads in the storage
        try (InputStream is = Storage.getInstance().createInputStream(file);) {
            String s = readToString(is, "UTF-8");
            if (s.contains("reward")) {
                if (s.substring(0, 1).contains("0")) {
                    listRewards.put(file, s.substring(1, 3));
                    points.setText(s.substring(1, 3) + " points");
                    if (s.substring(1, 2).contains("0")) {
                        listRewards.put(file, s.substring(2, 3));
                        points.setText(s.substring(2, 3) + " points");
                    }
                } else {
                    listRewards.put(file, s.substring(0, 3));
                    points.setText(s.substring(0, 3) + " points");
                }
                for (Map.Entry<String, String> entry : listRewards.entrySet()) {

                    entryValue = entry.getValue();
                    entryKey = entry.getKey();

                    db.setMap(entry);

                }
                content.add(CENTER, encloseX(reward));
                content.add(EAST, encloseX(points, c));
            }

        } catch (IOException err) {
            e(err);
        }

        res.add(content);
    }
} //End Subclass CreateFileReward

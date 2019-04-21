package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass AddGoal Description: Creates form that takes user input and stores
 * it to persistent storage.
 */

import com.codename1.io.Log;
import static com.codename1.io.Log.e;
import com.codename1.io.Storage;
import static com.codename1.io.Storage.getInstance;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import static com.codename1.ui.Display.getInstance;
import com.codename1.ui.FontImage;
import static com.codename1.ui.FontImage.MATERIAL_HELP_OUTLINE;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import static com.codename1.ui.TextArea.ANY;
import static com.codename1.ui.TextArea.NUMERIC;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.ui.layouts.BoxLayout.encloseXNoGrow;
import static com.codename1.ui.layouts.BoxLayout.y;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import static com.goalup.madelinemerced.MyObject.getRewards;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Apr 19, 2019
 * @Subclass AddReward Description:
 */
//Imports
//Begin Subclass AddReward
public class AddReward extends BaseForm {

    /**
     * Method AddReward: Inherits BaseForm properties to create form with
     * textfields for entering reward details.
     * 
     * @param res
     * @param ptsTot
     */
    public AddReward(Resources res, Label ptsTot) {
        
        //Inherits from BaseForm
        super("", y());
        
        
        Form addRewardForm = new Form();

        Toolbar tb = super.getToolbar();
        Form previous = Display.getInstance().getCurrent();

        addRewardForm.setToolbar(tb);
        tb.addMaterialCommandToRightBar("", MATERIAL_HELP_OUTLINE,
                e -> new Profile(res).show());

        tb.setBackCommand("", e -> previous.showBack());

        //Logo Image
        Image logo = res.getImage("LogoHeader.png");
        Label l = new Label(logo);
        Container logoForm = new Container(new FlowLayout(CENTER));

        //Adds Logo
        logoForm.addComponent(l);

        //TextFields
        TextField rewardTF = new TextField("", "Reward", 10, ANY);
        TextField pointsTF = new TextField("", "Points", 5, NUMERIC);
        rewardTF.setHeight(100);
        rewardTF.requestFocus();
        pointsTF.setHint("Points");
        Button enter = new Button("Enter");
        VerifyValidate vv = new VerifyValidate();
        enter.addActionListener(e -> {
            //Action listener for enter button
            if (rewardTF.getText().isEmpty() && pointsTF.getText().isEmpty()) {
                vv.alertBox("Please enter your username and password.");
            } else if (rewardTF.getText().isEmpty()) {
                vv.alertBox("Please enter a reward in the required field.");
            } else if (pointsTF.getText().isEmpty()) {
                vv.alertBox("Please enter a point value in the required field.");
            } else {
                enter(allTotal, ptsTot, rewardTF, pointsTF, addRewardForm, res, vv);
            }
        });

        add(logoForm);

        Container goalEnter = encloseXNoGrow(rewardTF, pointsTF);
        Container count = new Container();
        count.add(
                GridLayout.encloseIn(
                        (goalEnter)
                ));

        add(count);
        add(enter);
    }

    /**
     *
     * @param allTotal
     * @param dailyTotal
     * @param rewardTF
     * @param pointsTF
     * @param newForm
     * @param hi
     * @param vv
     */
    public void enter(Label allTotal, Label dailyTotal, TextField rewardTF,
            TextField pointsTF, Form newForm, Resources hi, VerifyValidate vv) {

        Dashboard db = new Dashboard();
        HashMap<String, String> pairHere = new HashMap<>();

        //Storage Management
        ArrayList<MyObject> rewards = getRewards();
        MyObject r = new MyObject();
        String reward = "reward";

        try (OutputStream os = Storage.getInstance().createOutputStream(rewardTF.getText());) {
            try {
                int pointsInt = parseInt(pointsTF.getText());

                rewards.add(r);

                //Holds points with comma deliminator
                String points = pointsTF.getText();
                String type = " " + r.getType() + " ";
                String checked = r.getCheckbox();
                String gTextField = rewardTF.getText() + " ";
                String space = " ";
                pairHere.put(rewardTF.getText(), pointsTF.getText());
                r.saveRewards();

                //Saves points with leading zeros for formating and structure
                if (pointsInt < 10) {
                    String pointsZero = "00" + points;
                    db.setPoints(pointsInt);
                    os.write(pointsZero.getBytes("UTF-8"));
                } else if (9 < pointsInt && pointsInt < 100) {
                    String pointsZero = "0" + points;
                    db.setPoints(pointsInt);
                    os.write(pointsZero.getBytes("UTF-8"));
                } else {
                    db.setPoints(pointsInt);
                    os.write(pointsTF.getText().getBytes("UTF-8"));
                }

                r.setType(reward);
                int dm = db.method(pointsInt);
                os.write(space.getBytes("UTF-8"));
                os.write(space.getBytes("UTF-8"));
                os.write(r.getType().getBytes("UTF-8"));

                db.createFileEntryReward(newForm, rewardTF.getText(), r.getType());

                new Dashboard(hi).showBack();
            } catch (NumberFormatException nfe) {
                vv.alertBox("Please enter only numbers in the points field.");

            }
        } catch (IOException err) {
            e(err);
        }
    }
    private static final Logger LOG = Logger.getLogger(AddReward.class.getName());

} //End Subclass AddReward

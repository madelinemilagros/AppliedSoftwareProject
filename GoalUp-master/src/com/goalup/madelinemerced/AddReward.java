package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass AddReward Description: Creates form that takes user input and
 * stores it to persistent storage.
 */

//Imports
import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.io.OutputStream;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Button;
import com.codename1.ui.Toolbar;
import com.codename1.ui.Display;
import com.codename1.io.Storage;
import com.codename1.ui.TextField;
import com.codename1.ui.Container;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import static com.codename1.io.Log.e;
import static java.lang.Integer.parseInt;
import static com.codename1.ui.TextArea.ANY;
import static com.codename1.ui.TextArea.NUMERIC;
import static com.codename1.ui.layouts.BoxLayout.y;
import static com.goalup.madelinemerced.MyObject.getRewards;
import static com.codename1.ui.FontImage.MATERIAL_HELP_OUTLINE;
import static com.codename1.ui.layouts.BoxLayout.encloseXNoGrow;

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

        //Initialize a new form
        Form addRewardForm = new Form();

        //Inherits project toolbar properties
        Toolbar tb = super.getToolbar();

        //Form to save previous page information
        Form previous = Display.getInstance().getCurrent();

        //Saves toolbar to form
        addRewardForm.setToolbar(tb);

        //Adds icon to toolbar that links to profile
        tb.addMaterialCommandToRightBar("", MATERIAL_HELP_OUTLINE,
                e -> new Profile(res).show());

        //Adds arrow icon to toolbar that displays previous page
        tb.setBackCommand("", e -> previous.showBack());

        //Saves logo image to label
        Image logo = res.getImage("LogoHeader.png");
        Label l = new Label(logo);

        //New container with flowlayout for centered display
        Container logoContainer = new Container(new FlowLayout(CENTER));

        //Adds Logo to flowlayout
        logoContainer.addComponent(l);

        //TextFields for reward
        TextField rewardTF = new TextField("", "Reward", 10, ANY);
        TextField pointsTF = new TextField("", "Points", 5, NUMERIC);

        //Textfield formatting
        rewardTF.setHeight(100);
        rewardTF.requestFocus();

        //Create enter button
        Button enter = new Button("Enter");

        //VerifyValidate class object initialization
        VerifyValidate vv = new VerifyValidate();

        //Action listener for enter button
        enter.addActionListener(e -> {

            //If-else-if statement for error handling
            if (rewardTF.getText().isEmpty() && pointsTF.getText().isEmpty()) {
                vv.alertBox("Please enter your username and password.");
            } else if (rewardTF.getText().isEmpty()) {
                vv.alertBox("Please enter a reward in the required field.");
            } else if (pointsTF.getText().isEmpty()) {
                vv.alertBox("Please enter a point value in the required field.");
            } else {
                Enter(ptsTot, rewardTF, pointsTF, res, vv);
            }
        });

        //Adds logo to main form
        add(logoContainer);

        //Creates container to hold textfields
        Container rewardEnter = encloseXNoGrow(rewardTF, pointsTF);

        //Creates container to formatGoal fields
        Container formatReward = new Container();

        //Adds rewardEnter container to formatReward container
        formatReward.add(
                GridLayout.encloseIn(
                        (rewardEnter)
                ));

        //Adds formatReward container to main form
        add(formatReward);

        //Adds enter button to main form
        add(enter);
    }

    /**
     * Method Enter: Writes reward and point values to persistent storage
     *
     * @param ptsTot
     * @param rewardTF
     * @param pointsTF
     * @param res
     * @param vv
     */
    public void Enter(Label ptsTot, TextField rewardTF,
            TextField pointsTF, Resources res, VerifyValidate vv) {

        //Dashboard object
        Dashboard db = new Dashboard();

        //CreateFileReward Object
        CreateFileReward cfReward = new CreateFileReward();

        //Creates hashmap for reward and points
        HashMap<String, String> pairHere = new HashMap<>();

        //Arraylist to hold storage reward results
        ArrayList<MyObject> rewards = getRewards();

        //Storage object
        MyObject r = new MyObject();

        //Variabels to hold space, type and textfield input
        String space = " ";
        String reward = "reward";
        String rewardString = rewardTF.getText();
        String pointString = pointsTF.getText();

        //Try catch statement to write to storage
        try (OutputStream os = Storage.getInstance().createOutputStream(rewardString)) {

            try {

                //Converts pointsString into int
                int pointsInt = parseInt(pointString);

                //Adds object to arraylist
                rewards.add(r);

                //adds reward and points to hashmap
                pairHere.put(rewardString, pointString);

                //Saves points with leading zeros for formating and structure
                if (pointsInt < 10) {
                    String pointsZero = "00" + pointString;
                    db.setPoints(pointsInt);
                    os.write(pointsZero.getBytes("UTF-8"));
                } else if (9 < pointsInt && pointsInt < 100) {
                    String pointsZero = "0" + pointString;
                    db.setPoints(pointsInt);
                    os.write(pointsZero.getBytes("UTF-8"));
                } else {
                    db.setPoints(pointsInt);
                    os.write(pointsTF.getText().getBytes("UTF-8"));
                }

                //Sets type to reward for storage sorting
                r.setType(reward);

                //Writes space and type to storage
                os.write(space.getBytes("UTF-8"));
                os.write(space.getBytes("UTF-8"));
                os.write(r.getType().getBytes("UTF-8"));

                //Calls dashboard CreateFileReward pointsTotalMethod for reward display
                cfReward.CreateFileReward(super.getComponentForm(),
                        rewardString, r.getType());

                //Displays dashboard after reward added
                new Dashboard(res).show();
                
            } catch (NumberFormatException nfe) {
                vv.alertBox("Please enter only numbers in the points field.");

            }
        } catch (IOException err) {
            e(err);
        }
    
    }
    
} //End Subclass AddReward

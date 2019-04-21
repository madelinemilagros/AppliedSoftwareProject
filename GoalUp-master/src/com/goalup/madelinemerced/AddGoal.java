package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass AddGoal Description: Creates form that takes user input and stores
 * it to persistent storage.
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
import static com.goalup.madelinemerced.MyObject.getGoals;
import static com.codename1.ui.FontImage.MATERIAL_HELP_OUTLINE;
import static com.codename1.ui.layouts.BoxLayout.encloseXNoGrow;

//Begin Subclass AddGoal
public class AddGoal extends BaseForm {

    /**
     * Method AddGoal: Inherits BaseForm properties to create form with
     * textfields for entering goal details.
     *
     * @param res
     * @param ptsTot
     */
    public AddGoal(Resources res, Label ptsTot) {

        //Inherits from BaseForm
        super("", y());

        //Initialize a new form
        Form addGoalForm = new Form();

        //Inherits project toolbar properties 
        Toolbar tb = super.getToolbar();

        //Form to save previous page information
        Form previous = Display.getInstance().getCurrent();

        //Saves toolbar to form
        addGoalForm.setToolbar(tb);

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

        //TextFields for goal 
        TextField goalTF = new TextField("", "Goal", 10, ANY);
        TextField pointsTF = new TextField("", "Points", 5, NUMERIC);

        //Textfield formatting
        goalTF.setHeight(50);
        goalTF.requestFocus();

        //Create enter button
        Button enter = new Button("Enter");

        //VerifyValidate class object initialization
        VerifyValidate vv = new VerifyValidate();

        //Action listener for enter button
        enter.addActionListener(e -> {

            //If-else-if statement for error handling
            if (goalTF.getText().isEmpty() && pointsTF.getText().isEmpty()) {
                vv.alertBox("Please enter your username and password.");
            } else if (goalTF.getText().isEmpty()) {
                vv.alertBox("Please enter a goal in the required field.");
            } else if (pointsTF.getText().isEmpty()) {
                vv.alertBox("Please enter a point value in the required field.");
            } else {
                Enter(ptsTot, goalTF, pointsTF, res, vv);
            }

        });

        //Adds logo to main form
        add(logoContainer);

        //Creates container to hold textfields
        Container goalEnter = encloseXNoGrow(goalTF, pointsTF);

        //Creates container to formatGoal fields
        Container formatGoal = new Container();

        //Adds goalEnter container to formatGoal container
        formatGoal.add(
                GridLayout.encloseIn(
                        (goalEnter)
                ));

        //Adds formatGoal container to main form
        add(formatGoal);

        //Adds enter button to main form
        add(enter);
    }

    /**
     * Method Enter: Writes goal and point values to persistent storage
     *
     * @param ptsTot
     * @param goalTF
     * @param pointsTF
     * @param res
     * @param vv
     */
    public void Enter(Label ptsTot, TextField goalTF,
            TextField pointsTF, Resources res, VerifyValidate vv) {

        //Dashboard object
        Dashboard db = new Dashboard();

        //Creates hashmap for goal and points
        HashMap<String, String> pairHere = new HashMap<>();

        //Arraylist to hold storage goal results
        ArrayList<MyObject> goals = getGoals();

        //Storage object
        MyObject g = new MyObject();

        //Variables to hold space, type and textfield input
        String space = " ";
        String goal = "goal";
        String goalString = goalTF.getText();
        String pointString = pointsTF.getText();

        //Try catch statement to write to storage
        try (OutputStream os = Storage.getInstance().createOutputStream
        (goalString)) {

            try {

                //Converts pointsString into int
                int pointsInt = parseInt(pointString);

                //Adds object to arraylist
                goals.add(g);

                //Adds goal and points to hashmap
                pairHere.put(goalString, pointString);

                //Adds hashmap to storage 
                g.setGoalPair(pairHere);

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
                    os.write(pointString.getBytes("UTF-8"));
                }

                //Sets type to goal for storage sorting
                g.setType(goal);

                //Writes space and type to storage
                os.write(space.getBytes("UTF-8"));
                os.write(space.getBytes("UTF-8"));
                os.write(g.getType().getBytes("UTF-8"));

                //Calls dashboard createFileEntry method for goal display 
                db.createFileEntry(super.getComponentForm(), goalTF.getText(), 
                        g.getType(), ptsTot);

                //Displays dashboard after goal added
                new Dashboard(res).show();

            } catch (NumberFormatException nfe) {
                vv.alertBox("Please enter only numbers in the points field.");

            }
        } catch (IOException err) {
            e(err);
        }

    }

} //End Subclass AddGoal

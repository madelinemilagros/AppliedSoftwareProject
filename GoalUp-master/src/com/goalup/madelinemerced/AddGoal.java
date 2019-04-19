package com.goalup.madelinemerced;

import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Apr 19, 2019
 * @Subclass AddGoal Description:
 */
//Imports
//Begin Subclass AddGoal
public class AddGoal extends BaseForm {

//public Form AddGoal(Form hi, Image logo, Label allTotal, Label dailyTotal) {
    public AddGoal(Resources hi, Label allTotal, Label dailyTotal) {
        super("", BoxLayout.y());
        Form newForm = new Form();

        Toolbar tb = super.getToolbar();
        Form previous = Display.getInstance().getCurrent();

        newForm.setToolbar(tb);
        tb.addSearchCommand(e -> {
        });

        Dashboard db = new Dashboard();

        tb.setBackCommand("", e -> previous.showBack());

        //Logo Image
        Image logo = hi.getImage("LogoHeader.png");
        Label l = new Label(logo);
        Container logoForm = new Container(new FlowLayout(Component.CENTER));

        //Adds Logo
        logoForm.addComponent(l);

        //Storage Management
        ArrayList<MyObject> goals = MyObject.getGoals();
        MyObject g = new MyObject();
        String goal = "goal";

        //TextFields
        TextField goalTF = new TextField("", "Goal", 10, TextField.ANY);
        TextField pointsTF = new TextField("", "Points", 5, TextField.NUMERIC);
        goalTF.setHeight(100);
        goalTF.requestFocus();
        pointsTF.setHint("Points");
        Button enter = new Button("Enter");
        HashMap<String, String> pairHere = new HashMap<String, String>();

        enter.addActionListener(e -> {
            
            //Action listener for enter button
            try (OutputStream os = Storage.getInstance().createOutputStream(goalTF.getText());) {
                int pointsInt = Integer.parseInt(pointsTF.getText());
                goals.add(g);

                //Holds points with comma deliminator
                String points = pointsTF.getText();
                String type = " " + g.getType() + " ";
                String checked = g.getCheckbox();
                String gTextField = goalTF.getText() + " ";
                String space = " ";
                pairHere.put(goalTF.getText(), pointsTF.getText());
                g.setGoalPair(pairHere);
                g.saveGoals();
                
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

                g.setType(goal);
                int dm = db.method(pointsInt);
                os.write(space.getBytes("UTF-8"));
                os.write(space.getBytes("UTF-8"));
                os.write(g.getType().getBytes("UTF-8"));

                db.createFileEntry(newForm, goalTF.getText(), g.getType(), allTotal, dailyTotal);

                new Dashboard(hi).showBack();
            } catch (IOException err) {
                Log.e(err);
            }

        });
        add(logoForm);

        Container goalEnter = BoxLayout.encloseXNoGrow(goalTF, pointsTF);
        Container count = new Container();
        count.add(
                GridLayout.encloseIn(
                        (goalEnter)
                ));

        add(count);
        add(enter);

    }
} //End Subclass AddGoal

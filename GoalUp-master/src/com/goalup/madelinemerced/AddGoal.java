package com.goalup.madelinemerced;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Apr 7, 2019
 * @Subclass AddGoal Description:
 */
//Imports
//Begin Subclass AddGoal
public class AddGoal extends Form {
 int pointsTotal;
    int cPointsTotal;
    int pointsInt;
    
//    public AddGoal(Resources res, Label allTotal, Label dailyTotal) {
//        super("Dashboard", BoxLayout.y());
//        Toolbar tb = getToolbar();
//
//        //Storage Management
//        ArrayList<Storage> goals = Storage.getGoals();
//        Storage g = new Storage();
//
//        //TextFields
//        TextField goalTF = new TextField("", "Goal", 16, TextField.ANY);
//        TextArea pointsTF = new TextArea(2, 2);
//
//        pointsTF.setHint("Points");
//        Button enter = new Button("Enter");
//
//        enter.addActionListener(e -> {
//            //Action listener for enter button
//            try {
//                int pointsInt = Integer.parseInt(pointsTF.getText());
//                setPoints(pointsInt);
//                int dm = method(pointsInt);
//
//                String dailyTString = Integer.toString(dm);
////                allTotal.setText(dailyTString);
//                g.setGoal(goalTF.getText());
//                g.setPoints(pointsTF.getText());
//
//                Storage.setGoals(goals);
//                goals.add(g);
//                new Goal(g, dailyTotal, allTotal, pointsTotal, cPointsTotal, pointsInt).show();
////                add(Goal(g, dailyTotal, allTotal));
////                show();
////                add(Goal(g, dailyTotal, allTotal));
//
//            } catch (NumberFormatException nfe) {
//
//            }
//
//        });
//
//        Container goalEnter = BoxLayout.encloseXNoGrow(goalTF, pointsTF);
//        Container count = new Container();
//        count.add(
//                GridLayout.encloseIn(
//                        (goalEnter)
//                ));
//
//        add(count);
//        add(enter);
//
//        show();
//
//    }
//
//    public String date() {
//        //Date formatter
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//        String strDate = formatter.format(date);
//        return strDate;
//    }
//
//    public void setPoints(int points) {
//        pointsInt = points;
//    }
//
//    public int getPoints() {
//        return pointsInt;
//    }
//
//    public int method(int p) {
//        pointsTotal = p + pointsTotal;
//        met(pointsTotal);
//        return pointsTotal;
//    }
//
//    public int met(int p) {
//        cPointsTotal = p + cPointsTotal;
//        return cPointsTotal;
//    }
//
//    public Component createLineSeparator() {
//        Label separator = new Label("", "WhiteSeparator");
//        separator.setShowEvenIfBlank(true);
//        return separator;
//    }
} //End Subclass AddGoal

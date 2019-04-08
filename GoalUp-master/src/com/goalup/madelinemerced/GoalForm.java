package com.goalup.madelinemerced;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Apr 7, 2019
 * @Subclass AddGoal Description:
 */
//Imports
//Begin Subclass AddGoal
public class GoalForm extends BaseForm {
//
//    int pointsTotal;
//    int cPointsTotal;
//    int pointsInt;
//    Resources theme;
//    
//    public GoalForm(Resources res, Label allTotal, Label dailyTotal) {
//        super("", BoxLayout.y());
//        Toolbar tb = super.getToolbar();
//        setToolbar(tb);
//        theme = res;
//        //Logo Image
//        Image logo = res.getImage("LogoHeader.png");
//        Label l = new Label(logo);
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
//                add(addGoal(g, dailyTotal, allTotal));
//                show();
//                add(addGoal(g, dailyTotal, allTotal));
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
//        add(count);
//        add(enter);
//
//
//        super.addSideMenu(res);
//        super.show();
//        tb.addSearchCommand(e -> {
//        });
//    }
//    
// private Container addGoal(Storage s, Label dailyTotal, Label allTotal) {
//        Label goal = new Label(s.getGoal());
//        Button points = new Button(s.getPoints());
//        CheckBox completeCB = new CheckBox();
//        Container row = BoxLayout.encloseXNoGrow(goal, points, completeCB);
//        Container count = new Container();
//        
//        count.add(
//                GridLayout.encloseIn(
//                        (row)
//                ));
//
//        Container cnt = BoxLayout.encloseY(
//                (count), (createLineSeparator())
//        );
//
//        completeCB.addChangeListener(e -> {
//            if (completeCB.isSelected()) {
//                int total = method(pointsTotal);
//                dailyTotal.setText(Integer.toString(total));
//                allTotal.setText(Integer.toString(total));
//
//            } else if (!completeCB.isSelected()) {
////                pointsTotal = 0;
//                String dT = allTotal.getText();
//                int total = Integer.parseInt(dT);
////                method(total);
//                allTotal.setText(Integer.toString(total));
//            }
//        });
//        return cnt;
//    }
// 
//   public int method(int p) {
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
//      public void setPoints(int points) {
//        pointsInt = points;
//    }
//
//    public int getPoints() {
//        return pointsInt;
//    }

} //End Subclass AddGoal

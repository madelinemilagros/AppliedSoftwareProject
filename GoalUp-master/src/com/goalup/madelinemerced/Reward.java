package com.goalup.madelinemerced;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/** 
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Apr 7, 2019
 * @Subclass Reward Description: 
 */
//Imports

//Begin Subclass Reward
public class Reward extends BaseForm {

    int pointsTotal;
    int cPointsTotal;
    int pointsInt;

    public Reward(Resources hi) {
        super("", BoxLayout.y());
        Toolbar tb =super.getToolbar();
        setToolbar(tb);
        
        //Logo Image
        Image logo = hi.getImage("LogoHeader.png");
        Label l = new Label(logo);
        
         //Flow Container
        Container flowLabel = new Container(new FlowLayout(Component.CENTER));

        //Adds Logo
        flowLabel.addComponent(l);
super.addSideMenu(hi);
        //SideMenu
        Image icon = hi.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icon));
        Image icon2 = hi.getImage("icon.png");
        topBar.add(BorderLayout.WEST, new Label(icon2));
        topBar.add(BorderLayout.SOUTH, new Label("", "Cause You Got This!"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
        
        //Storage Management
        ArrayList<Storage> goals = Storage.getGoals();
        
        for(int i=0; i<goals.size(); i++){
        Storage g = goals.get(i);
            
        Label goal = new Label(g.getGoal());
        Button points = new Button(g.getPoints());
        CheckBox completeCB = new CheckBox();
        
        Container row = BoxLayout.encloseXNoGrow(goal, points, completeCB);
        
        Container count = new Container();
        count.add(
                GridLayout.encloseIn(
                        (row)
                ));
    Container cnt = BoxLayout.encloseY(
                (count), (createLineSeparator())
        );
   add(flowLabel);
    add(cnt);}
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
} //End Subclass Reward
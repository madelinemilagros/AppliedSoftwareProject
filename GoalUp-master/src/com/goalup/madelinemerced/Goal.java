package com.goalup.madelinemerced;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.LEFT;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.madelinemerced.mlmxms
 * @Date: Apr 4, 2019
 * @Subclass Goal Description:
 */
//Imports
//Begin Subclass Goal
public class Goal extends Form {

    int pointsTotal;
    int cPointsTotal;
    int pointsInt;

    public Goal(Resources hi, Label allTotal, Label dailyTotal) {
        super("", BoxLayout.y());
        Toolbar tb =super.getToolbar();
        setToolbar(tb);
 //Logo Image
        Image logo = hi.getImage("LogoHeader.png");
        Label l = new Label(logo);
        
         //Flow Container
        Container flowLabel = new Container(new FlowLayout(Component.CENTER));
        //Adds Dashboard Labels to Flow Container (holds image as well)                

        //Adds Logo
        flowLabel.addComponent(l);

        //SideMenu
        Image icon = hi.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icon));
        Image icon2 = hi.getImage("icon.png");
        topBar.add(BorderLayout.WEST, new Label(icon2));
        topBar.add(BorderLayout.SOUTH, new Label("Goal Up", "Cause You Got This!"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
        Button goalButton = new Button("Goals");
        tb.addComponentToSideMenu(goalButton);
        goalButton.addActionListener(e -> {
            new Goal(hi, allTotal, dailyTotal).show();
        });
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
} //End Subclass Goal

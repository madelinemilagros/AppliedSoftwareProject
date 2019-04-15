package com.goalup.madelinemerced;

import com.codename1.io.Storage;
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

    public Reward(Resources hi, Label allTotal, Label dailyTotal) {
        super("", BoxLayout.y());
        Toolbar tb = super.getToolbar();
        setToolbar(tb);
        tb.addSearchCommand(e -> {
        });
        
        //Logo Image
        Image logo = hi.getImage("LogoHeader.png");
        Label l = new Label(logo);
        Container flowLabelLogo = new Container(new FlowLayout(Component.CENTER));
   
        //Adds Logo
        flowLabelLogo.addComponent(l);
        super.add(flowLabelLogo);
        super.addSideMenu(hi);

        
        Container pageTitleRewards = new Container(new FlowLayout(Component.CENTER));
        Label rewardsLabel = new Label("Rewards");
        pageTitleRewards.add(rewardsLabel);
        super.add(pageTitleRewards);

        Dashboard db = new Dashboard();
        MyObject g = new MyObject();
        
        for (String file : Storage.getInstance().listEntries()) {
        db.createFileEntryReward(super.getComponentForm(), file, g.getType(), dailyTotal, allTotal);
        }
    } 

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
} //End Subclass Reward

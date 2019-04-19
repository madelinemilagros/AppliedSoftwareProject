package com.goalup.madelinemerced;

import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.madelinemerced.mlmxms
 * @Date: Apr 4, 2019
 * @Subclass Goal Description:
 */
//Imports
//Begin Subclass Goal
public class Goal extends BaseForm {

    int pointsTotal;
    int cPointsTotal;
    int pointsInt;

    public Goal(Resources hi, Label allTotal, Label dailyTotal) {
        super("", BoxLayout.y());
        Toolbar tb = super.getToolbar();
        setToolbar(tb);
        tb.addSearchCommand(e -> {
        });
        Form previous = Display.getInstance().getCurrent();

        tb.setBackCommand("", e -> previous.showBack());

        //Logo Image
        Image logo = hi.getImage("LogoHeader.png");
        Label l = new Label(logo);
        Container flowLabel = new Container(new FlowLayout(Component.CENTER));

        flowLabel.addComponent(l);
        super.add(flowLabel);
        super.addSideMenu(hi);

        Container pageTitleGoals = new Container(new FlowLayout(Component.CENTER));
        Label goalsLabel = new Label("Goals");
        pageTitleGoals.add(goalsLabel);
        super.add(pageTitleGoals);

        Dashboard db = new Dashboard();
        MyObject g = new MyObject();

        for (String file : Storage.getInstance().listEntries()) {
            db.createFileEntry(super.getComponentForm(), file, g.getType(), dailyTotal, allTotal);
        }

    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
} //End Subclass Goal

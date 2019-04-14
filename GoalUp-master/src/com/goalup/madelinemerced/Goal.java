package com.goalup.madelinemerced;

import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
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
        //Logo Image
        Image logo = hi.getImage("LogoHeader.png");
        Label l = new Label(logo);
        Container flowLabel = new Container(new FlowLayout(Component.CENTER));

        flowLabel.addComponent(l);
        super.add(flowLabel);
        super.addSideMenu(hi);

        //SideMenu
        Image icon = hi.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icon));
        Image icon2 = hi.getImage("icon.png");
        topBar.add(BorderLayout.WEST, new Label(icon2));
        topBar.add(BorderLayout.SOUTH, new Label(" ", "Cause You Got This!"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
  
        for (String file : Storage.getInstance().listEntries()) {
            createFileEntry(super.getComponentForm(), file, dailyTotal, allTotal);
        }
        super.revalidate();

    }
    
     private void createFileEntry(Form hi, String file, Label dailyTotal, Label allTotal) {
        Label goal = new Label(file);
        CheckBox completeCB = new CheckBox();
        Button points = new Button("");
        Button delete = new Button();
        FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
        Container content = BorderLayout.center(goal);
        delete.addActionListener(e -> {
            Storage.getInstance().deleteStorageFile(file);
            content.setY(hi.getWidth());
            hi.getContentPane().animateUnlayoutAndWait(150, 255);
            hi.removeComponent(content);
            hi.getContentPane().animateLayout(150);
        });

        
        try (InputStream is = Storage.getInstance().createInputStream(file);) {
            String s = Util.readToString(is, "UTF-8");
            points.setText(s);
            
        } catch (IOException err) {
            Log.e(err);
        }
        
        content.add(BorderLayout.EAST, BoxLayout.encloseX(points,delete, completeCB));
        hi.add(content);
    }
    

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
} //End Subclass Goal

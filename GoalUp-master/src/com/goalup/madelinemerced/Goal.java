package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass Goal Description: Creates form that takes user input and stores
 * it to persistent storage.
 */

//Imports
import com.codename1.io.Storage;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

//Begin Subclass Goal
public class Goal extends BaseForm {

    int pointsTotal;
    int cPointsTotal;
    int pointsInt;

    public Goal(Resources hi, Label allTotal, Label dailyTotal) {
        super("", BoxLayout.y());
        Toolbar tb = super.getToolbar();
        setToolbar(tb);
         tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_HELP_OUTLINE,
                e -> new Profile(hi).show());

    

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
            db.createFileEntry(super.getComponentForm(), file, g.getType(), dailyTotal);
        }

    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
} //End Subclass Goal

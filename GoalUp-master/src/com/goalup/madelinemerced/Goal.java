package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass Goal Description: Reads storage for goal files, formats and
 * displays them on page
 */
//Imports
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.Display;
import com.codename1.io.Storage;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.FlowLayout;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.layouts.BoxLayout.y;
import static com.codename1.ui.FontImage.MATERIAL_HELP_OUTLINE;

//Begin Subclass Goal
public class Goal extends BaseForm {

    //Classwide variables
    int pointsTotal;
    int cPointsTotal;
    int pointsInt;

    /**
     * Method Goal: Displays all goals in storage.
     *
     * @param res
     * @param ptsTotal
     */
    public Goal(Resources res, Label ptsTotal) {
        //Inherits from BaseForm
        super("", y());

        //Inherits project toolbar properties
        Toolbar tb = super.getToolbar();

        //Form to save previous page information
        Form previous = Display.getInstance().getCurrent();

        //Saves toolbar to form
        setToolbar(tb);

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

        //Adds logoContainer and sidemenu to page
        super.add(logoContainer);
        super.addSideMenu(res);

        //Container for goals page with label
        Container pageTitleGoals = new Container(new FlowLayout(CENTER));
        Label goalsLabel = new Label("Goals");
        pageTitleGoals.add(goalsLabel);

        //Adds pageTitleGoals container to page
        super.add(pageTitleGoals);

        //Storage object
        MyObject g = new MyObject();

        //CreateFileGoal object
        CreateFileGoal cfGoal = new CreateFileGoal();

        //For loop to read in storage files
        for (String file : Storage.getInstance().listEntries()) {
            cfGoal.CreateFileGoal(super.getComponentForm(), file, g.getType(),
                    ptsTotal);
        }

    }

    /**
     * Method createLineSeparator: Formatting white separator
     *
     * @return
     */
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
} //End Subclass Goal

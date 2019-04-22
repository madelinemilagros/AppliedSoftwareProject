package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass Reward Description: Reads storage for reward files, formats and
 * displays them on page
 */
//Imports
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.Display;
import com.codename1.io.Storage;
import com.codename1.ui.Container;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.FlowLayout;
import static com.codename1.ui.layouts.BoxLayout.y;
import static com.codename1.ui.FontImage.MATERIAL_HELP_OUTLINE;

//Begin Subclass Reward
public class Reward extends BaseForm {

    /**
     * Method Reward: Displays all rewards in storage.
     *
     * @param res
     */
    public Reward(Resources res) {

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

        //Container for rewards page with label
        Container pageTitleRewards = new Container(new FlowLayout(CENTER));
        Label rewardsLabel = new Label("Rewards");
        pageTitleRewards.add(rewardsLabel);

        //Adds pageTitleRewards container to page
        super.add(pageTitleRewards);

        //Reward image example 
        Image shoes = res.getImage("shoesSmall.png");
        Label shoeHolder = new Label(shoes);

        //Container for reward image
        Container centerRewardPicture = new Container(new FlowLayout(CENTER));
        centerRewardPicture.add(shoeHolder);

        //Adds centerRewardPicture to page
        super.add(centerRewardPicture);

        //Dashboard object 
        Dashboard db = new Dashboard();

        //Storage object
        MyObject g = new MyObject();

        //Passes value to dashboard class setMainRewardPoints pointsTotalMethod
        db.setMainRewardPoints("100");

        CreateFileReward cfReward = new CreateFileReward();

        //For loop to read all storage files
        for (String file : Storage.getInstance().listEntries()) {

            //Passes values to CreateFileReward pointsTotalMethod in dashboard to 
            //display on page
            cfReward.CreateFileReward(super.getComponentForm(), file, g.getType());
        }

    }

} //End Subclass Reward

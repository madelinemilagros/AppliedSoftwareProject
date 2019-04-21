package com.goalup.madelinemerced;

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

/**
 * @Course: SDEV 435 ~ Applied Software
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
        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_HELP_OUTLINE,
                e -> new Profile(hi).show());
        Form previous = Display.getInstance().getCurrent();

        tb.setBackCommand("", e -> previous.showBack());

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

        Image shoes = hi.getImage("shoesSmall.png");
        Label shoeHolder = new Label(shoes);
        Container centerRewardPicture = new Container(new FlowLayout(Component.CENTER));
        centerRewardPicture.add(shoeHolder);
        super.add(centerRewardPicture);
        
        Dashboard db = new Dashboard();
        MyObject g = new MyObject();
        db.setMainRewardPoints("100");
        for (String file : Storage.getInstance().listEntries()) {
            db.createFileEntryReward(super.getComponentForm(),file, g.getType());
        }

    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
} //End Subclass Reward

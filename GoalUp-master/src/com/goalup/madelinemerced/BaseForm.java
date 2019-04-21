package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass AddGoal Description: Creates form that takes user input and stores
 * it to persistent storage.
 */

//Imports
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.Component;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.components.ScaleImageLabel;
import static com.codename1.ui.Display.getInstance;
import static com.codename1.ui.FontImage.MATERIAL_CAKE;
import static com.codename1.ui.FontImage.MATERIAL_STARS;
import static com.codename1.ui.FontImage.MATERIAL_DASHBOARD;
import static com.codename1.ui.FontImage.MATERIAL_EXIT_TO_APP;
import static com.codename1.ui.layouts.FlowLayout.encloseCenterBottom;
import static com.codename1.ui.plaf.Style.BACKGROUND_IMAGE_SCALED_FILL;

//Begin Subclass BaseForm
public class BaseForm extends Form {

    /**
     *BaseForm constructor
     */
    public BaseForm() {
    
    }

    /**
     * BaseForm constructor with contentPaneLayout
     * 
     * @param contentPaneLayout
     */
    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    /**
     * BaseForm constructor with title and contentPaneLayout
     * 
     * @param title
     * @param contentPaneLayout
     */
    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    /**
     * Method createLineSeparator: Provides formatting with white space when 
     * called
     * 
     * @return
     */
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    /**
     * Method createLineSeparator: Provides formatting with color/whie space
     * 
     * @param color
     * @return
     */
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    /**
     * Method addSideMenu: Creates and adds menu to toolbar 
     * 
     * @param res
     */
    protected void addSideMenu(Resources res) {
        
        //Gets the toolbar
        Toolbar tb = getToolbar();
        
        //Image holder for background of profile 
        Image img = res.getImage("backgroundProfile.jpg");
        if (img.getHeight() > getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(getInstance().getDisplayHeight() / 3);
        }
        
        //Formats and sets background image
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(BACKGROUND_IMAGE_SCALED_FILL);

        //Adds components to menu
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(sl,
                encloseCenterBottom(
                        new Label(res.getImage("headshot.jpg"), 
                              "PictureWhiteBackgrond"))
        ));
        Label allTotal = null;
        Label dailyTotal = null;
        
        //Adds navigation buttons to side menu
        tb.addMaterialCommandToSideMenu("Dashboard", MATERIAL_DASHBOARD, 
                e ->new Dashboard(res).show());
        tb.addMaterialCommandToSideMenu("Goals", MATERIAL_STARS, e -> 
                new Goal(res, allTotal, dailyTotal).show());
        tb.addMaterialCommandToSideMenu("Rewards", MATERIAL_CAKE, e 
                -> new Reward(res, allTotal, dailyTotal).show());
        tb.addMaterialCommandToSideMenu("Logout", MATERIAL_EXIT_TO_APP, 
                e -> new SignIn(res).show());
    }
}

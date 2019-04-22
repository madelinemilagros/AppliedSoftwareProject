package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass Profile Description: Displays user profile values to page.
 */

//Imports
import java.io.IOException;
import java.io.InputStream;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Display;
import com.codename1.io.Storage;
import com.codename1.ui.Toolbar;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Component;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.components.ScaleImageLabel;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.io.Util.readToString;
import static com.codename1.ui.layouts.BoxLayout.y;
import static com.codename1.ui.Display.getInstance;
import static com.codename1.ui.CheckBox.createToggle;
import static com.codename1.ui.layouts.BorderLayout.west;
import static com.codename1.ui.layouts.BorderLayout.south;
import static com.codename1.ui.FontImage.MATERIAL_HELP_OUTLINE;
import static com.codename1.ui.layouts.FlowLayout.encloseCenter;
import static com.codename1.ui.layouts.FlowLayout.encloseRightMiddle;
import static com.codename1.ui.plaf.Style.BACKGROUND_IMAGE_SCALED_FILL;

//Begin Subclass Profile
public class Profile extends BaseForm {

    /**
     * Method Profile: Inherits BaseForm properties to displays user's profile
     * values
     *
     * @param res
     */
    public Profile(Resources res) {

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
        
        //Component for layered profile background
        Image img = res.getImage("backgroundProfile.jpg");
        if (img.getHeight() > getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(getInstance().getDisplayHeight() / 3);
        }

        //Scales image for display
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(BACKGROUND_IMAGE_SCALED_FILL);

        //Add profile picture
        add(LayeredLayout.encloseIn(
                south(
                        encloseCenter(
                                new Label(res.getImage("headshot.jpg"),
                                        "PictureWhiteBackgrond"))
                )
        ));

        //Username field
        Label username = new Label();
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        //Email field
        Label email = new Label();
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);

        //Password field
        Label password = new Label();
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);

        //Profile picture container
        Container pic = new Container();
        Label picture = new Label();
        pic.add(picture);

        //Toggle button for email
        CheckBox cb1 = createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        addStringValue("Email Updates", encloseRightMiddle(cb1));

        //Toggle button for social media 
        CheckBox cb2 = createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));
        addStringValue("Social Media", encloseRightMiddle(cb2));

        //Try-catch for username storage information
        try (InputStream is = Storage.getInstance().createInputStream("ProfileUsername");) {
            String s = readToString(is, "UTF-8");

            username.setText(s);

        } catch (IOException ex) {
        }

        //Try-catch for email storage information
        try (InputStream is = Storage.getInstance().createInputStream("ProfileEmail");) {
            String s = readToString(is, "UTF-8");

            email.setText(s);

        } catch (IOException ex) {
        }

        //Try-catch for password storage information
        try (InputStream is = Storage.getInstance().createInputStream("ProfilePassword");) {
            String s = readToString(is, "UTF-8");
            password.setText(s);

        } catch (IOException ex) {
        }

    }

    /**
     * Method addStringValue: Adds string value to profile.
     *
     * @param s
     * @param v
     */
    private void addStringValue(String s, Component v) {
        add(west(new Label(s, "PaddedLabel")).
                add(CENTER, v));
        add(createLineSeparator(0xee_eeee));
    }

} //End Subclass Profile

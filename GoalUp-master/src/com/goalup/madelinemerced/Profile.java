package com.goalup.madelinemerced;

import com.codename1.components.ScaleImageLabel;
import com.codename1.io.Storage;
import com.codename1.io.Util;
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
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Apr 20, 2019
 * @Subclass Profile Description:
 */
//Imports
//Begin Subclass Profile
public class Profile extends BaseForm {

    public Profile(Resources res) {
        super("", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        Form previous = Display.getInstance().getCurrent();

//        super.addSideMenu(res);
        tb.setBackCommand("", e -> previous.showBack());

//
        Image img = res.getImage("backgroundProfile.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

//        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
//        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
//        facebook.setTextPosition(BOTTOM);
//        twitter.setTextPosition(BOTTOM);

        add(LayeredLayout.encloseIn(
//                sl,
                BorderLayout.south(
//                        GridLayout.encloseIn(3,
//                                facebook,
                                FlowLayout.encloseCenter(
                                        new Label(res.getImage("headshot.jpg"), "PictureWhiteBackgrond"))
//                                twitter
//                        )
                )
        ));
        

        Label username = new Label();
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        Label email = new Label();
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);

        Label password = new Label();
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
Container pic = new Container();
        Label picture = new Label();
        pic.add(picture);
//        password.setUIID("TextFieldBlack");
//        addStringValue("Password", password);
        
        CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));

        addStringValue("Email Updates", FlowLayout.encloseRightMiddle(cb1));
        addStringValue("Social Media", FlowLayout.encloseRightMiddle(cb2));

        
       
            
        try (InputStream is = Storage.getInstance().createInputStream("ProfileUsername");) {
            String s = Util.readToString(is, "UTF-8");

            username.setText(s);

        } catch (IOException ex) {
        }

        try (InputStream is = Storage.getInstance().createInputStream("ProfileEmail");) {
            String s = Util.readToString(is, "UTF-8");

            email.setText(s);

        } catch (IOException ex) {
        }

        try (InputStream is = Storage.getInstance().createInputStream("ProfilePassword");) {
            String s = Util.readToString(is, "UTF-8");
            password.setText(s);

        } catch (IOException ex) {
        }
       
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
    
    
} //End Subclass Profile

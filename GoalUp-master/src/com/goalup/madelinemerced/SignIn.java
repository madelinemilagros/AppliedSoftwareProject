package com.goalup.madelinemerced;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.madelinemerced.mlmxms
 * @Date: Apr 4, 2019
 * @Subclass Goal Description:
 */
//Imports
//Begin Subclass Goal
public class SignIn extends BaseForm {

    int pointsTotal;
    int cPointsTotal;
    int pointsInt;

    public SignIn(Resources hi) {
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
        
// TextField username = new TextField("", "Username", 20, TextField.ANY);
//        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
//        username.setSingleLineTextArea(false);
//        password.setSingleLineTextArea(false);
//        Button signIn = new Button("Sign In");
//        Button signUp = new Button("Sign Up");
////        signUp.addActionListener(e -> new SignUpForm(res).show());
//        signUp.setUIID("Link");
//        Label doneHaveAnAccount = new Label("Don't have an account?");
//        
//        Container content = BoxLayout.encloseY(
//                new FloatingHint(username),
//                createLineSeparator(),
//                new FloatingHint(password),
//                createLineSeparator(),
//                signIn,
//                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
//        );
//        content.setScrollableY(true);
//        add(BorderLayout.SOUTH, content);
//        signIn.requestFocus();
//        signIn.addActionListener(e -> new Dashboard(hi).show());
    }
    

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
} //End Subclass Goal

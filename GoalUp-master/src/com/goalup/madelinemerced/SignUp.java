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
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Apr 17, 2019
 * @Subclass SignUp Description: 
 */
//Imports

//Begin Subclass SignUp
public class SignUp extends BaseForm {

    public SignUp(Resources hi) {
        super("", new BorderLayout());
        Toolbar tb = super.getToolbar();
        setToolbar(tb);
        
        //Logo Image
        Image logo = hi.getImage("LogoHeader.png");
        Label l = new Label(logo);
        Container flowLabel = new Container(new FlowLayout(Component.CENTER));

        flowLabel.addComponent(l);
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField email = new TextField("", "Email", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Label welcome = new Label("Welcome to Goal Up");
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        signIn.addActionListener(e -> new SignIn(hi).show());
        signIn.setUIID("Link");
        Label alreadyHaveAnAccount = new Label("Already have an account?");
//        
        Container content = BoxLayout.encloseY(
                FlowLayout.encloseCenterMiddle(flowLabel),
                createLineSeparator(),
                FlowLayout.encloseCenterMiddle(welcome),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signUp,
                FlowLayout.encloseCenter(alreadyHaveAnAccount, signIn)
        );
//        add(BorderLayout.SOUTH, addSideMenu(hi));

        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signUp.requestFocus();
        signUp.addActionListener(e -> new Dashboard(hi).show());
    }
    

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

} //End Subclass SignUp
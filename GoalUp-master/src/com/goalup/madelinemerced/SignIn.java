package com.goalup.madelinemerced;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.util.regex.RE;
import javafx.scene.control.Alert;

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

    public SignIn(Resources hi) {
        super("", new BorderLayout());
        Toolbar tb = super.getToolbar();
        setToolbar(tb);

        //Logo Image
        Image logo = hi.getImage("LogoHeader.png");
        Label l = new Label(logo);
        Container flowLabel = new Container(new FlowLayout(Component.CENTER));

        flowLabel.addComponent(l);
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);

        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUp(hi).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        Label topPad = new Label();
        topPad.setUIID("TopPad");
        VerifyValidate vv = new VerifyValidate();

        Container content = BoxLayout.encloseY(
                topPad,
                FlowLayout.encloseCenter(flowLabel),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );

        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        signIn.requestFocus();
        signIn.addActionListener(e -> {
            if (username.getText().isEmpty() && password.getText().isEmpty()) {
                vv.alertBox("Please enter your username and password.");
            } else if (username.getText().isEmpty()) {
                vv.alertBox("Please enter your username in the required field.");
            } else if (password.getText().isEmpty()) {
                vv.alertBox("Please enter your password in the required field.");
            } else {
                new Dashboard(hi).show();
            };

        });
    }
} //End Subclass Goal

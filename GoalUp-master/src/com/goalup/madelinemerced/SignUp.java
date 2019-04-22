package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass SignUp Description: Creates form with textfields for new users to
 * sign up
 */

//Imports
import java.util.ArrayList;
import java.io.IOException;
import java.io.OutputStream;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Button;
import com.codename1.ui.Toolbar;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.validation.Validator;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.validation.RegexConstraint;
import static com.codename1.io.Log.e;
import static com.codename1.ui.TextArea.ANY;
import static com.codename1.ui.TextArea.PASSWORD;
import static com.codename1.io.Storage.getInstance;
import static com.codename1.ui.layouts.BoxLayout.encloseY;
import static com.goalup.madelinemerced.MyObject.getProfile;
import static com.codename1.ui.layouts.FlowLayout.encloseCenter;
import static com.codename1.ui.layouts.FlowLayout.encloseCenterMiddle;

//Begin Subclass SignUp
public class SignUp extends BaseForm {

    /**
     * Method SignUp: Inherits BaseForm properties to create form with
     * textfields for new users to sign up
     *
     * @param res
     */
    public SignUp(Resources res) {

        //Inherits from BaseForm
        super("", new BorderLayout());

        //Initialize project toolbar properties
        Toolbar tb = super.getToolbar();

        //Saves toolbar to form
        setToolbar(tb);

        //Saves logo image to label
        Image logo = res.getImage("LogoHeader.png");
        Label l = new Label(logo);

        //New container with flowlayout for centered display
        Container flowLabel = new Container(new FlowLayout(CENTER));

        //Adds logo to flowlayout
        flowLabel.addComponent(l);

        //Textfields for signup form 
        TextField username = new TextField("", "Username", 20, ANY);
        TextField email = new TextField("", "Email", 20, ANY);
        TextField password = new TextField("", "Password", 20, PASSWORD);
        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);

        //VerifyValidate object
        VerifyValidate vv = new VerifyValidate();
    
        //Email Validator Object
        Validator emailVal = new Validator();
        emailVal.addConstraint(email, RegexConstraint.validEmail());

        //SignIn button
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> new SignIn(res).show());
        signIn.setUIID("Link");
        Label alreadyHaveAnAccount = new Label("Already have an account?");
        Label topPad = new Label();
        topPad.setUIID("TopPad");

        //SignUp button        
        Button signUp = new Button("Sign Up");
        signUp.requestFocus();
        signUp.addActionListener(e -> {
 
            if (username.getText().isEmpty() && email.getText().isEmpty()
                    && password.getText().isEmpty()) {
                vv.alertBox("Please enter a username, email and password.");
            } else if (username.getText().isEmpty() && email.getText().isEmpty()) {
                vv.alertBox("Please enter a username and email.");
            } else if (username.getText().isEmpty() && password.getText().isEmpty()) {        
                vv.alertBox("Please enter a username and password.");
            } else if (email.getText().isEmpty() && password.getText().isEmpty()) {
                vv.alertBox("Please enter a valid email and password.");
            } else if (username.getText().isEmpty()) {
                vv.alertBox("Please enter a username.");
            } else if (email.getText().isEmpty()) {
                vv.alertBox("Please enter a email.");
            } else if (password.getText().isEmpty()) {
                vv.alertBox("Please enter a password");
            } else if (!emailVal.isValid()) {
                vv.alertBox("Invalid Email Please try again.");
            } else if(password.getText().length() < 8){
                vv.alertBox("Password must be 8 or more letters long.");
            } else{
                Enter(res, username, email, password, vv);
            }

        });

        //Container for main formatting
        Container content = encloseY(topPad,
                encloseCenterMiddle(flowLabel),
                createLineSeparator(),
                (username),
                (email),
                (password),
                createLineSeparator(),
                signUp,
                encloseCenter(alreadyHaveAnAccount, signIn)
        );

        content.setScrollableY(true);
        add(CENTER, content);

    }

    /**
     * Method Enter: Writes username, email and password to persistent storage
     *
     * @param usernameTF
     * @param emailTF
     * @param passwordTF
     * @param vv
     */
    public void Enter(Resources res, TextField usernameTF, TextField emailTF, 
            TextField passwordTF, VerifyValidate vv) {

        //Storage Management
        ArrayList<MyObject> profile = getProfile();
        MyObject p = new MyObject();

        //Profile variables for three files that hold corresponding information
        String username = "ProfileUsername";
        String email = "ProfileEmail";
        String password = "ProfilePassword";

        //Try-Catch to write username to persistent storage
        try (OutputStream os = getInstance().createOutputStream(username);) {
            try {
                String usernameText = usernameTF.getText();
                profile.add(p);
                p.saveProfile();
                os.write(usernameText.getBytes("UTF-8"));

            } catch (NumberFormatException nfe) {
                vv.alertBox("Please enter only numbers in the points field.");

            }
        } catch (IOException err) {
            e(err);
        }

        //Try-Catch to write email to persistent storage
        try (OutputStream os = getInstance().createOutputStream(email);) {
            try {
                String emailText = emailTF.getText();
                profile.add(p);
                p.saveProfile();
                os.write(emailText.getBytes("UTF-8"));

            } catch (NumberFormatException nfe) {
                vv.alertBox("Please enter only numbers in the points field.");

            }
        } catch (IOException err) {
            e(err);
        }

        //Try-Catch to write password to persistent storage
        try (OutputStream os = getInstance().createOutputStream(password);) {
            try {
                String passwordText = passwordTF.getText();
                profile.add(p);
                p.saveProfile();
                os.write(passwordText.getBytes("UTF-8"));

            } catch (NumberFormatException nfe) {
                vv.alertBox("Please enter only numbers in the points field.");

            }
        } catch (IOException err) {
            e(err);
        }

        //Displays dashboard
        new Dashboard(res).show();
    }

}//End Subclass SignUp

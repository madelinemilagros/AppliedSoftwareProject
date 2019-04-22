package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass SignIn Description: Creates form that takes user sign in details 
 */

//Imports
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Button;
import com.codename1.ui.Toolbar;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.TextArea.ANY;
import static com.codename1.ui.TextArea.PASSWORD;
import static com.codename1.ui.layouts.BoxLayout.encloseY;
import static com.codename1.ui.layouts.FlowLayout.encloseCenter;

//Begin Subclass SignIn
public class SignIn extends BaseForm {

    /**
     * Method SignIn: Inherits BaseForm properties to create form with
     * textfields for existing users to sign in.
     * 
     * @param res
     */
    public SignIn(Resources res) {
        
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
        Container logoContainer = new Container(new FlowLayout(CENTER));

        //Adds logo to flowlayout
        logoContainer.addComponent(l);
        
        //Textfields for signin form
        TextField username = new TextField("", "Username", 20, ANY);
        TextField password = new TextField("", "Password", 20, PASSWORD);

        //VerifyValidate object 
        VerifyValidate vv = new VerifyValidate();

        //SignIn Button
        Button signIn = new Button("Sign In");
        signIn.requestFocus();
        signIn.addActionListener(e -> {
            if (username.getText().isEmpty() && password.getText().isEmpty()) {
                vv.alertBox("Please enter your username and password.");
            } else if (username.getText().isEmpty()) {
                vv.alertBox("Please enter your username in the required field.");
            } else if (password.getText().isEmpty()) {
                vv.alertBox("Please enter your password in the required field.");
            } else {
                new Dashboard(res).show();
            }

        });
        
        //SignUp button
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUp(res).show());
        signUp.setUIID("Link");
        
        //Label with message for signup link
        Label doneHaveAnAccount = new Label("Don't have an account?");
        Label topPad = new Label();
        topPad.setUIID("TopPad");

        //Container for main components
        Container upContainer = encloseY(topPad,
                encloseCenter(logoContainer),
                (username),
                (password),
                createLineSeparator(),
                signIn,
                encloseCenter(doneHaveAnAccount, signUp)
        );

        //Sets upContainer to scrollable
        upContainer.setScrollableY(true);
        
        //Adds container to page
        add(CENTER, upContainer);
       
    }
} 

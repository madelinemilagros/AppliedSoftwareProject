package com.goalup.madelinemerced;

import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

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
        VerifyValidate vv = new VerifyValidate();

        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        signIn.addActionListener(e -> new SignIn(hi).show());
        signIn.setUIID("Link");
        Label alreadyHaveAnAccount = new Label("Already have an account?");
        Label topPad = new Label();
        topPad.setUIID("TopPad");

        Container content = BoxLayout.encloseY(
                topPad,
                FlowLayout.encloseCenterMiddle(flowLabel),
                createLineSeparator(),
                (username),
             
                (email),
              
                (password),
                createLineSeparator(),
                signUp,
                
                FlowLayout.encloseCenter(alreadyHaveAnAccount, signIn)
        );

        Validator val = new Validator();
        val.addConstraint(password, new Constraint() {
            public boolean isValid(Object value) {

                String v = (String) value;
                if (v.length() < 8) {
                    return false;
                }
                for (int i = 0; i < v.length(); i++) {
                    char c = v.charAt(i);
                    if (c >= '0' && c <= '9' || c >= 'a' && c <= 'z') {
                        continue;
                    }
                    return false;
                }
                return true;
            }

            public String getDefaultFailMessage() {
                return "Must be valid phone number";
            }
        });
        
      
                
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        signUp.requestFocus();
        signUp.addActionListener(e -> {
            enter(username, email, password,
                    super.getComponentForm(), hi, vv);
//            Validator validator = new Validator();
//
//            validator.addConstraint(email, RegexConstraint.validEmail());
//            if (!val.isValid()) {
//                vv.alertBox("Password must include one number and be at least 8 letters");
//            } else if (!validator.isValid()) {
//                vv.alertBox("Invalid Email Please try again.");
//            } else if (username.getText().isEmpty() && email.getText().isEmpty()
//                    && password.getText().isEmpty()) {
//                vv.alertBox("Please enter a username, email and password.");
//            } else if (username.getText().isEmpty() && email.getText().isEmpty()) {
//                vv.alertBox("Please enter a username and email.");
//            } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
//                vv.alertBox("Please enter a username and password.");
//            } else if (email.getText().isEmpty() && password.getText().isEmpty()) {
//                vv.alertBox("Please enter a valid email and password.");
//            } else if (username.getText().isEmpty()) {
//                vv.alertBox("Please enter a username.");
//            } else if (email.getText().isEmpty()) {
//                vv.alertBox("Please enter a email.");
//            } else if (password.getText().isEmpty()) {
//                vv.alertBox("Please enter a password");
//            } else {

        });
    }

    public void enter(TextField usernameTF, TextField emailTF, TextField passwordTF,
            Form newForm, Resources hi, VerifyValidate vv) {

        HashMap<String, String> pairHere = new HashMap<String, String>();

        //Storage Management
        ArrayList<MyObject> profile = MyObject.getProfile();
        MyObject p = new MyObject();

        String username = "ProfileUsername";
        String email = "ProfileEmail";
        String password = "ProfilePassword";
        
        try (OutputStream os = Storage.getInstance().createOutputStream(username);) {
            try {
                String usernameText = usernameTF.getText();
                profile.add(p);
                p.saveProfile();
                os.write(usernameText.getBytes("UTF-8"));

            } catch (NumberFormatException nfe) {
                vv.alertBox("Please enter only numbers in the points field.");

            }
        } catch (IOException err) {
            Log.e(err);
        }
        try (OutputStream os = Storage.getInstance().createOutputStream(email);) {
            try {
                String emailText = emailTF.getText();
                profile.add(p);
                p.saveProfile();
                os.write(emailText.getBytes("UTF-8"));
                ;

            } catch (NumberFormatException nfe) {
                vv.alertBox("Please enter only numbers in the points field.");

            }
        } catch (IOException err) {
            Log.e(err);
        }

        try (OutputStream os = Storage.getInstance().createOutputStream(password);) {
            try {
                String passwordText = passwordTF.getText();
                profile.add(p);
                p.saveProfile();
                os.write(passwordText.getBytes("UTF-8"));

            } catch (NumberFormatException nfe) {
                vv.alertBox("Please enter only numbers in the points field.");

            }
        } catch (IOException err) {
            Log.e(err);
        }
        
       
        

        new Dashboard(hi).show();
    }

  

};

//End Subclass SignUp
//Holds points with comma deliminator


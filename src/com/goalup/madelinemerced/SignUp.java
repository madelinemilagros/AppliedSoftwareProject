package com.goalup.madelinemerced;

/** 
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Doug
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Mar 2, 2019
 * @Subclass SignUp Description: 
 */

//Imports
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;

//public class SignUp extends BaseForm {
//
//    public SignUp(Resources res) {
//        super(new BorderLayout());
//
//        if (!Display.getInstance().isTablet()) {
//            BorderLayout bl = (BorderLayout) getLayout();
//            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
//            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
//        }
//
//        getTitleArea().setUIID("Container");
//        setUIID("SignIn");
//
//        Form loginForm = new Form();
//        loginForm.setLayout(new GridLayout(1, 1));
//        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//        center.setUIID("ContainerWithPadding");
//
//        Image logo = res.getImage("LogoHeader.png");
//        Label l = new Label(logo);
//
//        Button signUp = new Button("Sign Up");
//        Button log = new Button("Login");
//        
//        TextField name = new TextField("", "Name", 12, TextField.ANY);
//        TextField username = new TextField("", "Username", 12, TextField.ANY);
//        TextField password = new TextField("", "Password", 12, TextField.PASSWORD);
//        TextField confirmPass = new TextField("", "Confirm Password", 12, TextField.PASSWORD);
//        
//        username.setSingleLineTextArea(false);
//        password.setSingleLineTextArea(false);
//        
//        log.addActionListener(e -> new LogIn(res).show());
//        log.setUIID("Link");
//        Label doneHaveAnAccount = new Label("Existing Account?");
//        signUp.addActionListener(e -> new Goal(res).show());
//        
//        Container content = BoxLayout.encloseY(
//                (name),(username), (password), (confirmPass),
//                createLineSeparator(),
//                signUp,
//                FlowLayout.encloseCenter(doneHaveAnAccount, log)
//        );
//
//        Container flow = new Container(new FlowLayout(Component.CENTER));
//        flow.addComponent(l);
//        center.addComponent(flow);
//
//        loginForm.add(center);
//        loginForm.add(content);
//        add(BorderLayout.CENTER, loginForm);
//    }
//
//}
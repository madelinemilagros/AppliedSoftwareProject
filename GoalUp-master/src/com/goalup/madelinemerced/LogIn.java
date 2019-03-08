/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.goalup.madelinemerced;

import com.codename1.components.FloatingHint;
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

public class LogIn extends BaseForm {

    public LogIn(Resources res) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }

        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        Form loginForm = new Form();
        loginForm.setLayout(new GridLayout(1, 1));
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("ContainerWithPadding");

        Image logo = res.getImage("LogoHeader.png");
        Label l = new Label(logo);
        Button signUp = new Button("Sign Up");
        Button log = new Button("Login");
        TextField username = new TextField("", "Username", 12, TextField.ANY);
        TextField password = new TextField("", "Password", 12, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        
//        signUp.addActionListener(e -> new SignUp(res).show());
//        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        log.addActionListener(e -> new Dashboard(res).show());
        Container content = BoxLayout.encloseY(
                (username), (password),
                createLineSeparator(),
                log,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );

        Container flow = new Container(new FlowLayout(Component.CENTER));
        flow.addComponent(l);
        center.addComponent(flow);

        loginForm.add(center);
        loginForm.add(content);
        add(BorderLayout.CENTER, loginForm);
    }

}

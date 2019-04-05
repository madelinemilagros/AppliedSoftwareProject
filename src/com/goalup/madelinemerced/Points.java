package com.goalup.madelinemerced;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Doug
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Mar 2, 2019
 * @Subclass Points Description:
 */
//Imports
import com.codename1.io.Storage;
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

public class Points extends BaseForm {

    public Points(Resources res) {
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
        Label pointsLabel = new Label("How many points is this goal worth?");
        Button Enter = new Button("Enter");
        TextField points = new TextField("", "points", 12, TextField.ANY);
        points.setSingleLineTextArea(false);
//        Enter.addActionListener(e -> new Dashboard(res).show());

        Enter.addActionListener(e -> { 
            String t2 = points.getText();
            MyObject o = new MyObject();
            o.setY(t2);
            Storage.getInstance().writeObject("SavedData", o);
            new Dashboard(res).show();
        });

        Container content = BoxLayout.encloseY(
                (pointsLabel), (points),
                createLineSeparator(),
                Enter
        );

        Container flow = new Container(new FlowLayout(Component.CENTER));
        flow.addComponent(l);
        center.addComponent(flow);

        loginForm.add(center);
        loginForm.add(content);
        add(BorderLayout.CENTER, loginForm);
    }

}

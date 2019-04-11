package com.goalup.madelinemerced;

import com.codename1.components.FloatingActionButton;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Apr 7, 2019
 * @Subclass Dashboard Description:
 */
//Imports
//Begin Subclass Dashboard
public class Dashboard extends BaseForm {

    private Form current;
    private Resources theme;
    int pointsTotal;
    int cPointsTotal;
    int pointsInt;
    CheckBox cb = new CheckBox();

    public Dashboard(Resources res) {
        super("", BoxLayout.y());
        Toolbar tb = super.getToolbar();
        setToolbar(tb);

        //Logo Image
        Image logo = res.getImage("LogoHeader.png");
        Label l = new Label(logo);

        //Labels and formatting for dashboard trackers
        Container landingPageButtons = new Container(new GridLayout(2, 2));
        Label dailyPointsTotal = new Label("Daily Points Total");
        Label pointsTracker = new Label("40/100 points");

        landingPageButtons.add(dailyPointsTotal);
        landingPageButtons.add(pointsTracker);

        //Flow Container
        Container flowLabel = new Container(new FlowLayout(Component.CENTER));

        //Adds Logo
        flowLabel.addComponent(l);

        Label allTotal = new Label();
        Label dailyTotal = new Label();

        //List of Goals Formatting
        Container center = new Container(new GridLayout(2, 1));
        Label goalsList = new Label();
        center.add(goalsList);

        //CheckBox completeCB = new CheckBox();
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.addActionListener(e -> {
            Label tf = new Label("Add New Reward or Goal?");
            Command goal = new Command("Goal");
            Command reward = new Command("Reward");
            Command cancel = new Command("Cancel");
            Command result = Dialog.show(" ", tf, goal, reward, cancel);
            if (goal == result) {
                Goal(super.getComponentForm(), logo, allTotal, dailyTotal);
            } else if (reward == result) {
                Reward(logo, allTotal, dailyTotal);
            } else {

            }

        });
        MyObject g = new MyObject();

        //Dashboard Point Total Holders (cumulative and daily) 
        Container flow = new Container(new GridLayout(1, 2));
        flow.add(allTotal);
        flow.add(dailyTotal);
        flowLabel.addComponent(landingPageButtons);

        //add to main form
        add(flowLabel);
        add(flow);
        add(center);
        for (String file : Storage.getInstance().listEntries()) {
            createFileEntry(super.getComponentForm(), file, dailyTotal, allTotal);
        }

        CheckBox checkBox = getCheckbox();
        checkBox.addChangeListener(e -> {
            if (checkBox.isSelected()) {
                setCheckbox(checkBox);
                int total = method(pointsTotal);
                dailyTotal.setText(Integer.toString(total));
                allTotal.setText(Integer.toString(total));
                System.out.print(date());
            } else if (!checkBox.isSelected()) {
                pointsTotal = 0;
                String dT = allTotal.getText();
                int total = Integer.parseInt(dT);
                method(total);
                allTotal.setText(Integer.toString(total));
            }
        });

        add(fab);
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });
    }

    public void setCheckbox(CheckBox completeCB) {
        cb = completeCB;
    }

    public CheckBox getCheckbox() {
        return cb;
    }

    private void createFileEntry(Form hi, String file, Label dailyTotal, Label allTotal) {
        Label goal = new Label(file);
        CheckBox completeCB = new CheckBox();
        Button points = new Button("");
//        Button delete = new Button();
//        FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
        Container content = BorderLayout.center(goal);
//        delete.addActionListener(e -> {
//            Storage.getInstance().deleteStorageFile(file);
//            content.setY(hi.getWidth());
//            hi.getContentPane().animateUnlayoutAndWait(150, 255);
//            hi.removeComponent(content);
//            hi.getContentPane().animateLayout(150);
//        });

        try (InputStream is = Storage.getInstance().createInputStream(file);) {
            String s = Util.readToString(is, "UTF-8");
            points.setText(s);
        } catch (IOException err) {
            Log.e(err);
        }
        
        setCheckbox(completeCB);

        content.add(BorderLayout.EAST, BoxLayout.encloseX(points, completeCB));
        hi.add(content);
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }

    public Form Goal(Form hi, Image logo, Label allTotal, Label dailyTotal) {
        Form newForm = new Form();
        Toolbar tb = super.getToolbar();
        newForm.setToolbar(tb);
        tb.addSearchCommand(e -> {
        });

        Label l = new Label(logo);

        //Flow Container
        Container logoForm = new Container(new FlowLayout(Component.CENTER));

        //Adds Logo
        logoForm.addComponent(l);
        //Storage Management
        ArrayList<MyObject> goals = MyObject.getGoals();
        MyObject g = new MyObject();
        //TextFields
        TextField goalTF = new TextField("", "Goal", 16, TextField.ANY);
        TextArea pointsTF = new TextArea(2, 2);

        pointsTF.setHint("Points");
        Button enter = new Button("Enter");

        enter.addActionListener(e -> {
            //Action listener for enter button
            try (OutputStream os = Storage.getInstance().createOutputStream(goalTF.getText());) {
                int pointsInt = Integer.parseInt(pointsTF.getText());
                setPoints(pointsInt);
                int dm = method(pointsInt);
                os.write(pointsTF.getText().getBytes("UTF-8"));
                createFileEntry(newForm, goalTF.getText(), allTotal, dailyTotal);
                newForm.getContentPane().animateLayout(250);
                hi.show();
            } catch (IOException err) {
                Log.e(err);
            }

        });

        Container goalEnter = BoxLayout.encloseXNoGrow(goalTF, pointsTF);
        Container count = new Container();
        count.add(
                GridLayout.encloseIn(
                        (goalEnter)
                ));
        logoForm.add(count);
        newForm.add(logoForm);
        newForm.add(enter);

        newForm.show();
        return newForm;
    }

    public Form Reward(Image logo, Label allTotal, Label dailyTotal) {
        Form newForm = new Form();
        Toolbar tb = super.getToolbar();
        newForm.setToolbar(tb);
        tb.addSearchCommand(e -> {
        });

        Label l = new Label(logo);

        //Flow Container
        Container logoForm = new Container(new FlowLayout(Component.CENTER));

        //Adds Logo
        logoForm.addComponent(l);

        //Storage Management
        ArrayList<MyObject> rewards = MyObject.getRewards();
        MyObject r = new MyObject();

        //TextFields
        TextField rewardTF = new TextField("", "Reward", 16, TextField.ANY);
        TextArea pointsTF = new TextArea(2, 2);

//        goalTF.setWidth(LEFT);
        pointsTF.setHint("Points");
        Button enter = new Button("Enter");

        enter.addActionListener(e -> {
            //Action listener for enter button
            try {
                int pointsInt = Integer.parseInt(pointsTF.getText());
//                setPoints(pointsInt);
//                int dm = method(pointsInt);
//
//                String dailyTString = Integer.toString(dm);
                r.setReward(rewardTF.getText());
                r.setRPoints(pointsTF.getText());

                MyObject.setRewards(rewards);
                rewards.add(r);
                add(addReward(r, dailyTotal, allTotal));
                show();
                newForm.add(addReward(r, dailyTotal, allTotal));

            } catch (NumberFormatException nfe) {

            }

        });

        Container rewardEnter = BoxLayout.encloseXNoGrow(rewardTF, pointsTF);
        Container count = new Container();
        count.add(
                GridLayout.encloseIn(
                        (rewardEnter)
                ));
        logoForm.add(count);
        newForm.add(logoForm);
        newForm.add(enter);

        newForm.show();
        return newForm;
    }

    private Container addReward(MyObject s, Label dailyTotal, Label allTotal) {
        Label reward = new Label(s.getReward());
        Button points = new Button(s.getRPoints());
        CheckBox completeCB = new CheckBox();
        Container row = BoxLayout.encloseXNoGrow(reward, points, completeCB);
        Container count = new Container();
        count.add(
                GridLayout.encloseIn(
                        (row)
                ));

        Container cnt = BoxLayout.encloseY(
                (count), (createLineSeparator())
        );

        completeCB.addChangeListener(e -> {
            if (completeCB.isSelected()) {
                int total = method(pointsTotal);
//                dailyTotal.setText(Integer.toString(total));
//                allTotal.setText(Integer.toString(total));
                System.out.print(date());

            } else if (!completeCB.isSelected()) {
//                pointsTotal = 0;
//                String dT = allTotal.getText();
//                int total = Integer.parseInt(dT);
//                method(total);
//                allTotal.setText(Integer.toString(total));
            }
        });
        return cnt;
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public String date() {
        //Date formatter
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }

    public void setPoints(int points) {
        pointsInt = points;
    }

    public int getPoints() {
        return pointsInt;
    }

    public int method(int p) {
        pointsTotal = p + pointsTotal;
        met(pointsTotal);
        return pointsTotal;
    }

    public int met(int p) {
        cPointsTotal = p + cPointsTotal;
        return cPointsTotal;
    }

} //End Subclass Dashboard

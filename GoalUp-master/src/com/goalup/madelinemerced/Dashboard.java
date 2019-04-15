package com.goalup.madelinemerced;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.OnOffSwitch;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
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
import com.codename1.ui.List;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
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
    boolean tr;
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
//          Container flabel = new Container(new FlowLayout(Component.CENTER));
//          Label tf = new Label("Add New Reward or Goal?");
//          flabel.add(tf);
            Command goal = new Command("Goal");
            Command reward = new Command("Reward");
            Command cancel = new Command("Cancel");
            Command result = Dialog.show("Add New Reward or Goal? ", " ", goal, reward, cancel);
            if (goal == result) {
                Goal(super.getComponentForm(), logo, allTotal, dailyTotal);

            } else if (reward == result) {
                Reward(super.getComponentForm(), logo, allTotal, dailyTotal);
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
            createFileEntry(super.getComponentForm(), file, g.getType(), dailyTotal, allTotal);
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
//        for (String reward : Storage.getInstance().listEntries()) {
//            createFileEntryReward(super.getComponentForm(), reward, g.getType(), dailyTotal, allTotal);
//        }
        tb.addSearchCommand(e -> {
        });
        super.revalidate();
    }

    Dashboard() {
    }

    public void setCheckbox(CheckBox completeCB) {
        cb = completeCB;
    }

    public CheckBox getCheckbox() {
        return cb;
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
        String goal = "goal";

        //TextFields
        TextField goalTF = new TextField("", "Goal", 16, TextField.ANY);
        TextField pointsTF = new TextField("", "Points", 2, TextField.ANY);

//        pointsTF.setHint("Points");
        Button enter = new Button("Enter");

        enter.addActionListener(e -> {
            //Action listener for enter button
            try (OutputStream os = Storage.getInstance().createOutputStream(goalTF.getText());) {
                int pointsInt = Integer.parseInt(pointsTF.getText());

                //Saves points with leading zeros for formating and structure
                if (pointsInt < 10) {
                    String pointsZero = "00" + pointsTF.getText();
                    setPoints(pointsInt);
                    os.write(pointsZero.getBytes("UTF-8"));

                } else if (9 < pointsInt && pointsInt < 100) {
                    String pointsZero = "0" + pointsTF.getText();
                    setPoints(pointsInt);
                    os.write(pointsZero.getBytes("UTF-8"));
                } else {
                    setPoints(pointsInt);
                    os.write(pointsTF.getText().getBytes("UTF-8"));
                }

                g.setType(goal);
                int dm = method(pointsInt);
                os.write("  ".getBytes("UTF-8"));
                os.write(g.getType().getBytes("UTF-8"));
                createFileEntry(newForm, goalTF.getText(), g.getType(), allTotal, dailyTotal);
                newForm.getContentPane().animateLayout(250);
                hi.revalidate();

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

    public void createFileEntry(Form hi, String file, String t, Label dailyTotal, Label allTotal) {

        //Components for container
        Label goal = new Label(file);
        CheckBox completeCB = new CheckBox();
        Label points = new Label("");
        Label holder = new Label("");
//Label pointsDescription = new Label("points ");
        //Container for holding components
        Container content = BorderLayout.center(holder);

        //Reads in the storage
        try (InputStream is = Storage.getInstance().createInputStream(file);) {
            String s = Util.readToString(is, "UTF-8");

            //Checks if goal exists and sorts accordingly for display
            if (s.contains("goal")) {

                //Checks for leading zeros for formatting of points label
                if (s.substring(0, 1).contains("0")) {
                    points.setText(s.substring(1, 3) + " points");
                    if (s.substring(1, 2).contains("0")) {
                        points.setText(s.substring(2, 3) + " points");
                    }
                } else {
                    points.setText(s.substring(0, 3) + " points");
                }

                System.out.print(s.substring(4, s.length()));
                content.add(BorderLayout.CENTER, BoxLayout.encloseX(goal));
                content.add(BorderLayout.EAST, BoxLayout.encloseX(points, completeCB));
            }

        } catch (IOException err) {
            Log.e(err);
        }

        setCheckbox(completeCB);

        //Adds storage contents with goals to main form
        hi.add(content);
    }

    public Form Reward(Form hi, Image logo, Label allTotal, Label dailyTotal) {
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
        String reward = "reward";
        //TextFields
        TextField rewardTF = new TextField("", "Reward", 16, TextField.ANY);
        TextArea pointsTF = new TextArea(2, 2);

//        goalTF.setWidth(LEFT);
        pointsTF.setHint("Points");
        Button enter = new Button("Enter");

        enter.addActionListener(e -> {
            //Action listener for enter button
            try (OutputStream os = Storage.getInstance().createOutputStream(rewardTF.getText());) {
                int pointsInt = Integer.parseInt(pointsTF.getText());

                //Saves points with leading zeros for formating and structure
                if (pointsInt < 10) {
                    String pointsZero = "00" + pointsTF.getText();
                    setPoints(pointsInt);
                    os.write(pointsZero.getBytes("UTF-8"));

                } else if (9 < pointsInt && pointsInt < 100) {
                    String pointsZero = "0" + pointsTF.getText();
                    setPoints(pointsInt);
                    os.write(pointsZero.getBytes("UTF-8"));
                } else {
                    setPoints(pointsInt);
                    os.write(pointsTF.getText().getBytes("UTF-8"));
                }

                r.setType(reward);
                int dm = method(pointsInt);
                os.write("  ".getBytes("UTF-8"));
                os.write(r.getType().getBytes("UTF-8"));
                createFileEntry(newForm, rewardTF.getText(), r.getType(), allTotal, dailyTotal);
                newForm.getContentPane().animateLayout(250);
                hi.revalidate();

                hi.show();

            } catch (IOException err) {
                Log.e(err);
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

    public void createFileEntryReward(Form hi, String file, String type, Label dailyTotal, Label allTotal) {

        Label reward = new Label(file);
        CheckBox completeCB = new CheckBox();
        completeCB.setUIID("Star");

        Label points = new Label("");
        Label holder = new Label("");

        Container content = BorderLayout.center(holder);
        try (InputStream is = Storage.getInstance().createInputStream(file);) {
            String s = Util.readToString(is, "UTF-8");

            if (s.contains("reward")) {

                //Checks for leading zeros for formatting of points label
                if (s.substring(0, 1).contains("0")) {
                    points.setText(s.substring(1, 3));
                    if (s.substring(1, 2).contains("0")) {
                        points.setText(s.substring(2, 3));
                    }
                } else {
                    points.setText(s.substring(0, 3));
                }

                System.out.print(s.substring(4, s.length()));
                content.add(BorderLayout.CENTER, BoxLayout.encloseX(reward));
                content.add(BorderLayout.EAST, BoxLayout.encloseX(points, completeCB));
            } else {

            }

        } catch (IOException err) {
            Log.e(err);
        }
        setCheckbox(completeCB);

        hi.add(content);
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

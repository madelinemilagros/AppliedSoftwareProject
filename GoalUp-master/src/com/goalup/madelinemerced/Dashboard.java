package com.goalup.madelinemerced;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.GeneralPath;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Apr 7, 2019
 * @Subclass Dashboard Description:
 */
//Imports
//Image credit https://www.pexels.com/photo/brown-wooden-dock-over-body-of-water-1227520/
//Begin Subclass Dashboard
public class Dashboard extends BaseForm {

    private Form current;
    private Resources theme;
    private String inputStreamString;
    int pointsTotal;
    int cPointsTotal;
    int pointsInt;
    int dailyPointsTotal;
    int pointsValueInt;
    int i;

    String mainRewardPoints;
    boolean tr;
    CheckBox cb = new CheckBox();
    Map.Entry<String, String> runningMap;

    public Dashboard(Resources res) {
        super("", BoxLayout.y());
        Toolbar tb = super.getToolbar();
        setToolbar(tb);

        //Logo Image
        Image logo = res.getImage("Logo@0,1x.png");
        Label l = new Label(logo);

        Image img = res.getImage("selected-backgroundFilled.png");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 4) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 4);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("TopPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        sl.setUIID("BottomPad");
        Container quoteCont = new Container(new FlowLayout(Component.CENTER));
        Container quoteLines = BoxLayout.encloseY();

        //Labels and formatting for dashboard trackers
        Container landingPageButtons = BoxLayout.encloseY();
//        String quote1 = ("We are what we repeatedly do.");
//        String quote2 = ("Excellence, therefore, is not an act but a habit. "
////                + "- Aristotle");
////        Label quoteOfDay1 = new Label();
////        Label quoteOfDay2 = new Label();
////
////        quoteOfDay1.setUIID("Quote");
////        quoteOfDay2.setUIID("Quote");
////        quoteOfDay1.setText(quote1);
////        quoteOfDay2.setText(quote2);
////
////        quoteLines.add(quoteOfDay1);
////        quoteLines.add(quoteOfDay2);
////        quoteCont.add(quoteLines);
        //Flow Container
        Container flowLabel = new Container(new FlowLayout(Component.CENTER));
        Label date = new Label();
        date.setText(date());
        Label dTotal = new Label();

        String getDailyTotalString = Integer.toString(getDailyTotal());

        Label rightPad = new Label("");
        Label leftPad = new Label("");
        Label rightPad1 = new Label("");
        Label leftPad1 = new Label("");
        rightPad.setUIID("RightPadding");
        leftPad.setUIID("LeftPadding");
        rightPad1.setUIID("RightPadding1");
        leftPad1.setUIID("LeftPadding1");

        Label topPad = new Label("");
        Label topPad1 = new Label("");
        topPad.setUIID("TopPad1");
        topPad1.setUIID("TopPad1");

        //CumulativePointsContainer
        Container cumulativeContainer = BoxLayout.encloseY();
        Label pointsTracker = new Label();
        setMainRewardPoints("100");
        Label descripTotal = new Label("Points Total");
        Label descripReward = new Label("Reward Points Total");

        descripTotal.setUIID("pointsHeaders");
        descripReward.setUIID("pointsHeaders");

//        Label pointsWord = new Label("points");
        pointsTracker.setText(getDailyTotalString);
        Container centerPoints = new Container(new BorderLayout(Component.CENTER));
//        pointsTracker.setUIID("PaddedLabel");
        cumulativeContainer.add(centerPoints);
        Label allTotal = new Label();

        cumulativeContainer.add(allTotal);
//        landingPageButtons.add(date);
        date.setUIID("Qte");
        landingPageButtons.add(quoteCont);
        //Container for Dashboard Title Labels
//       

        Label rewardPoints = new Label("100");

        Container logoDate = BoxLayout.encloseY(l, date);
//        
//          tb.addComponentToSideMenu(LayeredLayout.encloseIn(
//                sl,
//                FlowLayout.encloseCenterBottom(
//                        new Label(res.getImage("headshot.jpg"), "PictureWhiteBackgrond"))
//        ));
        //Adds Logo
//        flowLabel.addComponent(date);
//        flowLabel.addComponent(l);
        flowLabel.add(logoDate);
        flowLabel.add(landingPageButtons);

//        Container p = new Container(new GridLayout(1,1));
//        p.add(leftPad, pointsTracker);
//        Container r = new Container(new GridLayout(1,1));
//        r.add(rightPad, rewardPoints);
        flowLabel.add(LayeredLayout.encloseIn(
                sl,
                BoxLayout.encloseY(topPad,
                        BoxLayout.encloseX(leftPad1, descripTotal),
                        BoxLayout.encloseX(leftPad, pointsTracker)),
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("shoesSmall.png"), "PictureWhiteBackgrond")),
                BoxLayout.encloseY(topPad1,
                        BoxLayout.encloseX(rightPad1, descripReward),
                        BoxLayout.encloseX(rightPad, rewardPoints))
        ));

        //List of Goals Formatting
        Container center = new Container(new GridLayout(2, 1));
        Label goalsList = new Label();
        center.add(goalsList);

        //Floating Action Button to add Goal or Reward
        FloatingActionButton fab = FloatingActionButton.createFAB(
                FontImage.MATERIAL_ADD);
        fab.addActionListener(e -> {
            Command goal = new Command("Goal");
            Command reward = new Command("Reward");
            Command cancel = new Command("Cancel");
            Command result = Dialog.show("Add New Reward or Goal? ", " ",
                    goal, reward, cancel);
            if (goal == result) {

                new AddGoal(res, allTotal).show();
                    } else if (reward == result) {
                new AddReward(res, allTotal, dTotal).show();
            } else {

            }

        });

        //Creates Storage Object
        MyObject g = new MyObject();

        //add to main form
        add(flowLabel);
//        add(flowTotals);
        add(center);
        for (String file : Storage.getInstance().listEntries()) {
            createFileEntry(super.getComponentForm(), file, g.getType(),
                    pointsTracker);

            try (InputStream is = Storage.getInstance().createInputStream(file);) {
                String s = Util.readToString(is, "UTF-8");
//
            } catch (IOException err) {
                Log.e(err);
            }
        }

        add(fab);

        super.addSideMenu(res);
        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_HELP_OUTLINE,
                e -> new Profile(res).show());

    }

    Dashboard() {
    }

    public void setCheckbox(CheckBox completeCB) {
        cb = completeCB;
    }

    public CheckBox getCheckbox() {
        return cb;
    }

    public void createFileEntry(Form hi, String file, String t, Label dailyTotal) 
    {
        //Components for container
        Label goal = new Label(file);
        CheckBox completeCB = new CheckBox();
        Label points = new Label("");
        Label holder = new Label("");
        //Container for holding components
        Container content = BorderLayout.center(holder);

        HashMap<String, String> entryList = new HashMap<String, String>();
        String entryKey;
        String entryValue = null;
        i = i + 1;

        Map<CheckBox, String> testing = new HashMap<CheckBox, String>();

        //Reads in the storage
        try (InputStream is = Storage.getInstance().createInputStream(file);) {
            String s = Util.readToString(is, "UTF-8");
            if (s.contains("goal")) {
                if (s.substring(0, 1).contains("0")) {
                    entryList.put(file, s.substring(1, 3));
                    points.setText(s.substring(1, 3) + " points");
                    if (s.substring(1, 2).contains("0")) {
                        entryList.put(file, s.substring(2, 3));
                        points.setText(s.substring(2, 3) + " points");
                    }
                } else {
                    entryList.put(file, s.substring(0, 3));
                    points.setText(s.substring(0, 3) + " points");
                }
                for (Map.Entry<String, String> entry : entryList.entrySet()) {

                    entryValue = entry.getValue();
                    entryKey = entry.getKey();
                    System.out.print("Entry Key: " + entry.getKey() + "\n"
                            + " Entry Value: " + entry.getValue() + "\n");

                    setMap(entry);

                }

                testing.put(completeCB, entryValue);
                content.add(BorderLayout.CENTER, BoxLayout.encloseX(goal));
                content.add(BorderLayout.EAST, BoxLayout.encloseX(points,
                        completeCB));

            } else {

            }

        } catch (IOException err) {
            Log.e(err);
        }

        //Map with date, and testing hashmap
        Map<Map<CheckBox, String>, Date> dateCheck
                = new HashMap<Map<CheckBox, String>, Date>();
        Date currentDate = new Date();
        currentDate.getTime();
        dateCheck.put(testing, currentDate);

        //Checkbox Listener
        completeCB.addActionListener(e -> {
            if (completeCB.isSelected()) {
                String pointValueTest = testing.get(completeCB);
                String dateTest = dateCheck.get(testing).toString();
                pointsValueInt = Integer.parseInt(pointValueTest);
                setPoints(pointsValueInt);
                dailyTotal.setText(dailyTotal());

            } else if (!completeCB.isSelected()) {
                int removePoints = 0 - pointsValueInt;

                setPoints(removePoints);
//                
                dailyTotal.setText(dailyTotal());

            }

        });

        //Adds storage contents with goals to main form
        super.add(content);
    }

    private Component createSeparator() {
        Label l = new Label("", "Separator");
        l.setShowEvenIfBlank(true);
        return l;
    }

    public void setMap(Map.Entry<String, String> runningMap) {
        this.runningMap = runningMap;
    }

    public Map.Entry<String, String> getMap() {
        return runningMap;
    }

    public void createFileEntryReward(Form hi, String file, String type) {
        Label reward = new Label(file);
        CheckBox c = new CheckBox();
        c.setSelected(true);
        c.setToggle(true);
        c.setTextPosition(Component.LEFT);
        c.setIcon(FontImage.createMaterial(FontImage.MATERIAL_STAR_BORDER,
                "UncheckedIcon", 4));
        c.setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_STAR,
                "CheckedIcon", 4));
        HashMap<String, String> listRewards = new HashMap<String, String>();

        MyObject obj = new MyObject();
        String entryKey;
        String entryValue = null;
        Label points = new Label("");
        Label holder = new Label("");

        Container content = BorderLayout.center(holder);
        try (InputStream is = Storage.getInstance().createInputStream(file);) {
            String s = Util.readToString(is, "UTF-8");
            if (s.contains("reward")) {
                if (s.substring(0, 1).contains("0")) {
                    listRewards.put(file, s.substring(1, 3));
                    points.setText(s.substring(1, 3) + " points");
                    if (s.substring(1, 2).contains("0")) {
                        listRewards.put(file, s.substring(2, 3));
                        points.setText(s.substring(2, 3) + " points");
                    }
                } else {
                    listRewards.put(file, s.substring(0, 3));
                    points.setText(s.substring(0, 3) + " points");
                }
                for (Map.Entry<String, String> entry : listRewards.entrySet()) {

                    entryValue = entry.getValue();
                    entryKey = entry.getKey();
                    System.out.print("Entry Key: " + entry.getKey() + "\n"
                            + " Entry Value: " + entry.getValue() + "\n");

                    setMap(entry);

                }
                content.add(BorderLayout.CENTER, BoxLayout.encloseX(reward));
                content.add(BorderLayout.EAST, BoxLayout.encloseX(points, c));
            }

        } catch (IOException err) {
            Log.e(err);
        }

        hi.add(content);
    }

    public String mainRewardPoints(String s) {

        setMainRewardPoints(s);
        return s;
    }

    public void setMainRewardPoints(String s) {
        this.mainRewardPoints = s;
    }

    public String getMainRewardPoints() {
        return mainRewardPoints;
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public String date() {
        //Date formatter
        Date date = new Date();
        String pattern = "EEEE, MMMMM dd, yyyy";

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

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

    public void setDailyTotal(int dailyPointsTotal) {
        this.dailyPointsTotal = dailyPointsTotal;
    }

    public int getDailyTotal() {
        return dailyPointsTotal;
    }

    public String dailyTotal() {
        setDailyTotal(getDailyTotal() + getPoints());
        String getDailyTotalString = Integer.toString(getDailyTotal());
        System.out.print("Daily Total: " + getDailyTotal() + "\n");
//        dT.setText(getDailyTotalString);

        return getDailyTotalString;
    }

    public String getInputStreamString() {
        return inputStreamString;
    }

    public void setInputStreamString(String inputStreamString) {
        this.inputStreamString = inputStreamString;
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth()
                / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }

} //End Subclass Dashboard

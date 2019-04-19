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
        Image logo = res.getImage("LogoHeader.png");
        Label l = new Label(logo);
        
//        
        Image img = res.getImage("selected-backgroundFilled.png");
        if (img.getHeight() > Display.getInstance().getDisplayHeight()/4) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight()/4);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("TopPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

       
   
   
//   Image rewardImage = res.getImage("shoesSmall.png");
//        Label rewardImageLabel = new Label(rewardImage);

        Container quoteCont = new Container(new FlowLayout(Component.CENTER));
        Container quoteLines = BoxLayout.encloseY();

        //Labels and formatting for dashboard trackers
        Container landingPageButtons = BoxLayout.encloseY();
        String quote1 = ("We are what we repeatedly do.");
        String quote2 = ("Excellence, therefore, is not an act but a habit. - Aristotle");
        Label quoteOfDay1 = new Label();
        Label quoteOfDay2 = new Label();

        quoteOfDay1.setUIID("Quote");
        quoteOfDay2.setUIID("Quote");
        quoteOfDay1.setText(quote1);
        quoteOfDay2.setText(quote2);

        quoteLines.add(quoteOfDay1);
        quoteLines.add(quoteOfDay2);
//        quoteCont.setUIID("ImageOver");
        quoteCont.add(quoteLines);
    

//        //dailyPointsContainer
//        Container dailyPointsContainer = BoxLayout.encloseY();
//        Label dailyPointsTotal = new Label("Daily Points Total");
//        dailyPointsContainer.add(dailyPointsTotal);
        Label dTotal = new Label();
//        dTotal.setText(dailyTotal());
//        dailyPointsContainer.add(dTotal);
                   String getDailyTotalString = Integer.toString(getDailyTotal());

        //CumulativePointsContainer
        Container cumulativeContainer = BoxLayout.encloseY();
        Label pointsTracker = new Label();
        setMainRewardPoints("100");
        String divided = (dailyTotal());
        pointsTracker.setText(getDailyTotalString);
        Container centerPoints = new Container(new BorderLayout(Component.CENTER));
//        pointsTracker.setUIID("PaddedLabel");
        cumulativeContainer.add(centerPoints);
        Label allTotal = new Label();

        cumulativeContainer.add(allTotal);
//        cumulativeContainer.add(rewardImageLabel);
//cumulativeContainer.setUIID("ImageOverlay");
//landingPageButtons.setUIID("LineSeparator");
        //Dashboard Point Total Holders (cumulative and daily)
//        Container flow = new Container(new GridLayout(2,1));
//        flow.add(dTotal);
//        landingPageButtons.add(dailyPointsContainer);
        landingPageButtons.add(quoteCont);
//        landingPageButtons.add(cumulativeContainer);

        //Container for Dashboard Title Labels
//        landingPageButtons.add(dailyPointsTotal);
//        landingPageButtons.add(pointsTracker);
        //Flow Container
        Container flowLabel = new Container(new FlowLayout(Component.CENTER));

        //Adds Logo
        flowLabel.addComponent(l);
    

        flowLabel.add(landingPageButtons);
 flowLabel.add(LayeredLayout.encloseIn(
                sl,
         FlowLayout.encloseCenter(   
                BoxLayout.encloseX(
                        pointsTracker,
                        BoxLayout.encloseY(
                        new Label(res.getImage("shoesSmall.png"), "PictureWhiteBackgrond"))
                                       ,new Label("100 Points"),
 cumulativeContainer
         ))
 ));    
                


//List of Goals Formatting
        Container center = new Container(new GridLayout(2, 1));
        Label goalsList = new Label();
        center.add(goalsList);

        //Floating Action Button to add Goal or Reward
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.addActionListener(e -> {
            Command goal = new Command("Goal");
            Command reward = new Command("Reward");
            Command cancel = new Command("Cancel");
            Command result = Dialog.show("Add New Reward or Goal? ", " ", goal, reward, cancel);
            if (goal == result) {
                Goal(super.getComponentForm(), logo, allTotal, dTotal);

            } else if (reward == result) {
                Reward(super.getComponentForm(), logo, allTotal, dTotal);
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
            createFileEntry(super.getComponentForm(), file, g.getType(), dTotal, pointsTracker);

            try (InputStream is = Storage.getInstance().createInputStream(file);) {
                String s = Util.readToString(is, "UTF-8");
//
            } catch (IOException err) {
                Log.e(err);
            }
        }

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
        HashMap<String, String> pairHere = new HashMap<String, String>();

        enter.addActionListener(e -> {
            //Action listener for enter button
            try (OutputStream os = Storage.getInstance().createOutputStream(goalTF.getText());) {
                int pointsInt = Integer.parseInt(pointsTF.getText());
                goals.add(g);

                //Holds points with comma deliminator
                String points = pointsTF.getText();
                String type = " " + g.getType() + " ";
                String checked = g.getCheckbox();
                String gTextField = goalTF.getText() + " ";
                String space = " ";
                pairHere.put(goalTF.getText(), pointsTF.getText());
                g.setGoalPair(pairHere);
                System.out.print(g.getGoalPair());
                g.saveGoals();
                //Saves points with leading zeros for formating and structure
                if (pointsInt < 10) {
                    String pointsZero = "00" + points;
                    setPoints(pointsInt);
                    os.write(pointsZero.getBytes("UTF-8"));
                } else if (9 < pointsInt && pointsInt < 100) {
                    String pointsZero = "0" + points;
                    setPoints(pointsInt);
                    os.write(pointsZero.getBytes("UTF-8"));
                } else {
                    setPoints(pointsInt);
                    os.write(pointsTF.getText().getBytes("UTF-8"));
                }

                g.setType(goal);
                int dm = method(pointsInt);
                os.write(space.getBytes("UTF-8"));
//                os.write(goalTF.getText().getBytes("UTF-8"));
                os.write(space.getBytes("UTF-8"));
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
        //Container for holding components
        Container content = BorderLayout.center(holder);

        HashMap<String, String> entryList = new HashMap<String, String>();
        String entryKey;
        String entryValue = null;
        Map<String, Map.Entry<String, String>> map = new HashMap<>();

        i = i + 1;

//        System.out.print("Everytime the create file entry is accessed " + i + "\n");
        Map<CheckBox, String> testing = new HashMap<CheckBox, String>();

//        Map<Map.Entry<String, String>, Integer> mapt = new HashMap<>();
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

//                System.out.print("Substring: 0,1: " + s.substring(0, 1)
//                        + "\nSubstring: 1,2: " + s.substring(1, 2) + "\nSubstring: 2,3: "
//                        + s.substring(2, 3) + "\n");
                    setMap(entry);
//                    hi.add(createCheck(s,false)).add(createSeparator());

                }
//                        mapt.put(runningMap, i + 1);
//            System.out.print("This is the nested Map: " + mapt.entrySet() + "\n");

                testing.put(completeCB, entryValue);
                content.add(BorderLayout.CENTER, BoxLayout.encloseX(goal));
                content.add(BorderLayout.EAST, BoxLayout.encloseX(points, completeCB));
//Checks if goal exists and sorts accordingly for display
//            if (s.contains("goal")) {
////            Checks for leading zeros for formatting of points label
//                System.out.print(s.substring(4, s.length()));
//                entryList.put(entryKey, entryValue);
//            }
            } else {
            }//Checks if goal exists and sorts accordingly for display
//                if (s.contains("goal")) {
//            points.setText(s);
//                }
//            System.out.print(s.substring(4, s.length()));

        } catch (IOException err) {
            Log.e(err);
        }

        //Map with date, and testing hashmap
        Map<Map<CheckBox, String>, Date> dateCheck = new HashMap<Map<CheckBox, String>, Date>();
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
//                dailyTotal.setText(dailyTotal());
                allTotal.setText(dailyTotal());
//                System.out.print("DATE: " + dateTest + "\n");
//                createCheck(pointValueTest, true);
            } else if (!completeCB.isSelected()) {
                int removePoints = 0 - pointsValueInt;
//                String currentHolder = dailyTotal.getText();
//                int currentHolderInt = Integer.parseInt(currentHolder);
//                                int testingZero = currentHolderInt - removePoints;
//
//               
//                    setPoints(0);
//                    dailyTotal.setText(dailyTotal());
//                } else {
                setPoints(removePoints);
//                dailyTotal.setText(dailyTotal());
                allTotal.setText(dailyTotal());

            }

        });

        //Adds storage contents with goals to main form
        hi.add(content);
    }
    
//private Component createCheck(String t, boolean selected){
//        CheckBox c = new CheckBox(t);
//        c.setSelected(selected);
//        c.setGap(Display.getInstance().convertToPixels(3));
//        c.setToggle(true);
//        c.setTextPosition(Component.LEFT);
//        c.setIcon(FontImage.createMaterial(FontImage.MATERIAL_CHECK, "UncheckedIcon",4));
//        c.setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_CHECK, "CheckedIcon",4));
//        return c;
//        
//    }
//    
    private Component createSeparator(){
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
//        ArrayList<MyObject> rewards = MyObject.getRewards();
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

        MyObject obj = new MyObject();

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

//            if(completeCB.isSelected()){
////                mainRewardPoints(hi, g);
                completeCB.setSelected(true);
//                    obj.setCheckbox(true);
//                }

                if (completeCB.isSelected()) {
                    mainRewardPoints(s);
                }

                System.out.print(s.substring(4, s.length()));
                content.add(BorderLayout.CENTER, BoxLayout.encloseX(reward));
                content.add(BorderLayout.EAST, BoxLayout.encloseX(points, completeCB));
            } else {

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
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }

} //End Subclass Dashboard

package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass Dashboard Description: Takes data from project classes and displays 
 * them to the dashboard 
 * //Image credit https://www.pexels.com/photo/brown-wooden-dock-over-body-of-water-1227520/
 * 
 */

//Imports
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStream;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Dialog;
import com.codename1.ui.Command;
import com.codename1.ui.Toolbar;
import com.codename1.io.Storage;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Component;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.FloatingActionButton;
import static com.codename1.io.Log.e;
import static java.lang.Integer.parseInt;
import static com.codename1.io.Util.readToString;
import static com.codename1.ui.Display.getInstance;
import static com.codename1.ui.layouts.BoxLayout.y;
import static com.codename1.ui.FontImage.MATERIAL_ADD;
import static com.codename1.ui.layouts.BorderLayout.EAST;
import static com.codename1.ui.layouts.BoxLayout.encloseX;
import static com.codename1.ui.layouts.BoxLayout.encloseY;
import static com.codename1.ui.layouts.BorderLayout.center;
import static com.codename1.ui.FontImage.MATERIAL_HELP_OUTLINE;
import static com.codename1.components.FloatingActionButton.createFAB;
import static com.codename1.ui.layouts.FlowLayout.encloseCenterBottom;
import static com.codename1.ui.plaf.Style.BACKGROUND_IMAGE_SCALED_FILL;


//Begin Subclass Dashboard
public final class Dashboard extends BaseForm {

    //Classwide variables
    int i;
    int pointsInt;
    int pointsTotal;
    int cPointsTotal;
    int pointsValueInt;
    int dailyPointsTotal;

    private Form current;
    private Resources theme;
    private String inputStreamString;

    String mainRewardPoints;
    CheckBox cb = new CheckBox();
    Map.Entry<String, String> runningMap;

    //Dashboard Constructor
    Dashboard() {

    }

    /**
     * Method Dashboard: Displays user information gathered from other classes
     *
     * @param res
     */
    public Dashboard(Resources res) {

        //Inherits from BaseForm
        super("", y());

        //Inherits project toolbar properties
        Toolbar tb = super.getToolbar();

        //Saves toolbar to form
        setToolbar(tb);

        //Logo Image
        Image logo = res.getImage("LogoHeader.png");
        Label l = new Label(logo);

        //Background layer for points/reward tracker section
        Image img = res.getImage("selected-backgroundFilled.png");
        if (img.getHeight() > getInstance().getDisplayHeight() / 4) {
            img = img.scaledHeight(getInstance().getDisplayHeight() / 4);
        }

        //Scales background image for points/reward tracker
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("TopPad");
        sl.setBackgroundType(BACKGROUND_IMAGE_SCALED_FILL);
        sl.setUIID("BottomPad");
        Container quoteCont = new Container(new FlowLayout(CENTER));

        //Labels and formatting for dashboard trackers
        Container landingPageButtons = encloseY();

        //Flow Container
        Container flowLabel = new Container(new FlowLayout(CENTER));

        //Label to display date from date pointsTotalMethod
        Label date = new Label();
        date.setText(date());
        date.setUIID("Qte");

        //Parses getDailyTotal int into string for formatting
        Label dTotal = new Label();
        String getDailyTotalString = Integer.toString(getDailyTotal());

        //Labels for margin formatting
        Label topPad = new Label("");
        Label topPad1 = new Label("");
        Label leftPad = new Label("");
        Label leftPad1 = new Label("");
        Label rightPad = new Label("");
        Label rightPad1 = new Label("");

        //UIIDs for labels
        topPad.setUIID("TopPad1");
        topPad1.setUIID("TopPad1");
        leftPad.setUIID("LeftPadding");
        leftPad1.setUIID("LeftPadding1");
        rightPad.setUIID("RightPadding");
        rightPad1.setUIID("RightPadding1");

        //To test setMainRewardPoints method
        setMainRewardPoints("100");
        
        //Labels for reward bar headers
        Label descripTotal = new Label("Points Total");
        Label descripReward = new Label("Reward Points Total");
        descripTotal.setUIID("pointsHeaders");
        descripReward.setUIID("pointsHeaders");
        
        //PointsTracker Label
        Label pointsTracker = new Label();
        pointsTracker.setText(getDailyTotalString);
        Label allTotal = new Label();

        landingPageButtons.add(quoteCont);

        Label rewardPoints = new Label("100");

        //Container for logo and date centering
        Container logoDate = encloseY(l, date);
        flowLabel.add(logoDate);
        flowLabel.add(landingPageButtons);

        flowLabel.add(LayeredLayout.encloseIn(sl,
                encloseY(topPad,
                        encloseX(leftPad1, descripTotal),
                        encloseX(leftPad, pointsTracker)),
                encloseCenterBottom(
                        new Label(res.getImage("shoesSmall.png"), "PictureWhiteBackgrond")),
                encloseY(topPad1,
                        encloseX(rightPad1, descripReward),
                        encloseX(rightPad, rewardPoints))
        ));

        //List of Goals Formatting
        Container center = new Container(new GridLayout(2, 1));
        Label goalsList = new Label();
        center.add(goalsList);

        //Floating Action Button to add Goal or Reward
        FloatingActionButton fab = createFAB(MATERIAL_ADD);
        fab.addActionListener(e -> {
            Command goal = new Command("Goal");
            Command reward = new Command("Reward");
            Command cancel = new Command("Cancel");
            Command result = Dialog.show("Add New Reward or Goal? ", " ",
                    goal, reward, cancel);
            if (goal == result) {

                new AddGoal(res, allTotal).show();
            } else if (reward == result) {
                new AddReward(res, dTotal).show();
            } else {

            }

        });

        //Creates Storage Object
        MyObject g = new MyObject();

        //add to main form
        add(flowLabel);
        add(center);
        for (String file : Storage.getInstance().listEntries()) {
            CreateFileGoal(super.getComponentForm(), file, g.getType(),
                    pointsTracker);

            try (InputStream is = Storage.getInstance().createInputStream(file);) {
                String s = readToString(is, "UTF-8");
//
            } catch (IOException err) {
                e(err);
            }
        }

        add(fab);

        super.addSideMenu(res);
        tb.addMaterialCommandToRightBar("", MATERIAL_HELP_OUTLINE,
                e -> new Profile(res).show());

    }

    /**
     * Method CreateFileGoal: Reads storage to return formatted files to page
     * 
     * @param res
     * @param file
     * @param type
     * @param ptsTotal
     */
    public void CreateFileGoal(Form res, String file, String type, Label ptsTotal) 
    {
        //Components for container
        Label goal = new Label(file);
        
        //Checkbox for point totaling
        CheckBox completeCB = new CheckBox();
        
        //Labels to hold points and temporary data
        Label points = new Label("");
        Label holder = new Label(""); 
        
        //Container for holding components
        Container content = center(holder);

        //Hashmap to hold entrys
        HashMap<String, String> entryList = new HashMap<>();
        String entryKey;
        String entryValue = null;
        i = 1;

        Map<CheckBox, String> initialMap = new HashMap<>();

        //Try-catch to read storage
        try (InputStream is = Storage.getInstance().createInputStream(file);) {
            String s = readToString(is, "UTF-8");
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

                    setMap(entry);

                }

                initialMap.put(completeCB, entryValue);
                content.add(CENTER, encloseX(goal));
                content.add(EAST, encloseX(points,
                        completeCB));

            } 

        } catch (IOException err) {
            e(err);
        }

        //Map with date, and initialMap hashmap
        Map<Map<CheckBox, String>, Date> dateCheck = new HashMap<>();
        Date currentDate = new Date();
        currentDate.getTime();
        dateCheck.put(initialMap, currentDate);

        //Checkbox Listener
        completeCB.addActionListener(e -> {
            if (completeCB.isSelected()) {
                String pointValueTest = initialMap.get(completeCB);
                String dateTest = dateCheck.get(initialMap).toString();
                pointsValueInt = parseInt(pointValueTest);
                setPoints(pointsValueInt);
                ptsTotal.setText(dailyTotal());
            } else if (!completeCB.isSelected()) {
                int removePoints = 0 - pointsValueInt;
                setPoints(removePoints);
                ptsTotal.setText(dailyTotal());
            }

        });

        //Adds storage contents with goals to main form
        super.add(content);
    }

    /**
     * Method setMap: Updates runningMap variable 
     * 
     * @param runningMap
     */
    public void setMap(Map.Entry<String, String> runningMap) {
        this.runningMap = runningMap;
    }

    /**
     * Method getMap: Returns runningMap variable
     *
     * @return
     */
    public Map.Entry<String, String> getMap() {
        return runningMap;
    }

    /**
     * Method mainRewardPoints: Takes string to pass to setMainRewardPoints
     * 
     * @param s
     * @return
     */
    public String mainRewardPoints(String s) {

        setMainRewardPoints(s);
        return s;
    }

    /**
     * Method setMainRewardPoints: Takes string to update mainRewardPoints 
     * variable
     * 
     * @param s
     */
    public void setMainRewardPoints(String s) {
        this.mainRewardPoints = s;
    }

    /**
     * Method getMainRewardPoints: Returns mainRewardPoints variable
     * 
     * @return
     */
    public String getMainRewardPoints() {
        return mainRewardPoints;
    }

    /**
     * Method createLineSeparator: Returns separator for formatting
     * 
     * @return 
     */
    @Override
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    /**
     * Method Date: Takes system date, formats it and returns it as string
     * 
     * @return
     */
    public String date() {
        //Date formatter
        Date date = new Date();
        String pattern = "EEEE, MMMMM dd, yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String strDate = formatter.format(date);
        return strDate;
    }

    /**
     * Method setPoints: Updates points int 
     * 
     * @param points
     */
    public void setPoints(int points) {
        pointsInt = points;
    }

    /**
     * Method getPoints: Returns pointsInt
     * 
     * @return
     */
    public int getPoints() {
        return pointsInt;
    }

    /**
     * Method pointsTotalMethod: Takes pointsTotal int and adds it to itself
     * 
     * @param p
     * @return
     */
    public int pointsTotalMethod(int p) {
        pointsTotal = p + pointsTotal;
        cumulativeTotal(pointsTotal);
        return pointsTotal;
    }

    /**
     * Method cumulativeTotal: Takes pointsTotal and adds it to a 
     * cumulativeTotal
     * 
     * @param p
     * @return
     */
    public int cumulativeTotal(int p) {
        cPointsTotal = p + cPointsTotal;
        return cPointsTotal;
    }

    /**
     * Method dailyTotal: Returns string with dailyTotal
     * 
     * @return
     */
    public String dailyTotal() {
        setDailyTotal(getDailyTotal() + getPoints());
        String getDailyTotalString = Integer.toString(getDailyTotal());

        return getDailyTotalString;
    }

    /**
     * Method setDailyTotal: Updates dailyPointsTotal int
     * 
     * @param dailyPointsTotal
     */
    public void setDailyTotal(int dailyPointsTotal) {
        this.dailyPointsTotal = dailyPointsTotal;
    }

    /**
     * Method getDailyTotal: Returns dailyPointsTotal int
     * 
     * @return
     */
    public int getDailyTotal() {
        return dailyPointsTotal;
    }

    /**
     * Method setInputStreamString: Updates inputStreamString variable
     * 
     * @param inputStreamString
     */
    public void setInputStreamString(String inputStreamString) {
        this.inputStreamString = inputStreamString;
    }

    /**
     * Method getInputStreamString: Returns inputStreamString variable
     * 
     * @return
     */
    public String getInputStreamString() {
        return inputStreamString;
    }

} //End Subclass Dashboard

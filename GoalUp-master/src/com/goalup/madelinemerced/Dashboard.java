package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass Dashboard Description: Creates form that takes user input and
 * stores it to persistent storage. Image credit
 * https://www.pexels.com/photo/brown-wooden-dock-over-body-of-water-1227520/
 *
 *
 */
//Imports
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStream;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Image;
import com.codename1.ui.Dialog;
import com.codename1.ui.Button;
import com.codename1.io.Storage;
import com.codename1.ui.Command;
import com.codename1.ui.Toolbar;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.FloatingActionButton;
import static com.codename1.io.Log.e;
import static java.lang.Integer.parseInt;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.io.Util.readToString;
import static com.codename1.ui.layouts.BoxLayout.y;
import static com.codename1.ui.Display.getInstance;
import static com.codename1.ui.FontImage.MATERIAL_ADD;
import static com.codename1.ui.FontImage.MATERIAL_STAR;
import static com.codename1.ui.FontImage.createMaterial;
import static com.codename1.ui.layouts.BorderLayout.EAST;
import static com.codename1.ui.layouts.BoxLayout.encloseX;
import static com.codename1.ui.layouts.BorderLayout.center;
import static com.codename1.ui.FontImage.MATERIAL_STAR_BORDER;
import static com.codename1.ui.FontImage.MATERIAL_HELP_OUTLINE;
import static com.codename1.components.FloatingActionButton.createFAB;
import static com.codename1.ui.layouts.FlowLayout.encloseCenterBottom;
import static com.codename1.ui.plaf.Style.BACKGROUND_IMAGE_SCALED_FILL;

//Begin Subclass Dashboard
public final class Dashboard extends BaseForm {

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

    //Dashboard Constructor
    Dashboard() {
    
    }
    
    /**
     * Method Dashboard: Inherits BaseForm to display snapshot of user status
     *
     * @param res
     */
    public Dashboard(Resources res) {

        //Inherits from BaseForm
        super("", y());

        //Inherits project toolbar properties
        Toolbar tb = super.getToolbar();

        //Adds toolbar to dashboard
        setToolbar(tb);

        //Saves logo image to label
        Image logo = res.getImage("LogoHeader.png");
        Label l = new Label(logo);

        //Component for layered reward points display
        Image img = res.getImage("selected-backgroundFilled.png");
        if (img.getHeight() > getInstance().getDisplayHeight() / 4) {
            img = img.scaledHeight(getInstance().getDisplayHeight() / 4);
        }

        //Scales image for display
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("TopPad");
        sl.setBackgroundType(BACKGROUND_IMAGE_SCALED_FILL);
        sl.setUIID("BottomPad");

        //Labels and formatting for dashboard trackers
        Container landingPageButtons = BoxLayout.encloseY();
        Container flowLabel = new Container(new FlowLayout(CENTER));

        //Holds points total returned from calculator methods
        Label ptsTotal = new Label();

        //Date label set with date() method results
        Label date = new Label();
        date.setText(date());
        date.setUIID("Qte");

        //Parses getDailyTotal method int into string for display
        String getDailyTotalString = Integer.toString(getDailyTotal());

        //Labels for spacing UIIDs
        Label topPad = new Label("");
        Label topPad1 = new Label("");
        Label leftPad = new Label("");
        Label leftPad1 = new Label("");
        Label rightPad = new Label("");
        Label rightPad1 = new Label("");

        //UIIDs for formatting assigned to above labels
        topPad.setUIID("TopPad1");
        topPad1.setUIID("TopPad1");
        leftPad.setUIID("LeftPadding");
        leftPad1.setUIID("LeftPadding1");
        rightPad.setUIID("RightPadding");
        rightPad1.setUIID("RightPadding1");
        
        //PointsTracker label set to dailyTotalString result
        Label pointsTracker = new Label();
        pointsTracker.setText(getDailyTotalString);
//        setMainRewardPoints("100");

        //Labels for reward bar headings
        Label descripTotal = new Label("Points Total");
        Label descripReward = new Label("Reward Points Total");
        
        //UIID settings for reward bar headers
        descripTotal.setUIID("pointsHeaders");
        descripReward.setUIID("pointsHeaders");
   
        //Label to display reward points total
        Label rewardPoints = new Label("100");

        //Container for logo and date formatting centered on page
        Container logoDate = BoxLayout.encloseY(l, date);

        //Adds logoDate container to main form
        flowLabel.add(logoDate);

        //Adds landingPageButtons container to main form
        flowLabel.add(landingPageButtons);

        //Adds reward picture, points and reward points to main form
        flowLabel.add(LayeredLayout.encloseIn(sl,
                BoxLayout.encloseY(topPad,
                        encloseX(leftPad1, descripTotal),
                        encloseX(leftPad, pointsTracker)),
                        encloseCenterBottom(
                        new Label(res.getImage("shoesSmall.png"), "PictureWhiteBackgrond")),
                BoxLayout.encloseY(topPad1,
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
                new AddGoal(res, ptsTotal).show();
            } else if (reward == result) {
                new AddReward(res, ptsTotal).show();
            } else {

            }

        });

        //Creates Storage Object
        MyObject g = new MyObject();

        //Adds flowLabel container to main form
        add(flowLabel);
        
        //Adds center container to main form
        add(center);
        
        /*For loop to iterate over storage contents and stores each result in 
        file string which is then passed to the createFileEntry method to process
        and display.*/ 
        for (String file : Storage.getInstance().listEntries()) {
           
            createFileEntry(super.getComponentForm(), file, g.getType(),
                    pointsTracker);

//            try (InputStream is = Storage.getInstance().createInputStream(file);) {
//                String s = readToString(is, "UTF-8");
////
//            } catch (IOException err) {
//                e(err);
//            }
        }

        //Adds floating action button to main form
        add(fab);

        //Adds sidemenu to main form
        super.addSideMenu(res);
        
        //Adds icon to toolbar for profile access
        tb.addMaterialCommandToRightBar("", MATERIAL_HELP_OUTLINE,
                e -> new Profile(res).show());
    }

//    /**
//     * 
//     * @param completeCB
//     */
//    public void setCheckbox(CheckBox completeCB) {
//        cb = completeCB;
//    }
//
//    /**
//     *
//     * @return
//     */
//    public CheckBox getCheckbox() {
//        return cb;
//    }

    /**
     *
     * @param res
     * @param file
     * @param t
     * @param dailyTotal
     */
    public void createFileEntry(Form res, String file, String t, Label dailyTotal) {
        
        //Components for container
        Label goal = new Label(file);
        CheckBox completeCB = new CheckBox();
        Label points = new Label("");
        Label holder = new Label("");
        //Container for holding components
        Container content = center(holder);

        HashMap<String, String> entryList = new HashMap<>();
        String entryKey;
        String entryValue = null;
        i += 1;

        Map<CheckBox, String> testing = new HashMap<>();

        //Reads in the storage
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

                testing.put(completeCB, entryValue);
                content.add(CENTER, encloseX(goal));
                content.add(EAST, encloseX(points,
                        completeCB));

            } 

        } catch (IOException err) {
            e(err);
        }

        //Map with date, and testing hashmap
        Map<Map<CheckBox, String>, Date> dateCheck
                = new HashMap<>();
        Date currentDate = new Date();
        currentDate.getTime();
        dateCheck.put(testing, currentDate);

        //Checkbox Listener
        completeCB.addActionListener(e -> {
            if (completeCB.isSelected()) {
                String pointValueTest = testing.get(completeCB);
                String dateTest = dateCheck.get(testing).toString();
                pointsValueInt = parseInt(pointValueTest);
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


    /**
     *
     * @param runningMap
     */
    public void setMap(Map.Entry<String, String> runningMap) {
        this.runningMap = runningMap;
    }

    /**
     *
     * @return
     */
    public Map.Entry<String, String> getMap() {
        return runningMap;
    }

    /**
     *
     * @param res
     * @param file
     * @param type
     */
    public void createFileEntryReward(Form res, String file, String type) {
        Label reward = new Label(file);
        CheckBox c = new CheckBox();
        c.setSelected(true);
        c.setToggle(true);
        c.setTextPosition(LEFT);
        c.setIcon(createMaterial(MATERIAL_STAR_BORDER,
                "UncheckedIcon", 4));
        c.setPressedIcon(createMaterial(MATERIAL_STAR,
                "CheckedIcon", 4));
        HashMap<String, String> listRewards = new HashMap<>();

        MyObject obj = new MyObject();
        String entryKey;
        String entryValue = null;
        Label points = new Label("");
        Label holder = new Label("");

        Container content = center(holder);
        try (InputStream is = Storage.getInstance().createInputStream(file);) {
            String s = readToString(is, "UTF-8");
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

                    setMap(entry);

                }
                content.add(CENTER, encloseX(reward));
                content.add(EAST, encloseX(points, c));
            }

        } catch (IOException err) {
            e(err);
        }

        res.add(content);
    }

    /**
     *
     * @param s
     * @return
     */
    public String mainRewardPoints(String s) {

        setMainRewardPoints(s);
        return s;
    }

    /**
     *
     * @param s
     */
    public void setMainRewardPoints(String s) {
        this.mainRewardPoints = s;
    }

    /**
     *
     * @return
     */
    public String getMainRewardPoints() {
        return mainRewardPoints;
    }

    @Override
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    /**
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
     *
     * @param points
     */
    public void setPoints(int points) {
        pointsInt = points;
    }

    /**
     *
     * @return
     */
    public int getPoints() {
        return pointsInt;
    }

    /**
     *
     * @param p
     * @return
     */
    public int method(int p) {
        pointsTotal = p + pointsTotal;
        met(pointsTotal);
        return pointsTotal;
    }

    /**
     *
     * @param p
     * @return
     */
    public int met(int p) {
        cPointsTotal = p + cPointsTotal;
        return cPointsTotal;
    }

    /**
     *
     * @param dailyPointsTotal
     */
    public void setDailyTotal(int dailyPointsTotal) {
        this.dailyPointsTotal = dailyPointsTotal;
    }

    /**
     *
     * @return
     */
    public int getDailyTotal() {
        return dailyPointsTotal;
    }

    /**
     *
     * @return
     */
    public String dailyTotal() {
        setDailyTotal(getDailyTotal() + getPoints());
        String getDailyTotalString = Integer.toString(getDailyTotal());
//        dT.setText(getDailyTotalString);

        return getDailyTotalString;
    }

    /**
     *
     * @return
     */
    public String getInputStreamString() {
        return inputStreamString;
    }

    /**
     *
     * @param inputStreamString
     */
    public void setInputStreamString(String inputStreamString) {
        this.inputStreamString = inputStreamString;
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth()
                / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }

} //End Subclass Dashboard

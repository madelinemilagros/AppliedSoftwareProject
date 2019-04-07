package com.goalup.madelinemerced;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
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
//    String pointsInt;
    int pointsTotal;
    int cPointsTotal;
    int pointsInt;

    public Dashboard(Resources res) {
        super("Dashboard", BoxLayout.y());
        Toolbar tb = super.getToolbar();
        setToolbar(tb);
//        getTitleArea().setUIID("Container");
//        setTitle("Dashboard");
//        getContentPane().setScrollVisible(false);
        //        //Main Form 
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
        //Adds Dashboard Labels to Flow Container (holds image as well)                

        //Adds Logo
        flowLabel.addComponent(l);

        //SideMenu
        Image icon = res.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icon));
        Image icon2 = res.getImage("icon.png");
        topBar.add(BorderLayout.WEST, new Label(icon2));
        topBar.add(BorderLayout.SOUTH, new Label("Goal Up", "Cause You Got This!"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
        Label allTotal = new Label();
        Label dailyTotal = new Label();
        Button goals = new Button("Goals");
        tb.addComponentToSideMenu(goals);
        goals.addActionListener(e -> {
            new Goal(res, allTotal, dailyTotal).show();
        });

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
            Command result = Dialog.show(" ", tf, goal, reward);
            if (goal == result) {
                Goal(allTotal, dailyTotal);
            } else {

            }

        });
        //Dashboard Point Total Holders (cumulative and daily) 

        Container flow = new Container(new GridLayout(1, 2));
        flow.add(allTotal);
        flow.add(dailyTotal);
        flowLabel.addComponent(landingPageButtons);

        //add to main form
        add(flowLabel);
        add(flow);
//                hi.add(landingPageButtons);
        add(center);
        add(fab);
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });
//        
//        Tabs swipe = new Tabs();
//
//        Label spacer1 = new Label();
//        Label spacer2 = new Label();
////        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
////        addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
//                
//        swipe.setUIID("Container");
//        swipe.getContentPane().setUIID("Container");
//        swipe.hideTabs();
//        
//        ButtonGroup bg = new ButtonGroup();
//        int size = Display.getInstance().convertToPixels(1);
//        Image unselectedWalkthru = Image.createImage(size, size, 0);
//        Graphics g = unselectedWalkthru.getGraphics();
//        g.setColor(0xffffff);
//        g.setAlpha(100);
//        g.setAntiAliased(true);
//        g.fillArc(0, 0, size, size, 0, 360);
//        Image selectedWalkthru = Image.createImage(size, size, 0);
//        g = selectedWalkthru.getGraphics();
//        g.setColor(0xffffff);
//        g.setAntiAliased(true);
//        g.fillArc(0, 0, size, size, 0, 360);
//        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
//        FlowLayout flow = new FlowLayout(CENTER);
//        flow.setValign(BOTTOM);
//        Container radioContainer = new Container(flow);
//        for(int iter = 0 ; iter < rbs.length ; iter++) {
//            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
//            rbs[iter].setPressedIcon(selectedWalkthru);
//            rbs[iter].setUIID("Label");
//            radioContainer.add(rbs[iter]);
//        }
//                
////        rbs[0].setSelected(true);
//        swipe.addSelectionListener((i, ii) -> {
//            if(!rbs[ii].isSelected()) {
//                rbs[ii].setSelected(true);
//            }
//        });
//        
//        Component.setSameSize(radioContainer, spacer1, spacer2);
//        add(LayeredLayout.encloseIn(swipe, radioContainer));
//        
//        ButtonGroup barGroup = new ButtonGroup();
//        RadioButton all = RadioButton.createToggle("All", barGroup);
//        all.setUIID("SelectBar");
//        RadioButton featured = RadioButton.createToggle("Featured", barGroup);
//        featured.setUIID("SelectBar");
//        RadioButton popular = RadioButton.createToggle("Popular", barGroup);
//        popular.setUIID("SelectBar");
//        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
//        myFavorite.setUIID("SelectBar");
//        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
//        
//        add(LayeredLayout.encloseIn(
//                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
//                FlowLayout.encloseBottom(arrow)
//        ));
//        
//        all.setSelected(true);
//        arrow.setVisible(false);
//        addShowListener(e -> {
//            arrow.setVisible(true);
//            updateArrowPosition(all, arrow);
//        });
//        bindButtonSelection(all, arrow);
//        bindButtonSelection(featured, arrow);
//        bindButtonSelection(popular, arrow);
//        bindButtonSelection(myFavorite, arrow);
//        
//        // special case for rotation
//        addOrientationListener(e -> {
//            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
//        });

//        addButton(res.getImage("news-item-1.jpg"), "Morbi per tincidunt tellus sit of amet eros laoreet.", false, 26, 32);
//        addButton(res.getImage("news-item-2.jpg"), "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21);
//        addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
//        addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }

    public Form Goal(Label allTotal, Label dailyTotal) {
        Form newForm = new Form();

        //Storage Management
        ArrayList<Storage> goals = Storage.getGoals();
        Storage g = new Storage();

        //TextFields
        TextField goalTF = new TextField("", "Goal", 16, TextField.ANY);
        TextArea pointsTF = new TextArea(2, 2);

//        goalTF.setWidth(LEFT);
        pointsTF.setHint("Points");
        Button enter = new Button("Enter");

        enter.addActionListener(e -> {
            //Action listener for enter button
            try {
                int pointsInt = Integer.parseInt(pointsTF.getText());
                setPoints(pointsInt);
                int dm = method(pointsInt);

                String dailyTString = Integer.toString(dm);
//                allTotal.setText(dailyTString);
                g.setGoal(goalTF.getText());
                g.setPoints(pointsTF.getText());

                Storage.setGoals(goals);
                goals.add(g);
                add(addGoal(g, dailyTotal, allTotal));
                show();
                newForm.add(addGoal(g, dailyTotal, allTotal));

            } catch (NumberFormatException nfe) {

            }

        });

        Container goalEnter = BoxLayout.encloseXNoGrow(goalTF, pointsTF);
        Container count = new Container();
        count.add(
                GridLayout.encloseIn(
                        (goalEnter)
                ));

        newForm.add(count);
        newForm.add(enter);

        newForm.show();
        return newForm;
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

    private Container addGoal(Storage s, Label dailyTotal, Label allTotal) {
        Label goal = new Label(s.getGoal());
        Button points = new Button(s.getPoints());
        CheckBox completeCB = new CheckBox();
        Container row = BoxLayout.encloseXNoGrow(goal, points, completeCB);
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
                dailyTotal.setText(Integer.toString(total));
                allTotal.setText(Integer.toString(total));
                System.out.print(date());

            } else if (!completeCB.isSelected()) {
//                pointsTotal = 0;
                String dT = allTotal.getText();
                int total = Integer.parseInt(dT);
//                method(total);
                allTotal.setText(Integer.toString(total));
            }
        });
        return cnt;
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(likes, comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if (!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0xff2d55);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes, comments)
                ));
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }

} //End Subclass Dashboard

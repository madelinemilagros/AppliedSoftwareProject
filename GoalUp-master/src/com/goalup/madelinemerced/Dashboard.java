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

import com.codename1.components.FloatingActionButton;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;

public class Dashboard extends BaseForm {

    public Dashboard(Resources res) {
        super(BoxLayout.y());
        
        Util.register("MyObject", MyObject.class);
        
        //Image Logo
        Image img = res.getImage("Logo@0,1x.png");
        Label logo = new Label(img);

        //Logo Position
        Container flow = new Container();
        flow.setLayout(new BorderLayout());
        flow.addComponent(BorderLayout.CENTER, logo);
        
        //Goals Container
            Container goals = new Container();
            Label TA = new Label();
            goals.setLayout(new BorderLayout());
            Label g1 = new Label("Goal One");
            goals.addComponent(BorderLayout.CENTER, g1);
            goals.addComponent(BorderLayout.CENTER, TA);

        //Header Position
        add(LayeredLayout.encloseIn(
                flow,
                BorderLayout.south(
                        GridLayout.encloseIn(
                                FlowLayout.encloseCenter(
                                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
                        )
                )));
        
        //Labels for Trackers
        Label dailyPointsTotal = new Label("Daily Points Total");
        Label pointsTracker = new Label("40/100 points");

        //Trackers Container
        Container dashboardTrackers = new Container(new GridLayout(1, 2));
        dashboardTrackers.add(dailyPointsTotal);
        dashboardTrackers.add(pointsTracker);
        FlowLayout trackerPositioning = new FlowLayout(Component.TOP);
        trackerPositioning.layoutContainer(dashboardTrackers);
        add(dashboardTrackers);
        
        MyObject o = (MyObject) Storage.getInstance().readObject("SavedData");
        TA.setText(o.getX() + " " + o.getY());
        add(goals);
        
        //Floating action button to add goals 
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.addActionListener(e -> new Goal(res).show());
        add(fab);

    }

}

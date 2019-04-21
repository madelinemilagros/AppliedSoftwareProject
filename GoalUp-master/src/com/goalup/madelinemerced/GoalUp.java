package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @MainClass Description: Application main class that displays signUp page
 */

//Imports
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;
import static com.codename1.ui.CN.*;
import static com.codename1.io.Log.e;
import static com.codename1.ui.Dialog.show;
import static com.codename1.io.Util.register;
import static com.codename1.ui.Toolbar.setGlobalToolbar;
import static com.codename1.ui.plaf.UIManager.initFirstTheme;

//GoalUp Main Class
public class GoalUp {

    //Variables
    private Form current;
    private Resources theme;

    /**
     * Method init: Responsible for the initialization of variables and values
     * 
     * @param context
     */
    public void init(Object context) {
       
        //Updates two network threads instead of one
        updateNetworkThreadCount(2);

        //Determines the appearance
        theme = initFirstTheme("/theme");

        //Enable Toolbar on all Forms by default
        setGlobalToolbar(true);

        //Registers storage object with Util class 
        register("Storage", MyObject.class);
        
        //In case of network error 
        addNetworkErrorListener(err -> {
            //prevents the event from propagating
            err.consume();
            if (err.getError() != null) {
                e(err.getError());
            }
            //Displays error dialog
            show("Connection Error", "There was a networking error in the "
                    + "connection to " + err.getConnectionRequest().getUrl(), 
                    "OK", null);
        });
    }

    /**
     * Method start: If the app has been minimized will skip init and come 
     * straight to start, or else it will run after init
     */
    public void start() {
        
        //Checks if app was minimized
        if (current != null) {
            //if minimized shows the most current form
            current.show();
            return;
        }

        //Passes theme to SignUp class and displays form
        new SignUp(theme).show();
    }

    /**
     * Method stop: If the app has been minimized or interrupted this will run 
     * and save the current form
     */
    public void stop() {
        current = getCurrentForm();
        if (current instanceof Dialog) {
            ((Dialog) current).dispose();
            current = getCurrentForm();
        }
    }

    /**
     * Method destroy: Standard with codenameone but not written to as it is 
     * only going to be used in a very special case
     */
    public void destroy() {

    }

}

package com.goalup.madelinemerced;

/**
 * @Course: SDEV-435-81 ~ Applied Software Practice
 * @Author Name: Madeline Merced
 * @Assignment Name: Final Project: Goal Up
 * @Subclass VerifyValidate Description: Returns alertBox when called
 * 
 */

//Imports
import com.codename1.ui.Command;
import static com.codename1.ui.Dialog.show;

//Begin Subclass VerifyValidate 
public class VerifyValidate  {

    /**
     * VerifyValidate Constructor
     */
    VerifyValidate(){
        
    };
   
     /**
     * Method alertBox: Returns alertBox with information for user.
     *
     * @param s
     */
    public void alertBox(String s) {
        String title = "Warning";
        String alertDescription = s;

        Command ok = new Command("OK");
        show(title, alertDescription, ok);
    }
    
} //End Subclass VerifyValidate
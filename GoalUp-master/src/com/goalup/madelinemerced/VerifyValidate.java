package com.goalup.madelinemerced;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;

/** 
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Apr 19, 2019
 * @Subclass VerifyValidate Description: 
 */
//Imports

//Begin Subclass VerifyValidate
public class VerifyValidate  {

    VerifyValidate(){
        
    };
    
    
      /**
     * Method alertBox: Returns alertBox with information for user.
     *
     * @param s2
     */
    public void alertBox(String s2) {
        String title = "Warning";
        String alertDescription = s2;

        Command ok = new Command("OK");
        Dialog.show(title, alertDescription, ok);
    }

    
    
} //End Subclass VerifyValidate
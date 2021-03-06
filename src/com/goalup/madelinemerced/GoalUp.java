package com.goalup.madelinemerced;

import com.codename1.io.Util;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename
 * One</a> for the purpose of building native mobile applications using Java.
 */
public class GoalUp {

    private Form current;
    private Resources theme;
    
    public void init(Object context) {
        try {
            theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }

        new LogIn(theme).show();
    }

    public void output(){
        try(OutputStream os = createStorageOutputStream("storageEntryName")){
            
        }catch(IOException err){
            
        }
        try(InputStream is = createStorageInputStream("storageEntryName")){
            
        }catch(IOException err){
            
        }
        
    }
    
    public void stop() {
        current = getCurrentForm();
        if (current instanceof Dialog) {
            ((Dialog) current).dispose();
            current = getCurrentForm();
        }
    }

    public void destroy() {
    }

}

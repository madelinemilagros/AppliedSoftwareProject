package com.goalup.madelinemerced;

/** 
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Doug
 * @Assignment Name: com.mycompany.myapp
 * @Date: Mar 2, 2019
 * @Subclass NewClass Description: 
 */

//Imports
import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

//Begin Subclass NewClass
public class MyObject implements Externalizable{
    private String x;
    private String y;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
    
    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
    
    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public void externalize(DataOutputStream out) throws IOException {
        Util.writeUTF(x, out);
        } 

    @Override
    public void internalize(int version, DataInputStream in) throws IOException {
        x = Util.readUTF(in);
    }

    @Override
    public String getObjectId() {
        return "MyObject";
    }
    
} //End Subclass NewClass
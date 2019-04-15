package com.goalup.madelinemerced;

import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import com.codename1.properties.IntProperty;
import com.codename1.properties.Property;
import com.codename1.properties.PropertyBusinessObject;
import com.codename1.properties.PropertyIndex;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/** 
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.goalup.madelinemerced
 * @Date: Apr 15, 2019
 * @Subclass TestObject Description: 
 */
//Imports

//Begin Subclass TestObject
public class TestObject  implements PropertyBusinessObject {
    public final IntProperty<TestObject> id  = new IntProperty<>("id");
    public final Property<String, TestObject> name = new Property<>("name");
    public final Property<String, TestObject> email = new Property<>("email");
    public final Property<String, TestObject> phone = new Property<>("phone");
    public final Property<Date, TestObject> dateOfBirth = new Property<>("dateOfBirth", Date.class);
    public final Property<String, TestObject> gender  = new Property<>("gender");
    public final IntProperty<TestObject> rank  = new IntProperty<>("rank");
    public final PropertyIndex idx = new PropertyIndex(this, "TestObject", id, name, email, phone, dateOfBirth, gender, rank);

    @Override
    public PropertyIndex getPropertyIndex() {
        return idx;
    }

    public TestObject() {
        name.setLabel("Name");
        email.setLabel("E-Mail");
        phone.setLabel("Phone");
        dateOfBirth.setLabel("Date Of Birth");
        gender.setLabel("Gender");
        rank.setLabel("Rank");
    }
} //End Subclass TestObject
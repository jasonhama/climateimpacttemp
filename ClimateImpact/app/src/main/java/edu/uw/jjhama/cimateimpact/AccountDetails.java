package edu.uw.jjhama.cimateimpact;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by iguest on 4/17/16.
 */
public class AccountDetails extends Application {

    private String fName;
    private String lName;
    private String email;
    private String password;
    private String country;
    private String zip;
    private String state;
    private int water;
    private int carbon;


    // Constructor.
    public AccountDetails() {
        this.fName = "";
        this.lName = "";
        this.email = "";
        this.password = "";
        this.water = 0;
        this.carbon = 0;
        this.zip = "00000";
        this.country = "USA";
        this.state = "WA";
    }

    public AccountDetails(String fName, String lName, String email, String password){
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.water = 0;
        this.carbon = 0;
    }

    public String toString(){
        return "Object contains... \n First Name : " + fName + "\nLast Name : " + lName;
    }

    public AccountDetails getAccountDetails(){
        return this;

    }

    //getters and setters
    public String getCountry(){ return state; }

    public void setCountry(String country){ this.country = country; }

    public String getZip(){ return zip; }

    public void setZip(String zip){this.zip = zip; }

    public String getState() { return state; }

    public void setState(String state){ this.state = state; }

    public String getfName(){
        return fName;
    }

    public void setfName(String name){
        this.fName = name;
    }

    public String getlName(){
        return lName;
    }

    public void setlName(String name){
        this.lName = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getWater(){
        return this.water;
    }

    public void setWater(int water){
        this.water = water;
    }

    public void addWater(int water){
        this.water = this.water + water;
    }

    public int getCarbon(){
        return this.carbon;
    }

    public void setCarbon(int carbon){
        this.carbon = carbon;
    }

    public void addCarbon(int carbon){
        this.carbon = this.carbon + carbon;
    }

    private int mData;
}

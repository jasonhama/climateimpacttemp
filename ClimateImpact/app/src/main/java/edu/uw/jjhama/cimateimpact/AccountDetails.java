package edu.uw.jjhama.climateimpact;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by iguest on 4/17/16.
 */
public class AccountDetails implements Parcelable{

    private String fName;
    private String lName;
    private String email;
    private String password;
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

    //getters and setters
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

    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }



    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<AccountDetails> CREATOR = new Parcelable.Creator<AccountDetails>() {
        public AccountDetails createFromParcel(Parcel in) {
            return new AccountDetails(in);
        }

        public AccountDetails[] newArray(int size) {
            return new AccountDetails[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private AccountDetails(Parcel in) {
        mData = in.readInt();
    }

}

package edu.uw.jjhama.cimateimpact;

/**
 * Created by iguest on 5/24/16.
 */
public class ActivityDetails {
    private String action; // email of the user
    private String frequency;
    private String startTime;
    private String endTime;
    private String uuid;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    public ActivityDetails() {
    }

    public ActivityDetails(String uuid, String action, String frequency, String startTime, String endTime) {
        this.action = action;
        this.frequency = frequency;
        this.startTime = startTime;
        this.endTime = endTime;
        this.uuid = uuid;
    }

    public String getAction() {
        return action;
    }

    public String getFrequency() {
        return frequency;
    }

    //@JsonIgnore
    public String getStartTime() {
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public String getUUID(){
        return uuid;
    }

    //compare to to be able to sort the values in the table
//    @Override
//    public int compareTo(Object another) {
//        ActivityDetails r = (ActivityDetails) another;
//        return (int)r.getKills() - (int)this.kills;
//    }

    //tosting to have the array adapter be called
    public String toString() {
        return this.uuid + " : " + this.action + " : " + this.frequency + " : " + this.startTime + " : " + this.endTime;
    }
}

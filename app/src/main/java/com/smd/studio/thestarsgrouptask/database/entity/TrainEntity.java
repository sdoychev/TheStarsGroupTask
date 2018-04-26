package com.smd.studio.thestarsgrouptask.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "trains")
public class TrainEntity {

    @PrimaryKey
    @NonNull
    @SerializedName("Traincode")
    @Expose
    private String trainCode;

    @SerializedName("Stationfullname")
    @Expose
    private String stationFullName;

    @SerializedName("Stationcode")
    @Expose
    private String stationCode;

    @SerializedName("Servertime")
    @Expose
    private Date serverTime;

    @SerializedName("Origin")
    @Expose
    private String origin;

    @SerializedName("Destination")
    @Expose
    private String destination;

    @SerializedName("Origintime")
    @Expose
    private String originTime;

    @SerializedName("Destinationtime")
    @Expose
    private String destinationTime;

    @SerializedName("Status")
    @Expose
    private String status;

    @SerializedName("Lastlocation")
    @Expose
    private String lastLocation;

    @SerializedName("Duein")
    @Expose
    private int dueIn;

    @SerializedName("Late")
    @Expose
    private int late;

    @SerializedName("Exparrival")
    @Expose
    private String expectedArrival;

    @SerializedName("Expdepart")
    @Expose
    private String expectedDeparture;

    @SerializedName("Scharrival")
    @Expose
    private String scheduledArrival;

    @SerializedName("Schdepart")
    @Expose
    private String scheduledDeparture;

    @SerializedName("Direction")
    @Expose
    private String direction;

    @SerializedName("Traintype")
    @Expose
    private String trainType;

    @SerializedName("Locationtype")
    @Expose
    private String locationType;

    private Date lastRefresh;

    //Constructor
    public TrainEntity(@NonNull String trainCode, String stationFullName, String stationCode, Date serverTime, String origin, String destination, String originTime, String destinationTime, String status, String lastLocation, int dueIn, int late, String expectedArrival, String expectedDeparture, String scheduledArrival, String scheduledDeparture, String direction, String trainType, String locationType, Date lastRefresh) {
        this.trainCode = trainCode;
        this.stationFullName = stationFullName;
        this.stationCode = stationCode;
        this.serverTime = serverTime;
        this.origin = origin;
        this.destination = destination;
        this.originTime = originTime;
        this.destinationTime = destinationTime;
        this.status = status;
        this.lastLocation = lastLocation;
        this.dueIn = dueIn;
        this.late = late;
        this.expectedArrival = expectedArrival;
        this.expectedDeparture = expectedDeparture;
        this.scheduledArrival = scheduledArrival;
        this.scheduledDeparture = scheduledDeparture;
        this.direction = direction;
        this.trainType = trainType;
        this.locationType = locationType;
        this.lastRefresh = lastRefresh;
    }

    //Getters
    @NonNull
    public String getTrainCode() {
        return trainCode;
    }

    public String getStationFullName() {
        return stationFullName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public Date getServerTime() {
        return serverTime;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getOriginTime() {
        return originTime;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public String getStatus() {
        return status;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public int getDueIn() {
        return dueIn;
    }

    public int getLate() {
        return late;
    }

    public String getExpectedArrival() {
        return expectedArrival;
    }

    public String getExpectedDeparture() {
        return expectedDeparture;
    }

    public String getScheduledArrival() {
        return scheduledArrival;
    }

    public String getScheduledDeparture() {
        return scheduledDeparture;
    }

    public String getDirection() {
        return direction;
    }

    public String getTrainType() {
        return trainType;
    }

    public String getLocationType() {
        return locationType;
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }

    //Setters
    public void setTrainCode(@NonNull String trainCode) {
        this.trainCode = trainCode;
    }

    public void setStationFullName(String stationFullName) {
        this.stationFullName = stationFullName;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setOriginTime(String originTime) {
        this.originTime = originTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }

    public void setDueIn(int dueIn) {
        this.dueIn = dueIn;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public void setExpectedArrival(String expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    public void setExpectedDeparture(String expectedDeparture) {
        this.expectedDeparture = expectedDeparture;
    }

    public void setScheduledArrival(String scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public void setScheduledDeparture(String scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }
}

package com.smd.studio.thestarsgrouptask.database.entity;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Commit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "trains")
@Root(name = "objStationData")
public class TrainEntity {

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @PrimaryKey
    @NonNull
    @Expose
    @Element(name = "Traincode")
    private String trainCode;

    @Expose
    @Element(name = "Stationfullname")
    private String stationFullName;

    @Expose
    @Element(name = "Stationcode")
    private String stationCode;

    @Expose
    @Element(name = "Servertime")
    private String serverTime;

    @Expose
    @Element(name = "Querytime")
    private String queryTime;

    @Expose
    @Element(name = "Traindate")
    private String trainDate;

    @Expose
    @Element(name = "Origin")
    private String origin;

    @Expose
    @Element(name = "Destination")
    private String destination;

    @Expose
    @Element(name = "Origintime")
    private String originTime;

    @Expose
    @Element(name = "Destinationtime")
    private String destinationTime;

    @Expose
    @Element(name = "Status")
    private String status;

    @Expose
    @Element(name = "Lastlocation", required = false)
    private String lastLocation;

    @Expose
    @Element(name = "Duein")
    private int dueIn;

    @Expose
    @Element(name = "Late")
    private int late;

    @Expose
    @Element(name = "Exparrival")
    private String expectedArrival;

    @Expose
    @Element(name = "Expdepart")
    private String expectedDeparture;

    @Expose
    @Element(name = "Scharrival")
    private String scheduledArrival;

    @Expose
    @Element(name = "Schdepart")
    private String scheduledDeparture;

    @Expose
    @Element(name = "Direction")
    private String direction;

    @Expose
    @Element(name = "Traintype")
    private String trainType;

    @Expose
    @Element(name = "Locationtype")
    private String locationType;

    private Date lastRefresh;

    @Commit
    private void parseDate() {
        if(serverTime != null) {
            try {
                lastRefresh = simpleDateFormat.parse(String.valueOf(serverTime));
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                lastRefresh = null;
            }
        }
    }

    //Constructors
    public TrainEntity() {
    }

    public TrainEntity(@NonNull String trainCode, String stationFullName, String stationCode, String serverTime, String queryTime, String trainDate, String origin, String destination, String originTime, String destinationTime, String status, String lastLocation, int dueIn, int late, String expectedArrival, String expectedDeparture, String scheduledArrival, String scheduledDeparture, String direction, String trainType, String locationType, Date lastRefresh) {
        this.trainCode = trainCode;
        this.stationFullName = stationFullName;
        this.stationCode = stationCode;
        this.serverTime = serverTime;
        this.queryTime = queryTime;
        this.trainDate = trainDate;
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

    public String getServerTime() {
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

    public String getQueryTime() {
        return queryTime;
    }

    public String getTrainDate() {
        return trainDate;
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

    public void setServerTime(String serverTime) {
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

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }
}

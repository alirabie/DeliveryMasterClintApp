package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mido PC on 2/19/2017.
 */
public class Locaton {

    @SerializedName("LocationID")
    @Expose
    private Integer locationID;
    @SerializedName("LocationName")
    @Expose
    private Object locationName;
    @SerializedName("BranchName")
    @Expose
    private String branchName;
    @SerializedName("BranchCode")
    @Expose
    private String branchCode;
    @SerializedName("StreetAddress")
    @Expose
    private String streetAddress;
    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    @SerializedName("Longtitude")
    @Expose
    private Double longtitude;

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public Object getLocationName() {
        return locationName;
    }

    public void setLocationName(Object locationName) {
        this.locationName = locationName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    //*
}

package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mido PC on 2/23/2017.
 */
public class MyLocation {
    @SerializedName("LocationID")
    @Expose
    private Integer locationID;
    @SerializedName("LocationObjectType")
    @Expose
    private Integer locationObjectType;
    @SerializedName("ObjectID")
    @Expose
    private String objectID;
    @SerializedName("Name")
    @Expose
    private Object name;
    @SerializedName("CountryID")
    @Expose
    private Integer countryID;
    @SerializedName("City")
    @Expose
    private Object city;
    @SerializedName("Area")
    @Expose
    private Object area;
    @SerializedName("StreetAddress")
    @Expose
    private String streetAddress;
    @SerializedName("BuildingNo")
    @Expose
    private Object buildingNo;
    @SerializedName("Longtitude")
    @Expose
    private String longtitude;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Comment")
    @Expose
    private String comment;
    @SerializedName("ObjectState")
    @Expose
    private Integer objectState;

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public Integer getLocationObjectType() {
        return locationObjectType;
    }

    public void setLocationObjectType(Integer locationObjectType) {
        this.locationObjectType = locationObjectType;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getArea() {
        return area;
    }

    public void setArea(Object area) {
        this.area = area;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public Object getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(Object buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getObjectState() {
        return objectState;
    }

    public void setObjectState(Integer objectState) {
        this.objectState = objectState;
    }

}

package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mido PC on 2/23/2017.
 */
public class NewLocaton {

    @SerializedName("Longtitude")
    @Expose
    private Double longtitude;
    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    @SerializedName("StreetAddress")
    @Expose
    private String streetAddress;
    @SerializedName("Comment")
    @Expose
    private String comment;


    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



}

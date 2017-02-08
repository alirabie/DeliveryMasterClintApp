package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mido PC on 2/8/2017.
 */
public class CustomizationM {
    @SerializedName("CustomizationType")
    @Expose
    private Object customizationType;
    @SerializedName("ID")
    @Expose
    private Integer CiD;
    @SerializedName("CustomizationTypeID")
    @Expose
    private Integer customizationTypeID;
    @SerializedName("Name")
    @Expose
    private String Cname;
    @SerializedName("Price")
    @Expose
    private Double Cprice;
    @SerializedName("ObjectState")
    @Expose
    private Integer objectState;

    public Integer getObjectState() {
        return objectState;
    }

    public void setObjectState(Integer objectState) {
        this.objectState = objectState;
    }

    public Integer getCustomizationTypeID() {
        return customizationTypeID;
    }

    public void setCustomizationTypeID(Integer customizationTypeID) {
        this.customizationTypeID = customizationTypeID;
    }

    public Object getCustomizationType() {
        return customizationType;
    }

    public void setCustomizationType(Object customizationType) {
        this.customizationType = customizationType;
    }

    public Integer getCiD() {
        return CiD;
    }

    public void setCiD(Integer ciD) {
        CiD = ciD;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public Double getCprice() {
        return Cprice;
    }

    public void setCprice(Double cprice) {
        Cprice = cprice;
    }

}

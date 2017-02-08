package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.Activites.Customization;

/**
 * Created by Mido PC on 2/8/2017.
 */
public class CustomizationMessage {

    @SerializedName("CustomizationTypeID")
    @Expose
    private Integer customizationTypeID;
    @SerializedName("CustomizationType")
    @Expose
    private String customizationType;
    @SerializedName("customizations")
    @Expose
    private List<CustomizationM> customizations = null;

    public Integer getCustomizationTypeID() {
        return customizationTypeID;
    }

    public void setCustomizationTypeID(Integer customizationTypeID) {
        this.customizationTypeID = customizationTypeID;
    }

    public String getCustomizationType() {
        return customizationType;
    }

    public void setCustomizationType(String customizationType) {
        this.customizationType = customizationType;
    }

    public List<CustomizationM> getCustomizations() {
        return customizations;
    }

    public void setCustomizations(List<CustomizationM> customizations) {
        this.customizations = customizations;
    }



}

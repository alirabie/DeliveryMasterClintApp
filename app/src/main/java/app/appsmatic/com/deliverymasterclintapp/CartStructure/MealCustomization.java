package app.appsmatic.com.deliverymasterclintapp.CartStructure;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mido PC on 2/8/2017.
 */
public class MealCustomization {
    @SerializedName("CustomizationID")
    @Expose
    private Integer iD;
    private String customizationName;
    @SerializedName("CustomizationQuantity")
    @Expose
    private int customizationCount;
    @SerializedName("CustomizationPrice")
    @Expose
    private Double customizationPrice;


    public Integer getiD() {
        return iD;
    }

    public void setiD(Integer iD) {
        this.iD = iD;
    }

    public String getCustomizationName() {
        return customizationName;
    }

    public void setCustomizationName(String customizationName) {
        this.customizationName = customizationName;
    }

    public int getCustomizationCount() {
        return customizationCount;
    }

    public void setCustomizationCount(int customizationCount) {
        this.customizationCount = customizationCount;
    }

    public Double getCustomizationPrice() {
        return customizationPrice;
    }

    public void setCustomizationPrice(Double customizationPrice) {
        this.customizationPrice = customizationPrice;
    }
}

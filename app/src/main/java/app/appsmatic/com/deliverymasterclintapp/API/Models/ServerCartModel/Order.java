package app.appsmatic.com.deliverymasterclintapp.API.Models.ServerCartModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.CartStructure.MealAddition;

/**
 * Created by Mido PC on 2/7/2017.
 */
public class Order {

    @SerializedName("MealItemID")
    @Expose
    private Integer mealItemID;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("Quantity")
    @Expose
    private Integer quatnitiy;
    @SerializedName("additions")
    @Expose
    private List<MealAddition> additions = null;
    @SerializedName("customization")
    @Expose
    private Object customization;

    public Integer getMealItemID() {
        return mealItemID;
    }

    public void setMealItemID(Integer mealItemID) {
        this.mealItemID = mealItemID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuatnitiy() {
        return quatnitiy;
    }

    public void setQuatnitiy(Integer quatnitiy) {
        this.quatnitiy = quatnitiy;
    }

    public List<MealAddition> getAdditions() {
        return additions;
    }

    public void setAdditions(List<MealAddition> additions) {
        this.additions = additions;
    }

    public Object getCustomization() {
        return customization;
    }

    public void setCustomization(Object customization) {
        this.customization = customization;
    }
}

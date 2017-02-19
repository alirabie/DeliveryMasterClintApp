package app.appsmatic.com.deliverymasterclintapp.CartStructure;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mido PC on 1/19/2017.
 */
public class CartMeal {

    @SerializedName("MealItemID")
    @Expose
    private Integer mealItemID;
    @SerializedName("Price")
    @Expose
    private Double mealPrice;
    @SerializedName("Quantity")
    @Expose
    private int mealCount;
    @SerializedName("additions")
    @Expose
    private List<MealAddition>mealAdditions;
    @SerializedName("customization")
    @Expose
    private List<MealCustomization> customization;

    private String mealName;
    private String mealDecription;
    private String mealPic;

    public Integer getMealItemID() {
        return mealItemID;
    }

    public void setMealItemID(Integer mealItemID) {
        this.mealItemID = mealItemID;
    }

    public String getMealDecription() {
        return mealDecription;
    }

    public void setMealDecription(String mealDecription) {
        this.mealDecription = mealDecription;
    }

    public String getMealPic() {
        return mealPic;
    }

    public void setMealPic(String mealPic) {
        this.mealPic = mealPic;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getMealCount() {
        return mealCount;
    }

    public void setMealCount(int mealCount) {
        this.mealCount = mealCount;
    }

    public Double getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(Double mealPrice) {
        this.mealPrice = mealPrice;
    }

    public List<MealAddition> getMealAdditions() {
        return mealAdditions;
    }

    public void setMealAdditions(List<MealAddition> mealAdditions) {
        this.mealAdditions = mealAdditions;
    }

    public List<MealCustomization> getCustomization() {
        return customization;
    }
    public void setCustomization(List<MealCustomization> customization) {
        this.customization = customization;
    }
}

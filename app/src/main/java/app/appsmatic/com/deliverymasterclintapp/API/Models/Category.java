package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mido PC on 12/19/2016.
 */
public class Category {

    @SerializedName("RestaurantID")
    @Expose
    private Integer restaurantID;
    @SerializedName("restaurant")
    @Expose
    private Object restaurant;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("IsParent")
    @Expose
    private Boolean isParent;
    @SerializedName("ParentCode")
    @Expose
    private Object parentCode;
    @SerializedName("Enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("Icon")
    @Expose
    private String icon;
    @SerializedName("MealItems")
    @Expose
    private List<Object> mealItems = null;
    @SerializedName("MealsCount")
    @Expose
    private Object mealsCount;
    @SerializedName("ObjectState")
    @Expose
    private Integer objectState;

    public Integer getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(Integer restaurantID) {
        this.restaurantID = restaurantID;
    }

    public Object getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Object restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public Object getParentCode() {
        return parentCode;
    }

    public void setParentCode(Object parentCode) {
        this.parentCode = parentCode;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Object> getMealItems() {
        return mealItems;
    }

    public void setMealItems(List<Object> mealItems) {
        this.mealItems = mealItems;
    }

    public Object getMealsCount() {
        return mealsCount;
    }

    public void setMealsCount(Object mealsCount) {
        this.mealsCount = mealsCount;
    }

    public Integer getObjectState() {
        return objectState;
    }

    public void setObjectState(Integer objectState) {
        this.objectState = objectState;
    }
}

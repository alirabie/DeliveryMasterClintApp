package app.appsmatic.com.deliverymasterclintapp.CartStructure;

import java.util.List;

/**
 * Created by Mido PC on 1/19/2017.
 */
public class CartMeal {
    private String mealName;
    private int mealCount;
    private Double mealPrice;
    private String mealDecription;
    private String mealPic;

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

    private List<MealAddition>mealAdditions;


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



}

package app.appsmatic.com.deliverymasterclintapp.CartStructure;

import java.util.List;

/**
 * Created by Mido PC on 1/19/2017.
 */
public class CartMeal {
    private String mealName;
    private int mealCount;
    private float mealPrice;
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

    public float getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(float mealPrice) {
        this.mealPrice = mealPrice;
    }

    public List<MealAddition> getMealAdditions() {
        return mealAdditions;
    }

    public void setMealAdditions(List<MealAddition> mealAdditions) {
        this.mealAdditions = mealAdditions;
    }



}

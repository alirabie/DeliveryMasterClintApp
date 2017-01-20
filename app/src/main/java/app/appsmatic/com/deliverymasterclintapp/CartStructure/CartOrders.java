package app.appsmatic.com.deliverymasterclintapp.CartStructure;

import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.Meal;

/**
 * Created by Mido PC on 1/19/2017.
 */
public class CartOrders {
    List<CartMeal> cartMeals;

    public List<CartMeal> getMeals() {
        return cartMeals;
    }

    public void setMeals(List<CartMeal> meals) {
        this.cartMeals = meals;
    }
}

package app.appsmatic.com.deliverymasterclintapp.API.Models.ServerCartModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.Addition;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.MealAddition;

/**
 * Created by Mido PC on 2/6/2017.
 */
public class ServerCart {

    @SerializedName("order")
    @Expose
    private List<Order> order = null;
    @SerializedName("cartid")
    @Expose
    private String cartid;

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
    }
}

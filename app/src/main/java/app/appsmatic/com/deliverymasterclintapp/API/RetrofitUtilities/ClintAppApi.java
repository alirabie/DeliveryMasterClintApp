package app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities;
import app.appsmatic.com.deliverymasterclintapp.API.Models.Msg;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResAdditions;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCats;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCreateCart;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCustomizations;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResLocations;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResMeals;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResNewLocation;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResOrderConfirmation;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Mido PC on 12/4/2016.
 */
public interface ClintAppApi {



    //Sign Up method
    @POST("account/Register")
    Call<Msg> SignUp(@Body Object signup);

    //Login Method
    @POST("account/login")
    Call<Msg>Login(@Body Object loginData);

    //Categories Method
    @POST("menu/categories")
    Call<ResCats>GetCategories(@Body Object resId);

    //Meals
    @POST("menu/meals")
    Call<ResMeals>GetMeals(@Body Object catdata);

    //Additions
    @POST("menu/additions")
    Call<ResAdditions>GetAdditions(@Body Object mealData);

    //Get CustomizationM data
    @POST("menu/customizations")
    Call<ResCustomizations>GetCustomizations(@Body Object data);

    //Create Shopping Cart
    @POST("ShoppingCart/Create")
    Call<ResCreateCart>cereateShoppingCart(@Body Object cartdata);

    //add to cart
    @POST("ShoppingCart/AddToCart")
    Call<ResCreateCart>addtocart(@Body Object cartorder);

    //get Pickup Branches
    @POST("Location/PickupBranches")
    Call<ResLocations>getPicupBranches(@Body Object resturantId);

    //get delivery locations
    @POST("Location/DeliveryLocations")
    Call<ResLocations>getDeleviryLocatons(@Body Object data);

    //add new location
    @POST("Location/add")
    Call<ResNewLocation>addNewLocation(@Body Object data);

    //confirm order
    @POST("ShoppingCart/ConfirmOrder")
    Call<ResOrderConfirmation>confirmOrder(@Body Object data);




}

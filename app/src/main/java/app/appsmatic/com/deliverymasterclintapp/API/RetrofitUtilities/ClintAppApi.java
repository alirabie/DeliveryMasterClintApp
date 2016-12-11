package app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities;
import app.appsmatic.com.deliverymasterclintapp.API.Models.Msg;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Mido PC on 12/4/2016.
 */
public interface ClintAppApi {

    @FormUrlEncoded
    @POST("Account/Register")
    Call<Msg> SignUp(@Field("AccountType") String Atyp,
                     @Field("RestaurantID") String Rid,
                     @Field("FirstName") String fname,
                     @Field("LastName") String lname,
                     @Field("MobileNo") String mnum,
                     @Field("NewPassword") String pass);



}

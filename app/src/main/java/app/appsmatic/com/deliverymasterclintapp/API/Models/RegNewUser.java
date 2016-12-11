package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mido PC on 12/11/2016.
 */
public class RegNewUser {

    @SerializedName("AccountType")
    @Expose
    private String accountType;
    @SerializedName("RestaurantID")
    @Expose
    private String restaurantID;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("NewPassword")
    @Expose
    private String newPassword;

    /**
     *
     * @return
     * The accountType
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     *
     * @param accountType
     * The AccountType
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     *
     * @return
     * The restaurantID
     */
    public String getRestaurantID() {
        return restaurantID;
    }

    /**
     *
     * @param restaurantID
     * The RestaurantID
     */
    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    /**
     *
     * @return
     * The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The FirstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The LastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     * The mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     *
     * @param mobileNo
     * The MobileNo
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     *
     * @return
     * The newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     *
     * @param newPassword
     * The NewPassword
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

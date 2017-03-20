package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mido PC on 3/20/2017.
 */
public class UserProfile {
    @SerializedName("AccountType")
    @Expose
    private Integer accountType;
    @SerializedName("UserID")
    @Expose
    private String userID;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("NewPassword")
    @Expose
    private Object newPassword;
    @SerializedName("OldPassword")
    @Expose
    private Object oldPassword;
    @SerializedName("Active")
    @Expose
    private Boolean active;
    @SerializedName("RestaurantID")
    @Expose
    private Integer restaurantID;
    @SerializedName("LinkedAccount")
    @Expose
    private Object linkedAccount;
    @SerializedName("ObjectState")
    @Expose
    private Integer objectState;

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Object getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(Object newPassword) {
        this.newPassword = newPassword;
    }

    public Object getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(Object oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(Integer restaurantID) {
        this.restaurantID = restaurantID;
    }

    public Object getLinkedAccount() {
        return linkedAccount;
    }

    public void setLinkedAccount(Object linkedAccount) {
        this.linkedAccount = linkedAccount;
    }

    public Integer getObjectState() {
        return objectState;
    }

    public void setObjectState(Integer objectState) {
        this.objectState = objectState;
    }
}

package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mido PC on 3/14/2017.
 */
public class UserOrder {
    @SerializedName("OrderID")
    @Expose
    private Integer orderID;
    @SerializedName("OrderDate")
    @Expose
    private String orderDate;
    @SerializedName("BranchCode")
    @Expose
    private String branchCode;
    @SerializedName("BranchName")
    @Expose
    private String branchName;
    @SerializedName("PickupTime")
    @Expose
    private Object pickupTime;
    @SerializedName("Customer")
    @Expose
    private String customer;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Comment")
    @Expose
    private String comment;
    @SerializedName("OrderType")
    @Expose
    private String orderType;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("StatusID")
    @Expose
    private Integer statusID;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Object getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Object pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }
}

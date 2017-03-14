package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mido PC on 3/14/2017.
 */
public class CurrentOrder {
    @SerializedName("PickupBranchCode")
    @Expose
    private String pickupBranchCode;
    @SerializedName("PickupBranch")
    @Expose
    private String pickupBranch;
    @SerializedName("DeliveryBranchCode")
    @Expose
    private Object deliveryBranchCode;
    @SerializedName("DeliveryBranch")
    @Expose
    private Object deliveryBranch;
    @SerializedName("OrderID")
    @Expose
    private Integer orderID;
    @SerializedName("MealItemID")
    @Expose
    private Object mealItemID;
    @SerializedName("OrderDate")
    @Expose
    private String orderDate;
    @SerializedName("Name")
    @Expose
    private Object name;
    @SerializedName("Quantity")
    @Expose
    private Object quantity;
    @SerializedName("ItemPrice")
    @Expose
    private Double itemPrice;
    @SerializedName("AdditionID")
    @Expose
    private Object additionID;
    @SerializedName("AdditionName")
    @Expose
    private Object additionName;
    @SerializedName("AdditionQuantity")
    @Expose
    private Object additionQuantity;
    @SerializedName("AdditionPrice")
    @Expose
    private Object additionPrice;
    @SerializedName("CustomizationID")
    @Expose
    private Object customizationID;
    @SerializedName("CustomizationName")
    @Expose
    private Object customizationName;
    @SerializedName("CustomizationQuantity")
    @Expose
    private Object customizationQuantity;
    @SerializedName("CustomizationPrice")
    @Expose
    private Object customizationPrice;
    @SerializedName("AdditionLinkedMealID")
    @Expose
    private Object additionLinkedMealID;
    @SerializedName("UserID")
    @Expose
    private Object userID;
    @SerializedName("Customer")
    @Expose
    private String customer;
    @SerializedName("StatusID")
    @Expose
    private Integer statusID;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("OrderType")
    @Expose
    private String orderType;
    @SerializedName("Comment")
    @Expose
    private Object comment;
    @SerializedName("PickupTime")
    @Expose
    private Object pickupTime;
    @SerializedName("DeliveryETA")
    @Expose
    private Object deliveryETA;

    public String getPickupBranchCode() {
        return pickupBranchCode;
    }

    public void setPickupBranchCode(String pickupBranchCode) {
        this.pickupBranchCode = pickupBranchCode;
    }

    public String getPickupBranch() {
        return pickupBranch;
    }

    public void setPickupBranch(String pickupBranch) {
        this.pickupBranch = pickupBranch;
    }

    public Object getDeliveryBranchCode() {
        return deliveryBranchCode;
    }

    public void setDeliveryBranchCode(Object deliveryBranchCode) {
        this.deliveryBranchCode = deliveryBranchCode;
    }

    public Object getDeliveryBranch() {
        return deliveryBranch;
    }

    public void setDeliveryBranch(Object deliveryBranch) {
        this.deliveryBranch = deliveryBranch;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Object getMealItemID() {
        return mealItemID;
    }

    public void setMealItemID(Object mealItemID) {
        this.mealItemID = mealItemID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getQuantity() {
        return quantity;
    }

    public void setQuantity(Object quantity) {
        this.quantity = quantity;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Object getAdditionID() {
        return additionID;
    }

    public void setAdditionID(Object additionID) {
        this.additionID = additionID;
    }

    public Object getAdditionName() {
        return additionName;
    }

    public void setAdditionName(Object additionName) {
        this.additionName = additionName;
    }

    public Object getAdditionQuantity() {
        return additionQuantity;
    }

    public void setAdditionQuantity(Object additionQuantity) {
        this.additionQuantity = additionQuantity;
    }

    public Object getAdditionPrice() {
        return additionPrice;
    }

    public void setAdditionPrice(Object additionPrice) {
        this.additionPrice = additionPrice;
    }

    public Object getCustomizationID() {
        return customizationID;
    }

    public void setCustomizationID(Object customizationID) {
        this.customizationID = customizationID;
    }

    public Object getCustomizationName() {
        return customizationName;
    }

    public void setCustomizationName(Object customizationName) {
        this.customizationName = customizationName;
    }

    public Object getCustomizationQuantity() {
        return customizationQuantity;
    }

    public void setCustomizationQuantity(Object customizationQuantity) {
        this.customizationQuantity = customizationQuantity;
    }

    public Object getCustomizationPrice() {
        return customizationPrice;
    }

    public void setCustomizationPrice(Object customizationPrice) {
        this.customizationPrice = customizationPrice;
    }

    public Object getAdditionLinkedMealID() {
        return additionLinkedMealID;
    }

    public void setAdditionLinkedMealID(Object additionLinkedMealID) {
        this.additionLinkedMealID = additionLinkedMealID;
    }

    public Object getUserID() {
        return userID;
    }

    public void setUserID(Object userID) {
        this.userID = userID;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public Object getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Object pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Object getDeliveryETA() {
        return deliveryETA;
    }

    public void setDeliveryETA(Object deliveryETA) {
        this.deliveryETA = deliveryETA;
    }
}

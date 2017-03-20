package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mido PC on 3/20/2017.
 */
public class OrderDetailsCustomization {

    @SerializedName("PickupBranchCode")
    @Expose
    private Object pickupBranchCode;
    @SerializedName("PickupBranch")
    @Expose
    private Object pickupBranch;
    @SerializedName("DeliveryBranchCode")
    @Expose
    private Object deliveryBranchCode;
    @SerializedName("DeliveryBranch")
    @Expose
    private Object deliveryBranch;
    @SerializedName("OrderID")
    @Expose
    private Object orderID;
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
    private Integer customizationID;
    @SerializedName("CustomizationName")
    @Expose
    private String customizationName;
    @SerializedName("CustomizationQuantity")
    @Expose
    private Integer customizationQuantity;
    @SerializedName("CustomizationPrice")
    @Expose
    private Double customizationPrice;
    @SerializedName("AdditionLinkedMealID")
    @Expose
    private Object additionLinkedMealID;
    @SerializedName("UserID")
    @Expose
    private Object userID;
    @SerializedName("Customer")
    @Expose
    private Object customer;
    @SerializedName("StatusID")
    @Expose
    private Object statusID;
    @SerializedName("Status")
    @Expose
    private Object status;
    @SerializedName("OrderType")
    @Expose
    private Object orderType;
    @SerializedName("Comment")
    @Expose
    private Object comment;
    @SerializedName("PickupTime")
    @Expose
    private Object pickupTime;
    @SerializedName("DeliveryETA")
    @Expose
    private Object deliveryETA;

    public Object getPickupBranchCode() {
        return pickupBranchCode;
    }

    public void setPickupBranchCode(Object pickupBranchCode) {
        this.pickupBranchCode = pickupBranchCode;
    }

    public Object getPickupBranch() {
        return pickupBranch;
    }

    public void setPickupBranch(Object pickupBranch) {
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

    public Object getOrderID() {
        return orderID;
    }

    public void setOrderID(Object orderID) {
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

    public Integer getCustomizationID() {
        return customizationID;
    }

    public void setCustomizationID(Integer customizationID) {
        this.customizationID = customizationID;
    }

    public String getCustomizationName() {
        return customizationName;
    }

    public void setCustomizationName(String customizationName) {
        this.customizationName = customizationName;
    }

    public Integer getCustomizationQuantity() {
        return customizationQuantity;
    }

    public void setCustomizationQuantity(Integer customizationQuantity) {
        this.customizationQuantity = customizationQuantity;
    }

    public Double getCustomizationPrice() {
        return customizationPrice;
    }

    public void setCustomizationPrice(Double customizationPrice) {
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

    public Object getCustomer() {
        return customer;
    }

    public void setCustomer(Object customer) {
        this.customer = customer;
    }

    public Object getStatusID() {
        return statusID;
    }

    public void setStatusID(Object statusID) {
        this.statusID = statusID;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getOrderType() {
        return orderType;
    }

    public void setOrderType(Object orderType) {
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

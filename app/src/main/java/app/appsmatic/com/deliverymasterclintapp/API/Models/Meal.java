package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mido PC on 12/19/2016.
 */
public class Meal {
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("CategoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ImagePreview")
    @Expose
    private String imagePreview;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("TimeToPrepare")
    @Expose
    private Double timeToPrepare;
    @SerializedName("InStock")
    @Expose
    private Boolean inStock;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePreview() {
        return imagePreview;
    }

    public void setImagePreview(String imagePreview) {
        this.imagePreview = imagePreview;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTimeToPrepare() {
        return timeToPrepare;
    }

    public void setTimeToPrepare(Double timeToPrepare) {
        this.timeToPrepare = timeToPrepare;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }
}

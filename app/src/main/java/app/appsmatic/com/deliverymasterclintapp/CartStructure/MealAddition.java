package app.appsmatic.com.deliverymasterclintapp.CartStructure;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mido PC on 1/19/2017.
 */
public class MealAddition {



    @SerializedName("AdditionID")
    @Expose

    private Integer iD;

    @SerializedName("AdditionName")
    @Expose
    private String additionName;

    @SerializedName("AdditionQuantity")
    @Expose
    private int addCount;

    @SerializedName("AdditionPrice")
    @Expose
    private Double addprice;

    public String getAdditionName() {
        return additionName;
    }

    public void setAdditionName(String additionName) {
        this.additionName = additionName;
    }

    public int getAddCount() {
        return addCount;
    }

    public void setAddCount(int addCount) {
        this.addCount = addCount;
    }

    public Double getAddprice() {
        return addprice;
    }

    public void setAddprice(Double addprice) {
        this.addprice = addprice;
    }
    public Integer getiD() {
        return iD;
    }

    public void setiD(Integer iD) {
        this.iD = iD;
    }
}

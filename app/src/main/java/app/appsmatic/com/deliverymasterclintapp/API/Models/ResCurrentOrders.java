package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mido PC on 3/14/2017.
 */
public class ResCurrentOrders {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private List<CurrentOrder> message = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<CurrentOrder> getMessage() {
        return message;
    }

    public void setMessage(List<CurrentOrder> message) {
        this.message = message;
    }
}

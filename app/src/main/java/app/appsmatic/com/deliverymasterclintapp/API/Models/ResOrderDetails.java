package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mido PC on 3/20/2017.
 */
public class ResOrderDetails {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private List<OrderDetailsItem> message = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<OrderDetailsItem> getMessage() {
        return message;
    }

    public void setMessage(List<OrderDetailsItem> message) {
        this.message = message;
    }
}

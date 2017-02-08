package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mido PC on 2/8/2017.
 */
public class ResCustomizations {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private List<CustomizationMessage> message = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<CustomizationMessage> getMessage() {
        return message;
    }

    public void setMessage(List<CustomizationMessage> message) {
        this.message = message;
    }

}

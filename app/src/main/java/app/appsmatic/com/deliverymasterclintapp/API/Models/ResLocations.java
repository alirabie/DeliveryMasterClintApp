package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mido PC on 2/19/2017.
 */
public class ResLocations {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private List<LocatioN> message = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<LocatioN> getMessage() {
        return message;
    }

    public void setMessage(List<LocatioN> message) {
        this.message = message;
    }
}

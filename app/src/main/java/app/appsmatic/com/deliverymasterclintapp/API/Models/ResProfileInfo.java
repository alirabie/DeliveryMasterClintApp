package app.appsmatic.com.deliverymasterclintapp.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mido PC on 3/20/2017.
 */
public class ResProfileInfo {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private List<UserProfile> message = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<UserProfile> getMessage() {
        return message;
    }

    public void setMessage(List<UserProfile> message) {
        this.message = message;
    }
}

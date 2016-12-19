package app.appsmatic.com.deliverymasterclintapp.API.Models;
import android.os.Message;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mido PC on 12/19/2016.
 */
public class ResCats {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private List<Category> message = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Category> getMessage() {
        return message;
    }

    public void setMessage(List<Category> message) {
        this.message = message;
    }
}

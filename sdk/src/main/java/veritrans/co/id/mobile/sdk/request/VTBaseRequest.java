package veritrans.co.id.mobile.sdk.request;

import com.google.gson.annotations.SerializedName;

import id.co.veritrans.android.api.VTUtil.VTConfig;

/**
 * Created by muhammadanis on 2/2/15.
 */
public class VTBaseRequest {

    @SerializedName("client_key")
    protected String clientKey;

    public VTBaseRequest(){
        clientKey = VTConfig.CLIENT_KEY;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }
}

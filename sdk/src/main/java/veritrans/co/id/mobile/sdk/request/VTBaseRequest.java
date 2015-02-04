package veritrans.co.id.mobile.sdk.request;

import com.google.gson.annotations.SerializedName;
import veritrans.co.id.mobile.sdk.helper.VTMobileConfig;

/**
 * Created by muhammadanis on 2/2/15.
 */
public class VTBaseRequest {

    @SerializedName("client_key")
    protected String clientKey;

    public VTBaseRequest(){
        clientKey = VTMobileConfig.ClientKey;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }
}

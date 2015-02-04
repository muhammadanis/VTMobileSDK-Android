package veritrans.co.id.mobile.sdk.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muhammadanis on 2/2/15.
 */
public class VTConfirmTransactionRequest extends VTBaseRequest{

    @SerializedName("transaction_id")
    private String transactionId;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}

package veritrans.co.id.mobile.sdk.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.veritrans.android.api.VTModel.VTCardDetails;
import veritrans.co.id.mobile.sdk.entity.VTProduct;
import veritrans.co.id.mobile.sdk.entity.VTTokenData;

/**
 * Created by muhammadanis on 2/2/15.
 */
public class VTChargeRequest extends VTBaseRequest {
    private String email;

    @SerializedName("payment_type")
    private String paymentType;

    @SerializedName("item_details")
    private List<VTProduct> items;

    @SerializedName("credit_card")
    private VTTokenData tokenData;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public List<VTProduct> getItems() {
        return items;
    }

    public void setItems(List<VTProduct> items) {
        this.items = items;
    }

    public VTTokenData getTokenData() {
        return tokenData;
    }

    public void setTokenData(VTTokenData tokenData) {
        this.tokenData = tokenData;
    }
}

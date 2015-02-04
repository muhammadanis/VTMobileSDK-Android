package veritrans.co.id.mobile.sdk.request;

import veritrans.co.id.mobile.sdk.entity.VTCardDetails;
import veritrans.co.id.mobile.sdk.helper.VTConstants;
import veritrans.co.id.mobile.sdk.helper.VTMobileConfig;

/**
 * Created by muhammadanis on 2/4/15.
 */
public class VTTokenRequest {
    private VTCardDetails cardDetails;


    public String getTokenUrl() {
        return VTConstants.getTokenEndPoint() + cardDetails.getParamUrl();
    }

    public VTCardDetails getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(VTCardDetails cardDetails) {
        this.cardDetails = cardDetails;
    }
}

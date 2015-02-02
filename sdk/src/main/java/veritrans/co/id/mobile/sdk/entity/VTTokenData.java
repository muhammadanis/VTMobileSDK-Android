package veritrans.co.id.mobile.sdk.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muhammadanis on 2/2/15.
 */
public class VTTokenData {

    @SerializedName("token_id")
    private String tokenId;

    private String bank;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}

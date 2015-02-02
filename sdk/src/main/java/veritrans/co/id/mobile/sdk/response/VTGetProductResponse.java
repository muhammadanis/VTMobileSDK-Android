package veritrans.co.id.mobile.sdk.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import veritrans.co.id.mobile.sdk.entity.VTProduct;

/**
 * Created by muhammadanis on 2/2/15.
 */
public class VTGetProductResponse {

    @SerializedName("merchant_id")
    public String merchantId;

    @SerializedName("merchant_product")
    List<VTProduct> products;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public List<VTProduct> getProducts() {
        return products;
    }

    public void setProducts(List<VTProduct> products) {
        this.products = products;
    }
}

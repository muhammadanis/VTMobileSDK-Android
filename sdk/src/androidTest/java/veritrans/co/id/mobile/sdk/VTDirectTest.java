package veritrans.co.id.mobile.sdk;

import android.test.InstrumentationTestCase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import id.co.veritrans.android.api.VTDirect;
import id.co.veritrans.android.api.VTInterface.ITokenCallback;
import id.co.veritrans.android.api.VTModel.VTCardDetails;
import id.co.veritrans.android.api.VTModel.VTToken;
import id.co.veritrans.android.api.VTUtil.VTConfig;
import veritrans.co.id.mobile.sdk.entity.VTProduct;
import veritrans.co.id.mobile.sdk.entity.VTTokenData;
import veritrans.co.id.mobile.sdk.helper.VTConstants;
import veritrans.co.id.mobile.sdk.helper.VTLogger;
import veritrans.co.id.mobile.sdk.interfaces.IActionCallback;
import veritrans.co.id.mobile.sdk.request.VTChargeRequest;
import veritrans.co.id.mobile.sdk.response.VTChargeResponse;
import veritrans.co.id.mobile.sdk.response.VTGetProductResponse;
import veritrans.co.id.mobile.sdk.vtexceptions.VTMobileException;

/**
 * Created by muhammadanis on 1/28/15.
 */
public class VTDirectTest extends InstrumentationTestCase {


    private final VTLogger logger = new VTLogger(VTDirectTest.class);

    @Override
    protected void setUp() throws Exception {

        VTConfig.VT_IsProduction = false;
        VTConfig.CLIENT_KEY = "VT-client-SimkwEjR3_fKj73D";
    }

    public void testGetProductList(){

        VTMobile.getAllProducts(new IActionCallback<VTGetProductResponse, VTMobileException>() {
            @Override
            public void onSuccess(VTGetProductResponse data) {
                logger.Log(String.format("Endpoint: %s", VTConstants.Endpoint), VTLogger.LogLevel.DEBUG);
                logger.Log("Success get AllProducts", VTLogger.LogLevel.DEBUG);
                assertNotNull(data);
                for(VTProduct product : data.getProducts()){
                    logger.Log(product.toString(), VTLogger.LogLevel.DEBUG);
                }
            }

            @Override
            public void onError(VTMobileException error) {
                logger.Log("OnError", VTLogger.LogLevel.INFO);
                logger.Log(error.getRawMessage(), VTLogger.LogLevel.ERROR);
                assertFalse(true);
            }
        });
    }

    public void testCharging() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);
        VTCardDetails cardDetails = CardFactory(false);
        VTDirect vtDirect = new VTDirect();
        vtDirect.setCard_details(cardDetails);

        logger.Log("Start test charging vt direct", VTLogger.LogLevel.DEBUG);
        vtDirect.getToken(new ITokenCallback() {
            @Override
            public void onSuccess(VTToken vtToken) {

                logger.Log("Success to get token", VTLogger.LogLevel.DEBUG);
                //create product
                VTProduct vtProduct = new VTProduct();
                vtProduct.setId("23aa44");
                vtProduct.setPrice(42500);
                vtProduct.setQuantity(2);

                //create tokendata
                VTTokenData tokenData = new VTTokenData();
                tokenData.setTokenId(vtToken.getToken_id());

                //create charge request
                VTChargeRequest chargeRequest = new VTChargeRequest();

                //create products
                List<VTProduct> products = new ArrayList<>();
                products.add(vtProduct);

                chargeRequest.setEmail("dichi@alfaridi.info");
                chargeRequest.setItems(products);
                chargeRequest.setPaymentType("credit_card");
                chargeRequest.setTokenData(tokenData);

                logger.Log("Start call charging", VTLogger.LogLevel.INFO);
                VTMobile.charge(new IActionCallback<VTChargeResponse,VTMobileException>(){

                    @Override
                    public void onSuccess(VTChargeResponse data) {
                        Log.d(VTConstants.SDK_TAG,"success charging");
                        logger.Log("Success charge " + data.toString(), VTLogger.LogLevel.DEBUG);
                        logger.Log("Trx id: "+data.getTransaction_id(), VTLogger.LogLevel.DEBUG);
                        assertNotNull(data);
                        signal.countDown();
                    }

                    @Override
                    public void onError(VTMobileException error) {
                        logger.Log("Failed to Charge " + error.getRawMessage(), VTLogger.LogLevel.ERROR);
                        assertFalse(true);
                        signal.countDown();
                    }
                }, chargeRequest);
            }

            @Override
            public void onError(Exception e) {
                logger.Log("Failed to get token", VTLogger.LogLevel.ERROR);
                assertFalse(true);
            }
        });

        signal.await();


    }

    public static VTCardDetails CardFactory(boolean secure){
        VTCardDetails cardDetails = new VTCardDetails();
        cardDetails.setCard_number("4811111111111114");
        cardDetails.setCard_cvv("123");
        cardDetails.setCard_exp_month(1);
        cardDetails.setCard_exp_year(2020);
        cardDetails.setSecure(secure);
        cardDetails.setGross_amount("100000");
        return cardDetails;
    }
}

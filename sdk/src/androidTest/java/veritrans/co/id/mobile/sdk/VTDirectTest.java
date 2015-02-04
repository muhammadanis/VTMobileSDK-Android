package veritrans.co.id.mobile.sdk;

import android.test.InstrumentationTestCase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import veritrans.co.id.mobile.sdk.entity.VTCardDetails;
import veritrans.co.id.mobile.sdk.entity.VTProduct;
import veritrans.co.id.mobile.sdk.entity.VTTokenData;
import veritrans.co.id.mobile.sdk.helper.VTConstants;
import veritrans.co.id.mobile.sdk.helper.VTLogger;
import veritrans.co.id.mobile.sdk.helper.VTMobileConfig;
import veritrans.co.id.mobile.sdk.interfaces.IActionCallback;
import veritrans.co.id.mobile.sdk.request.VTChargeRequest;
import veritrans.co.id.mobile.sdk.request.VTConfirmTransactionRequest;
import veritrans.co.id.mobile.sdk.request.VTTokenRequest;
import veritrans.co.id.mobile.sdk.response.VTChargeResponse;
import veritrans.co.id.mobile.sdk.response.VTConfirmTransactionResponse;
import veritrans.co.id.mobile.sdk.response.VTGetProductResponse;
import veritrans.co.id.mobile.sdk.response.VTTokenResponse;
import veritrans.co.id.mobile.sdk.vtexceptions.VTMobileException;

import static veritrans.co.id.mobile.sdk.helper.VTLogger.LogLevel;

/**
 * Created by muhammadanis on 1/28/15.
 */
public class VTDirectTest extends InstrumentationTestCase {


    private final VTLogger logger = new VTLogger(VTDirectTest.class);

    @Override
    protected void setUp() throws Exception {

        VTMobileConfig.IsProduction = false;
        VTMobileConfig.ClientKey = "VT-client-SimkwEjR3_fKj73D";
    }

    public void testGetProductList(){

        VTMobile.getAllProducts(new IActionCallback<VTGetProductResponse, VTMobileException>() {
            @Override
            public void onSuccess(VTGetProductResponse data) {
                assertNotNull(data);
                for(VTProduct product : data.getProducts()){
                    logger.Log(product.toString(), LogLevel.DEBUG);
                }
            }

            @Override
            public void onError(VTMobileException error) {
                logger.Log("OnError", LogLevel.INFO);
                logger.Log(error.getRawMessage(), LogLevel.ERROR);
                assertFalse(true);
            }
        });
    }

    public void testGetToken() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);
        VTCardDetails cardDetails = CardFactory(false);
        VTTokenRequest request = new VTTokenRequest();
        request.setCardDetails(cardDetails);

        logger.Log("Start get token", LogLevel.DEBUG);

        VTMobile.getToken(new IActionCallback<VTTokenResponse, VTMobileException>() {
            @Override
            public void onSuccess(VTTokenResponse data) {
                assertNotNull(data);
                assertNotNull(data.getToken_id());
                logger.Log("Token Id: "+data.getToken_id(),LogLevel.DEBUG);
                signal.countDown();
            }

            @Override
            public void onError(VTMobileException error) {
                error.printStackTrace();
                assertFalse(true);
                signal.countDown();
            }
        },request);
        signal.await();
    }

    public void testCharging() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);
        VTCardDetails cardDetails = CardFactory(false);
        
        logger.Log("Start test charging vt direct", LogLevel.DEBUG);
        vtDirect.getToken(new ITokenCallback() {
            @Override
            public void onSuccess(VTToken vtToken) {

                logger.Log("Success to get token", LogLevel.DEBUG);
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

                logger.Log("Start call charging", LogLevel.INFO);
                VTMobile.charge(new IActionCallback<VTChargeResponse,VTMobileException>(){

                    @Override
                    public void onSuccess(VTChargeResponse data) {
                        Log.d(VTConstants.SDK_TAG,"success charging");
                        logger.Log("Success charge " + data.toString(), LogLevel.DEBUG);
                        logger.Log("Trx id: "+data.getTransaction_id(), LogLevel.DEBUG);
                        assertNotNull(data);
                        signal.countDown();
                    }

                    @Override
                    public void onError(VTMobileException error) {
                        logger.Log("Failed to Charge " + error.getRawMessage(), LogLevel.ERROR);
                        assertFalse(true);
                        signal.countDown();
                    }
                }, chargeRequest);
            }

            @Override
            public void onError(Exception e) {
                logger.Log("Failed to get token", LogLevel.ERROR);
                assertFalse(true);
            }
        });

        signal.await();


    }

    public void testConfirmTransaction() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);
        VTConfirmTransactionRequest request = new VTConfirmTransactionRequest();
        request.setTransactionId("6f09c95d-75ff-4ecd-956c-c29095603bdd");
        VTMobile.confirmTransaction(new IActionCallback<VTConfirmTransactionResponse, VTMobileException>() {
            @Override
            public void onSuccess(VTConfirmTransactionResponse data) {
                assertNotNull(data);
                logger.Log("IsSuccess: "+ Boolean.toString(data.isSuccess()), LogLevel.DEBUG);
                signal.countDown();
            }

            @Override
            public void onError(VTMobileException error) {
                assertFalse(true);
                logger.Log("failed with error: "+error.getRawMessage(), LogLevel.ERROR);
                signal.countDown();
            }
        }, request);

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

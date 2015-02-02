package veritrans.co.id.mobile.sdk;

import android.test.InstrumentationTestCase;

import java.util.List;

import id.co.veritrans.android.api.VTUtil.VTConfig;
import veritrans.co.id.mobile.sdk.entity.VTProduct;
import veritrans.co.id.mobile.sdk.VTMobile;
import veritrans.co.id.mobile.sdk.helper.Constants;
import veritrans.co.id.mobile.sdk.helper.Logger;
import veritrans.co.id.mobile.sdk.interfaces.IActionCallback;
import veritrans.co.id.mobile.sdk.vtexceptions.VTMobileException;

/**
 * Created by muhammadanis on 1/28/15.
 */
public class VTDirectTest extends InstrumentationTestCase {


    private final Logger logger = new Logger(VTDirectTest.class);

    @Override
    protected void setUp() throws Exception {

        VTConfig.VT_IsProduction = false;
        VTConfig.CLIENT_KEY = "VT-client-SimkwEjR3_fKj73D";
    }

    public void testGetProductList(){

        VTMobile.getAllProducts(new IActionCallback<List<VTProduct>, VTMobileException>() {
            @Override
            public void onSuccess(List<VTProduct> data) {
                logger.Log(String.format("Endpoint: %s", Constants.Endpoint), Logger.LogLevel.INFO);
                logger.Log("Success get AllProducts ewasdasd", Logger.LogLevel.INFO);
                assertNull(data);
            }

            @Override
            public void onError(VTMobileException error) {
                logger.Log(error.getRawMessage(), Logger.LogLevel.ERROR);
                assertFalse(true);
            }
        });



    }
}

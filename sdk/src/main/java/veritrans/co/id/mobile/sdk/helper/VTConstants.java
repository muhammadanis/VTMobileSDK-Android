package veritrans.co.id.mobile.sdk.helper;

import veritrans.co.id.mobile.sdk.BuildConfig;

/**
 * Created by muhammadanis on 1/29/15.
 */
public class VTConstants {
    public static String SDK_TAG = "vt-mobile-sdk";

    public static String Port = "8080";
    public static String Version = "/v1";

    private static String Endpoint = "http://10.255.255.25:";
    private static String SandboxEndpoint = "http://10.255.255.25:";

    private static String TokenEndpoint = "https://api.veritrans.co.id/v2/token";
    private static String SandboxTokenEndpoint = "https://api.sandbox.veritrans.co.id/v2/token";



    private static String getEndpoint(){
        return VTMobileConfig.IsProduction ?  Endpoint + Port + Version   : SandboxEndpoint + Port + Version;
    }

    public static String GetProductsEndpoint(){
        return getEndpoint() + "/merchant/product";
    }

    public static String ChargeEndpoint(){
        return getEndpoint() + "/charge";
    }

    public static String ConfirmTransactionEndpoint() {
        return getEndpoint() + "/merchant/payment/confirm";
    }

    public static String getTokenEndPoint() {
        return VTMobileConfig.IsProduction ? TokenEndpoint : SandboxTokenEndpoint;
    }
}

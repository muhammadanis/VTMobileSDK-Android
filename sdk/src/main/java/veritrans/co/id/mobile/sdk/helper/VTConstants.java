package veritrans.co.id.mobile.sdk.helper;

import veritrans.co.id.mobile.sdk.BuildConfig;

/**
 * Created by muhammadanis on 1/29/15.
 */
public class VTConstants {
    public static String SDK_TAG = "vt-mobile-sdk";

    public static boolean isDebug = true;

    public static String Port = "8080";

    public static String Version = "/v1";

    public static String Endpoint = BuildConfig.DEBUG ? "http://10.255.255.25:" + Port + Version   : "http://10.255.255.1:" + Port + Version;



    public static  String GetProductsEndpoint = Endpoint + "/merchant/product";
    public static String ChargeEndpoint = Endpoint + "/charge";
}

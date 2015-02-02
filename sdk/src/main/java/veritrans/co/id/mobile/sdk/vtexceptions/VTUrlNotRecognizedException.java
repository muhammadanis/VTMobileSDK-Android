package veritrans.co.id.mobile.sdk.vtexceptions;

import veritrans.co.id.mobile.sdk.helper.VTStrings;

/**
 * Created by muhammadanis on 1/29/15.
 */
public class VTUrlNotRecognizedException extends VTMobileException {
    public VTUrlNotRecognizedException(String url){
        super(String.format(VTStrings.URL_NOT_RECOGNIZED_EXCEPTION,url));
    }
}

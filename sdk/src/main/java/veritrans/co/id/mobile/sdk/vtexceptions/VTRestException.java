package veritrans.co.id.mobile.sdk.vtexceptions;

import veritrans.co.id.mobile.sdk.helper.Strings;

/**
 * Created by muhammadanis on 1/30/15.
 */
public class VTRestException extends VTMobileException{

    public VTRestException(String json, Exception e) {
        super(String.format(Strings.REST_NOT_RECOGNIZED_EXCEPTION,json), e);
    }

    public VTRestException(String json){
        super(String.format(Strings.REST_NOT_RECOGNIZED_EXCEPTION,json));
    }
}

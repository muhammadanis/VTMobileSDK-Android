package veritrans.co.id.mobile.sdk.vtexceptions;

import veritrans.co.id.mobile.sdk.helper.Strings;

/**
 * Created by muhammadanis on 1/30/15.
 */
public class VTConnectionException extends VTMobileException{

    public VTConnectionException(Exception e){
        super(Strings.CONNECTION_EXCEPTION,e);
    }
}

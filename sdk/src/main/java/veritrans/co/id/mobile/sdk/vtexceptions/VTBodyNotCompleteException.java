package veritrans.co.id.mobile.sdk.vtexceptions;

import veritrans.co.id.mobile.sdk.helper.VTStrings;

/**
 * Created by muhammadanis on 2/2/15.
 */
public class VTBodyNotCompleteException extends VTMobileException{

    public VTBodyNotCompleteException(Exception e){
        super(VTStrings.BODY_EXCEPTION,e);
    }

    public VTBodyNotCompleteException(){
        super(VTStrings.BODY_EXCEPTION);
    }
}

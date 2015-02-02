package veritrans.co.id.mobile.sdk.vtexceptions;

import veritrans.co.id.mobile.sdk.helper.VTStrings;

/**
 * Created by muhammadanis on 1/29/15.
 */

/***
 * This is A Generic class that handles unknown exception
 * You should not manually throw this exception, unless it is really impossible to reach some lines of code
 */
public class VTMobileException extends Exception{
    protected Exception e;

    public VTMobileException(String s){
        super(VTStrings.GENERAL_ERROR);
        this.e = new Exception("Unknown Exception");
    }

    public VTMobileException(Exception e){
        super(VTStrings.GENERAL_ERROR);
        this.e = e;
    }

    public VTMobileException(String s, Exception e){
        super(VTStrings.GENERAL_ERROR);
        this.e = e;
    }

    public VTMobileException(String s, Throwable t, Exception e){
        super(VTStrings.GENERAL_ERROR,t);
        this.e = e;
    }

    public VTMobileException(Throwable t, Exception e){
        super(t);
        this.e = e;
    }

    public Exception getRawException(){
        return e;
    }

    public String getRawMessage(){
        return e.getMessage();
    }
}

package veritrans.co.id.mobile.sdk.helper;

import android.util.Log;

/**
 * Created by muhammadanis on 1/29/15.
 */
public class VTLogger {
    public enum LogLevel{
        VERBOSE,
        INFO,
        DEBUG,
        ERROR
    }

    String className;
    public VTLogger(Class c){
        className = c.getName();
    }

    public void Log(String message, LogLevel logLevel){
        String allMessage = String.format("Class: %s, Message: %s",className,message);
        switch (logLevel){
            case VERBOSE:
                Log.v(VTConstants.SDK_TAG,allMessage);
                break;
            case DEBUG:
                Log.d(VTConstants.SDK_TAG,allMessage);
                break;
            case ERROR:
                Log.e(VTConstants.SDK_TAG,allMessage);
                break;
            case INFO:
                Log.i(VTConstants.SDK_TAG,allMessage);
                break;
            default:
                break;
        }
    }
}

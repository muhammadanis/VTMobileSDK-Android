package veritrans.co.id.mobile.sdk.helper;

import android.util.Log;

import id.co.veritrans.android.api.VTDirect;

/**
 * Created by muhammadanis on 1/29/15.
 */
public class Logger {
    public enum LogLevel{
        VERBOSE,
        INFO,
        DEBUG,
        ERROR
    }

    String className;
    public Logger(Class c){
        className = c.getName();
    }

    public void Log(String message, LogLevel logLevel){
        String allMessage = String.format("Class: %s, Message: %s",className,message);
        switch (logLevel){
            case VERBOSE:
                Log.v(Constants.SDK_TAG,allMessage);
                break;
            case DEBUG:
                Log.d(Constants.SDK_TAG,allMessage);
                break;
            case ERROR:
                Log.e(Constants.SDK_TAG,allMessage);
                break;
            case INFO:
                Log.i(Constants.SDK_TAG,allMessage);
                break;
            default:
                break;
        }
    }
}

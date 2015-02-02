package veritrans.co.id.mobile.sdk.helper;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by muhammadanis on 2/2/15.
 */
public class VTHttpContainer {

    public enum VTHttpMethod{
        GET,
        POST
    }

    private String payload;
    private VTHttpMethod httpMethod;
    private String contentType;
    private String accept;

    public VTHttpContainer(){
        contentType = "application/json";
        accept = "application/json";
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public VTHttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(VTHttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
}

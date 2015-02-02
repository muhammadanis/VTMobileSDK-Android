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

    private List<NameValuePair> payload;
    private VTHttpMethod httpMethod;

    public VTHttpContainer(){

    }

    public List<NameValuePair> getPayload() {
        return payload;
    }

    public void setPayload(List<NameValuePair> payload) {
        this.payload = payload;
    }

    public VTHttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(VTHttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }
}

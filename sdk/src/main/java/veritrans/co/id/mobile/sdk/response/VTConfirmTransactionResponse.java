package veritrans.co.id.mobile.sdk.response;

/**
 * Created by muhammadanis on 2/2/15.
 */
public class VTConfirmTransactionResponse {
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSuccess(){
        return result.equalsIgnoreCase("OK");
    }

    public boolean isFailed(){
        return !isSuccess();
    }
}

package veritrans.co.id.mobile.sdk.interfaces;

/**
 * Created by muhammadanis on 1/29/15.
 */
public interface IActionCallback<T,E> {
    public void onSuccess(T data);
    public void onError(E error);

}

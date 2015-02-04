package veritrans.co.id.mobile.sdk;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import veritrans.co.id.mobile.sdk.helper.VTConstants;
import veritrans.co.id.mobile.sdk.helper.VTHttpContainer;
import veritrans.co.id.mobile.sdk.helper.VTLogger;
import veritrans.co.id.mobile.sdk.interfaces.IActionCallback;
import veritrans.co.id.mobile.sdk.request.VTBaseRequest;
import veritrans.co.id.mobile.sdk.request.VTChargeRequest;
import veritrans.co.id.mobile.sdk.request.VTConfirmTransactionRequest;
import veritrans.co.id.mobile.sdk.request.VTTokenRequest;
import veritrans.co.id.mobile.sdk.response.VTChargeResponse;
import veritrans.co.id.mobile.sdk.response.VTConfirmTransactionResponse;
import veritrans.co.id.mobile.sdk.response.VTGetProductResponse;
import veritrans.co.id.mobile.sdk.response.VTTokenResponse;
import veritrans.co.id.mobile.sdk.vtexceptions.VTBodyNotCompleteException;
import veritrans.co.id.mobile.sdk.vtexceptions.VTConnectionException;
import veritrans.co.id.mobile.sdk.vtexceptions.VTMobileException;
import veritrans.co.id.mobile.sdk.vtexceptions.VTRestException;
import veritrans.co.id.mobile.sdk.vtexceptions.VTUrlNotRecognizedException;

import static veritrans.co.id.mobile.sdk.helper.VTLogger.LogLevel;

/**
 * Created by muhammadanis on 1/29/15.
 */
public class VTMobile {


    private static VTLogger logger = new VTLogger(VTMobile.class);

    public static void getAllProducts(IActionCallback<VTGetProductResponse,VTMobileException> callback) {
        VTHttpContainer httpContainer = new VTHttpContainer();
        httpContainer.setHttpMethod(VTHttpContainer.VTHttpMethod.POST);
        String payload = new Gson().toJson(new VTBaseRequest());
        httpContainer.setPayload(payload);
        new VTMobile().new CallAPIAsync<>(callback, httpContainer,VTGetProductResponse.class).execute(VTConstants.GetProductsEndpoint());
    }

    public static void charge(IActionCallback<VTChargeResponse,VTMobileException> callback, VTChargeRequest request){
        VTHttpContainer httpContainer = new VTHttpContainer();
        httpContainer.setHttpMethod(VTHttpContainer.VTHttpMethod.POST);
        String payload = new Gson().toJson(request);
        httpContainer.setPayload(payload);
        new VTMobile().new CallAPIAsync<>(callback,httpContainer,VTChargeResponse.class).execute(VTConstants.ChargeEndpoint());
    }

    public static void confirmTransaction(IActionCallback<VTConfirmTransactionResponse,VTMobileException> callback, VTConfirmTransactionRequest request){
        VTHttpContainer httpContainer = new VTHttpContainer();
        httpContainer.setHttpMethod(VTHttpContainer.VTHttpMethod.POST);
        String payload = new Gson().toJson(request);
        httpContainer.setPayload(payload);
        new VTMobile().new CallAPIAsync<>(callback,httpContainer,VTConfirmTransactionResponse.class).execute(VTConstants.ConfirmTransactionEndpoint());
    }

    public static void getToken(IActionCallback<VTTokenResponse,VTMobileException> callback, VTTokenRequest request){
        VTHttpContainer httpContainer = new VTHttpContainer();
        httpContainer.setHttpMethod(VTHttpContainer.VTHttpMethod.GET);
        new VTMobile().new CallAPIAsync<>(callback,httpContainer,VTTokenResponse.class).execute(request.getTokenUrl());
    }

    private class CallAPIAsync<T> extends  AsyncTask<String,Void,Object>{

        IActionCallback<T,VTMobileException> callback;
        VTHttpContainer httpContainer;
        Class<T> clazz;

        public CallAPIAsync(IActionCallback<T,VTMobileException> callback, VTHttpContainer httpContainer, Class<T> clazz){
            this.callback = callback;
            this.httpContainer = httpContainer;
            this.clazz = clazz;
        }

        @Override
        protected Object doInBackground(String... strings) {
            if(strings.length == 0) return new VTUrlNotRecognizedException("null");
            String url = strings[0];
            URI uri = URI.create(url);
            HttpRequestBase http;
            HttpClient httpClient = new DefaultHttpClient();
            String json = "";
            try{
                if(httpContainer.getHttpMethod() == VTHttpContainer.VTHttpMethod.GET){
                    http = new HttpGet(uri);
                }else{
                    http = new HttpPost(uri);
                    HttpPost tempHttp = (HttpPost)http;

                    if(httpContainer.getPayload() == null) return new VTBodyNotCompleteException();

                    http.setHeader("Content-Type", httpContainer.getContentType());
                    http.setHeader("Accept", httpContainer.getAccept());
                    logger.Log("Payload: " + httpContainer.getPayload(), LogLevel.DEBUG);
                    ((HttpPost)http).setEntity(new StringEntity(httpContainer.getPayload()));
                }
                HttpResponse response = httpClient.execute(http);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null){
                    //append server response in string
                    sb.append(line);
                }
                json = sb.toString();
                logger.Log(json, VTLogger.LogLevel.INFO);
                Gson gson = new Gson();
                //create T from json
                T result = gson.fromJson(json, clazz);
                if(result == null){
                    return new VTRestException(json);
                }
                return result;
            }catch (IOException e){
                e.printStackTrace();
                return new VTConnectionException(e);
            }catch (JsonParseException e){
                return new VTRestException(json,e);
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            if(o == null){
                callback.onError(new VTMobileException("It should not be here"));
            }
            if(o instanceof VTMobileException){
                callback.onError((VTMobileException)o);
            }else{
                callback.onSuccess((T)o);
            }
        }
    }

}

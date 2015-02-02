package veritrans.co.id.mobile.sdk;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import id.co.veritrans.android.api.VTUtil.VTConfig;
import veritrans.co.id.mobile.sdk.helper.Constants;
import veritrans.co.id.mobile.sdk.helper.VTHttpContainer;
import veritrans.co.id.mobile.sdk.helper.Logger;
import veritrans.co.id.mobile.sdk.interfaces.IActionCallback;
import veritrans.co.id.mobile.sdk.entity.VTProduct;
import veritrans.co.id.mobile.sdk.vtexceptions.VTBodyNotCompleteException;
import veritrans.co.id.mobile.sdk.vtexceptions.VTConnectionException;
import veritrans.co.id.mobile.sdk.vtexceptions.VTMobileException;
import veritrans.co.id.mobile.sdk.vtexceptions.VTRestException;
import veritrans.co.id.mobile.sdk.vtexceptions.VTUrlNotRecognizedException;

/**
 * Created by muhammadanis on 1/29/15.
 */
public class VTMobile {


    private static Logger logger = new Logger(VTMobile.class);

    public static void getAllProducts(IActionCallback<List<VTProduct>,VTMobileException> callback) {
        VTHttpContainer httpContainer = new VTHttpContainer();
        httpContainer.setHttpMethod(VTHttpContainer.VTHttpMethod.POST);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        try {
            nameValuePairs.add(new BasicNameValuePair("client_key", URLEncoder.encode(VTConfig.CLIENT_KEY,"UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            callback.onError(new VTMobileException("Wrong Client Key",e));
        }
        new VTMobile().new CallAPIAsync<>(callback, httpContainer).execute(Constants.GetProductsEndpoint);
    }

    class CallAPIAsync<T> extends  AsyncTask<String,Void,Object>{

        IActionCallback<T,VTMobileException> callback;
        VTHttpContainer httpContainer;

        public CallAPIAsync(IActionCallback<T,VTMobileException> callback, VTHttpContainer httpContainer){
            this.callback = callback;
            this.httpContainer = httpContainer;
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
                    if(httpContainer.getPayload() == null) return new VTBodyNotCompleteException();
                    ((HttpPost)http).setEntity(new UrlEncodedFormEntity(httpContainer.getPayload()));
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
                logger.Log(json, Logger.LogLevel.INFO);
                Gson gson = new Gson();
                //create T from json
                T result = gson.fromJson(json, new TypeToken<T>(){}.getType());
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

    class GetAllProductsAsync extends AsyncTask<String,Void,Object>{

        IActionCallback<List<VTProduct>,VTMobileException> callback;

        public GetAllProductsAsync(IActionCallback<List<VTProduct>,VTMobileException>  callback){
            this.callback = callback;
        }

        @Override
        protected Object doInBackground(String... strings) {
            if(strings.length == 0) return new VTUrlNotRecognizedException("null");

            String url = strings[0];
            URI uri = URI.create(url);
            HttpGet httpGet = new HttpGet(uri);
            HttpClient httpClient = new DefaultHttpClient();
            String json = "";
            try {
                HttpResponse response = httpClient.execute(httpGet);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));
                String line = null;
                StringBuilder sb = new StringBuilder();
                //read server response
                while((line = reader.readLine()) != null){
                    //append server response in string
                    sb.append(line);
                }
                json = sb.toString();
                Gson gson = new Gson();
                //create list of vtproduct from json
                List<VTProduct> products = gson.fromJson(json, new TypeToken<List<VTProduct>>(){}.getType());
                if(products == null){
                    return new VTRestException(json);
                }
                return products;

            } catch (IOException e) {
                e.printStackTrace();
                return new VTConnectionException(e);
            } catch (JsonParseException e){
                return new VTRestException(json,e);
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            if (o == null) callback.onError(new VTMobileException("It should not be here"));
            if(o instanceof VTMobileException){
                callback.onError((VTMobileException)o);
            }else{
                callback.onSuccess((List<VTProduct>)o);
            }
        }
    }


}

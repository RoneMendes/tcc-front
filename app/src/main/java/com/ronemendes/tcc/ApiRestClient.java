package com.ronemendes.tcc;

import android.util.Log;

import com.loopj.android.http.*;

//Classe da API REST
public class  ApiRestClient {
    private static final String BASE_URL = "https://iron-store.herokuapp.com/index.php/";
    //private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d("http",getAbsoluteUrl(url) );
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
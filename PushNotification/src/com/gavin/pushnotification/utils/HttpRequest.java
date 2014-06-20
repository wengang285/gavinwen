/**
 * @author gavinwen
 *
 */
package com.gavin.pushnotification.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;




public class HttpRequest{
	
	private static AsyncHttpClient client = null;
	
	
	private static AsyncHttpClient getHttpClient(){
		if(client==null){
			client = new AsyncHttpClient();
			//设置超时时间
			client.setTimeout(5000);
		}
		return client;
	}
	
	
	
	/**
     * get请求
     * 
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void get(String url, RequestParams params,
            AsyncHttpResponseHandler responseHandler) {
    	getHttpClient().get(url, params, responseHandler);
    }

    /**
     * get请求
     * 
     * @param url
     * @param responseHandler
     */
    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
    	getHttpClient().get(url, null, responseHandler);
    }

    /**
     * post请求
     * 
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void post(String url, RequestParams params,
            AsyncHttpResponseHandler responseHandler) {
    	getHttpClient().post(url, params, responseHandler);
    }

    /**
     * post请求
     * 
     * @param url
     * @param responseHandler
     */
    public static void post(String url, AsyncHttpResponseHandler responseHandler) {
    	getHttpClient().post(url, null, responseHandler);
    }

	

	
	
	
	
	
	
	
	
	
	
}
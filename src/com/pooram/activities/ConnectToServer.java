package com.pooram.activities;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;

public class ConnectToServer{
	
	private Context context = null;
	
	
	
	public ConnectToServer(Context context){
		this.context = context;
	}
	

	
	
	public String connectToServerBtGet(String url){
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		try {
		    HttpResponse response = httpclient.execute(httpget);
		    if(response != null) {
		        String line = "";
//		        InputStream inputstream = response.getEntity().getContent();
//		        line = convertStreamToString(inputstream);
		        line = new BasicResponseHandler().handleResponse(response);
		        return line;
//		        Toast.makeText(context, line, Toast.LENGTH_SHORT).show();
		    } else {
		        return "Unable to complete your request";
		    }
		} catch (ClientProtocolException e) {
			return "Caught ClientProtocolException";
//		    Toast.makeText(context, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			return "Caught IOException";
//		    Toast.makeText(context, "Caught IOException", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			return "Caught Exception";
//		    Toast.makeText(context, "Caught Exception", Toast.LENGTH_SHORT).show();
		}
	}


}

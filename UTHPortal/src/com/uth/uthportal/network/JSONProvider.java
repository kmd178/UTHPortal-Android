package com.uth.uthportal.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class JSONProvider extends AsyncTask<String, String, String>{
		
		@Override
		protected String doInBackground(String... uri) {
			HttpClient httpClient = new DefaultHttpClient(); //http client for the request
			HttpResponse wResponse; //httpResponse
			String responseStr = null; //the actual response string
			try {
				wResponse = httpClient.execute(new HttpGet(uri[0]));
				StatusLine sLine = wResponse.getStatusLine(); //get response status
				if(sLine.getStatusCode()== HttpStatus.SC_OK){
					//request was ok.
					ByteArrayOutputStream out = new ByteArrayOutputStream(); //create output stream
					wResponse.getEntity().writeTo(out); //write our response to the stream
					out.close();
					responseStr = out.toString();
				} 
				else {
					//something went wrong
					wResponse.getEntity().getContent().close();//close the connection
					throw new IOException(sLine.getReasonPhrase()); //throw the error status
				}
			} catch (ClientProtocolException e) {
	            //TODO Handle this
	        } catch (IOException e) {
	            //TODO Handle this
	        }			
			return responseStr;
		}
	 }


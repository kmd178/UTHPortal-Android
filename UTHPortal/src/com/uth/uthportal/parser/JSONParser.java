package com.uth.uthportal.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;


import com.uth.uthportal.collections.Course;

public class JSONParser {
	JSONObject jObj;
	public JSONParser(String JSONBuffer){
		//constructor
		try {
			jObj= new JSONObject(JSONBuffer);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Course parseCourse(){
		
		return null;
	}
	private Date parseDateString(String dateStr){
		//function to parse IsoDate
		SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); 
		Date result = null;
		try {  
		    result = format.parse(dateStr);     
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}

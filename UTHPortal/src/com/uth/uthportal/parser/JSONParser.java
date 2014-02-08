package com.uth.uthportal.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.uth.uthportal.collections.Announcement;
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
		try {
			int _id = jObj.getInt("id");
			String _name = jObj.getString("name");
			String _link = jObj.getString("link");
			JSONObject announcements = jObj.getJSONObject("announcements"); //get announcements JObject
			String _announceLink = announcements.getString("link"); //get the link
			JSONArray items = announcements.getJSONArray("items"); //get the underlying JArray
			List<Announcement> _announcements = new ArrayList<Announcement>(); //initialize the list
			for(int i=0; i < items.length();i++){
				JSONObject item = items.getJSONObject(i); //get one object from the array
				//get this objject's items
				String _date = item.getString("date");
				String _content = item.getString("content");
				//create announcement
				Announcement ann = new Announcement(parseDateString(_date),_content,false);
				//populate array
				_announcements.add(ann);
			}
			//process success, return course
			return new Course(_id,_link,_name,_announceLink,_announcements);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

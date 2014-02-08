package com.uth.uthportal.collections;

import java.util.Date;

public class Announcement {
	public Date date;
	public String content;
	public Boolean hasTime;
	public Announcement(Date _date,String _content, Boolean _hasTime){
		date=_date;
		content=_content;
		hasTime=_hasTime;
	}
}

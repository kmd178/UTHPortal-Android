package com.uth.uthportal.collections;

public class Course {
	String id;
	Announcement[] announcements; //aray of announcements
	public Course(String _id, Announcement[] _announcements){
		announcements=_announcements;
		id=_id;
	}
	
}

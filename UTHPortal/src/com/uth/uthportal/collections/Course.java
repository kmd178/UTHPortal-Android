package com.uth.uthportal.collections;

import java.util.List;

public class Course {
	int id;
	public String link;
	public String name;
	public String announceLink;
	public List<Announcement> announcements; //list of announcements
	public Course(int _id,String _link,String _name,String _announceLink, List<Announcement> _announcements){
		announcements=_announcements;
		id=_id;
		link = _link;
		name = _name;
		announceLink = _announceLink;
	}
	
}

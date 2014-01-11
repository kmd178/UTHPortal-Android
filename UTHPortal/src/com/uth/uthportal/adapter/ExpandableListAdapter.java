package com.uth.uthportal.adapter;

import java.util.HashMap;
import java.util.List;

import com.uth.uthportal.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	
	private Context _context;
	private List<String> _listParentData; //the Parent elements
	private HashMap<String,List<String>> _listChildData; //the child elements
	// mapped like:   parent -> children.
	//constructor.!
	public ExpandableListAdapter(Context context, List<String> listParentData,
			HashMap<String,List<String>> listChildData){
		// we just set the class' properties through the constructor
		this._context = context;
		this._listParentData = listParentData;
		this._listChildData = listChildData;
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this._listChildData
				.get(this._listParentData
				.get(groupPosition))// <- here we have the list of children for that parent
				.get(childPosition); // <- we get the child we are interested in.
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		final String childText = (String) getChild(groupPosition, childPosition);
		
		if (convertView == null){
			//if there's no view, we get the inflater and inflate the view.
			LayoutInflater lInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = lInflater.inflate(R.layout.list_sub_item,null);
		}
		//get the label we added in the layout.
		TextView textListChild = (TextView) convertView
				.findViewById(R.id.labelListChild);
		
		textListChild.setText(childText);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listChildData.get(this._listParentData
				.get(groupPosition)) //get the parent.
				.size(); //get its size.
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listParentData.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listParentData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String parentTitle = (String) getGroup(groupPosition);
		
		if(convertView == null){
			// again same stuff
			LayoutInflater lInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = lInflater.inflate(R.layout.list_parent_item, null);
		}
		TextView labelListParent = (TextView) convertView
				.findViewById(R.id.labelListParent);
		labelListParent.setTypeface(null, Typeface.BOLD);
		labelListParent.setText(parentTitle);
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}

package com.uth.uthportal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.uth.uthportal.adapter.ExpandableListAdapter;
import com.uth.uthportal.adapter.TabsPagerAdapter;
import com.uth.uthportal.collections.Course;
import com.uth.uthportal.network.JSONProvider;
import com.uth.uthportal.parser.JSONParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class MainScreen extends FragmentActivity implements android.app.ActionBar.TabListener{
	//tab stuff.
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	private String[] tabs ={"Courses","Department","University"};
	//!----------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);

		 // Initializations
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        //use actionbar instead of tabhost.
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        //add the tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
        //listener to make a tab selected when the view is switched by swiping.
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         
            @Override
            public void onPageSelected(int position) {
            	//make the tabitem selected.
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
         
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// iflate the menu.
		//TODO: design the menu.
		getMenuInflater().inflate(R.menu.main_screen, menu);
		return true;
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		 viewPager.setCurrentItem(tab.getPosition());
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	//just for testing
	public void onBtnClicked(View v){
		AsyncTask<String, String, String> a =new JSONProvider().execute("http://192.168.1.4:8000/test");
		Course c;
		try {
			//
			JSONParser jParser = new JSONParser(a.get());
			 c = jParser.parseCourse();
			//Toast.makeText(this, c.name +"\n"+ c.announcements.get(0).content, Toast.LENGTH_LONG).show();
			 ExpandableListView expListView = (ExpandableListView) findViewById(R.id.coursesList);
			 List<String> listParent = new ArrayList<String>();
			 HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();
			 listParent.add(c.name); //add class as parent
			 for(int i=0;i<listParent.size();i++){
				 List<String> contents = new ArrayList<String>();
				 for(int j=0;j<c.announcements.size();j++){
					contents.add(c.announcements.get(j).content); //add the content of each announcement to the list
			 	}
				listDataChild.put(listParent.get(i),contents); //map the contents of announcements to i parent
			 }
			 ExpandableListAdapter lAdapter = new ExpandableListAdapter(this, listParent,listDataChild);
			 expListView.setAdapter(lAdapter);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	
	
	}
	


}

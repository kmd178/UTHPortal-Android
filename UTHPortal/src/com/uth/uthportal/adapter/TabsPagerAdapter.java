package com.uth.uthportal.adapter;
import com.uth.uthportal.CoursesFragment;
import com.uth.uthportal.DepartmentFragment;
import com.uth.uthportal.UniversityFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
public class TabsPagerAdapter extends FragmentPagerAdapter{

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		switch(arg0){
		case 0:
			return  new CoursesFragment();
		case 1:
			return  new DepartmentFragment();
		case 2:
			return new UniversityFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		//tabs count
		return 3;
	}
	
}

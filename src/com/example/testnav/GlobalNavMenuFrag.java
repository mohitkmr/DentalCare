package com.example.testnav;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class GlobalNavMenuFrag extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.global_nav_menu, container, false);
		String menuList[]={"Your Problems","ask us","Send Message"};

		ListView menuListView = (ListView) view.findViewById(R.id.menu_list);
		ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, menuList);
		
		menuListView.setAdapter(menuAdapter);
		return view;
	}

}

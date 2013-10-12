package com.care.dental;

import com.care.dental.events.NavMenuEvent;
import com.care.dental.events.ToggleNavigation;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GlobalNavMenuFrag extends Fragment implements OnItemClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.global_nav_menu, container, false);
		String menuList[];
		menuList = getResources().getStringArray(R.array.nav_menu);
		if (menuList != null) {
			ListView menuListView = (ListView) view
					.findViewById(R.id.menu_list);
			ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(
					getActivity(), android.R.layout.simple_list_item_1,
					menuList);

			menuListView.setAdapter(menuAdapter);
			menuListView.setOnItemClickListener(this);

		}

		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		DentalCareApplication.getEventBus().post(
				new NavMenuEvent("About Doctor"));
		DentalCareApplication.getEventBus().post(new ToggleNavigation());

	}
}
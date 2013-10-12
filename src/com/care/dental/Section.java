package com.care.dental;

import com.care.dental.about.AboutTheDoctor;
import com.care.dental.events.NavMenuEvent;
import com.squareup.otto.Subscribe;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Section extends Fragment {

	int DEFAULT_TAB = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.section_frag, container, false);
		DentalCareApplication.getEventBus().register(this);
		return view;
	}

	@Subscribe
	public void onNavMenuItemSelected(NavMenuEvent event) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment sectionFrag = new AboutTheDoctor();
		ft.replace(R.id.section_container, sectionFrag);
		ft.commit();

	}

}

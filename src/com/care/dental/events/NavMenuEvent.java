package com.care.dental.events;

public class NavMenuEvent {
	
	private String mNavItem;
	
	public NavMenuEvent(String menu){
		mNavItem = menu;
	}
	
	public String getNavMenu(){
		return mNavItem;
	}

}

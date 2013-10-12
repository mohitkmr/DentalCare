package com.care.dental;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import android.app.Application;

public class DentalCareApplication extends Application {

	private static Bus mEventBus;

	@Override
	public void onCreate() {
		super.onCreate();
		mEventBus = new Bus(ThreadEnforcer.ANY);
	}

	public static Bus getEventBus() {
		return mEventBus;
	}
}

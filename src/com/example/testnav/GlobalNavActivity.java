package com.example.testnav;
//this is test
//test 3
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GlobalNavActivity extends Activity implements OnClickListener {
	private RelativeLayout  mNavMenuContainer;
	private LinearLayout mSectionContainer;
	private Button mNavButton;
	private GlobalNavAnimation mGlobalNavAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.global_nav_activity);

		mSectionContainer = (LinearLayout) findViewById(R.id.section_layout);
		mNavMenuContainer = (RelativeLayout) findViewById(R.id.menu_frag_container_holder);
		mNavButton = (Button) findViewById(R.id.nav_button);
		mGlobalNavAnimation = new GlobalNavAnimation(this);

		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment menuFrag = new GlobalNavMenuFrag();
		ft.add(R.id.menu_frag_container, menuFrag);
		
		Fragment sectionFrag = new Section();
		ft.add(R.id.section_container, sectionFrag);
		ft.commit();

		mNavButton.setOnClickListener(this);
		initializeAnimation();
	}

	private void initializeAnimation() {

		final ViewTreeObserver navMenuObserver = mNavMenuContainer
				.getViewTreeObserver();

		navMenuObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {

				DisplayMetrics displayMetrics = getResources()
						.getDisplayMetrics();

				int deviceWidth = displayMetrics.widthPixels;

				int navMenuLayoutWidth = (deviceWidth * 80) / 100;

				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
						navMenuLayoutWidth,
						RelativeLayout.LayoutParams.MATCH_PARENT);

				mNavMenuContainer.setLayoutParams(params);

				mGlobalNavAnimation
						.initializeFilterAnimations(mNavMenuContainer);

			}
		});

		final ViewTreeObserver sectionObserver = mSectionContainer
				.getViewTreeObserver();

		sectionObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {

				mGlobalNavAnimation
						.initializeOtherAnimations(mSectionContainer);
			}
		});

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.nav_button:
			mGlobalNavAnimation.toggleSliding();
			break;
		}
	}
}
package com.care.dental;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GlobalNavAnimation implements AnimationListener {
	Context mContext;

	private RelativeLayout menuContainer;
	LinearLayout sectionContainer;

	private Animation menuContainerSlideIn, menuContainerSlideOut,
			sectionContainerSlideIn, sectionContainerSlideOut;

	private static int sectionContainerLayoutWidth,
			sectionContainerLayoutHeight;

	private boolean isSectionContainerSlideOut = false;

	private int deviceWidth;

	private int margin;

	public GlobalNavAnimation(Context context) {
		this.mContext = context;

		DisplayMetrics displayMetrics = context.getResources()
				.getDisplayMetrics();

		deviceWidth = displayMetrics.widthPixels; // as my animation is x-axis
													// related so i gets the
													// device width and will use
													// that width,so that this
													// sliding menu will work
													// fine in all screen
													// resolutions
	}

	public void initializeFilterAnimations(RelativeLayout menuContainer) {
		this.menuContainer = menuContainer;

		menuContainerSlideIn = AnimationUtils.loadAnimation(mContext,
				R.anim.filter_slide_in);

		menuContainerSlideOut = AnimationUtils.loadAnimation(mContext,
				R.anim.filter_slide_out);

	}

	public void initializeOtherAnimations(LinearLayout sectionContainer) {
		this.sectionContainer = sectionContainer;

		sectionContainerLayoutWidth = sectionContainer.getWidth();

		sectionContainerLayoutHeight = sectionContainer.getHeight();

		sectionContainerSlideIn = AnimationUtils.loadAnimation(mContext,
				R.anim.other_slide_in);
		sectionContainerSlideIn.setAnimationListener(this);

		sectionContainerSlideOut = AnimationUtils.loadAnimation(mContext,
				R.anim.other_slide_out);
		sectionContainerSlideOut.setAnimationListener(this);
	}

	public void toggleSliding() {
		if (isSectionContainerSlideOut) // check if findLayout is already slided
										// out so get so animate it back to
										// initial position
		{

			menuContainer.startAnimation(menuContainerSlideOut);

			menuContainer.setVisibility(View.INVISIBLE);

			sectionContainer.startAnimation(sectionContainerSlideIn);

		} else // slide findLayout Out and filterLayout In
		{
			sectionContainer.startAnimation(sectionContainerSlideOut);

			menuContainer.setVisibility(View.VISIBLE);

			menuContainer.startAnimation(menuContainerSlideIn);
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (isSectionContainerSlideOut) // Now here we will actually move our
										// view to the new position,because
										// animations just move the pixels not
										// the view
		{
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					sectionContainerLayoutWidth, sectionContainerLayoutHeight);

			sectionContainer.setLayoutParams(params);

			isSectionContainerSlideOut = false;
		} else {
			margin = (deviceWidth * 80) / 100; // here im coverting device
												// percentage width into pixels,
												// in my other_slide_in.xml or
												// other_slide_out.xml you can
												// see that i have set the
												// android:toXDelta="80%",so it
												// means the layout will move to
												// 80% of the device screen,to
												// work across all screens i
												// have converted percentage
												// width into pixels and then
												// used it

			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					sectionContainerLayoutWidth, sectionContainerLayoutHeight);

			params.leftMargin = margin;

			params.rightMargin = -margin; // same margin from right side
											// (negavite) so that our layout
											// won't get shrink

			sectionContainer.setLayoutParams(params);

			isSectionContainerSlideOut = true;

			dimOtherLayout();
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {

	}

	@Override
	public void onAnimationStart(Animation animation) {

	}

	private void dimOtherLayout() {
		AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.5f);

		alphaAnimation.setFillAfter(true);

		sectionContainer.startAnimation(alphaAnimation);
	}

}
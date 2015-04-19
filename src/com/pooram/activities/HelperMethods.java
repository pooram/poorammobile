package com.pooram.activities;

import java.util.HashMap;
import java.util.List;

import android.content.Context;

import com.pooram.library.foldablelayout.items.Painting;
import com.pooram.library.slider.SliderLayout;
import com.pooram.library.slider.Animations.DescriptionAnimation;
import com.pooram.library.slider.SliderTypes.BaseSliderView;
import com.pooram.library.slider.SliderTypes.TextSliderView;
import com.pooram.library.slider.SliderTypes.BaseSliderView.OnSliderClickListener;

public class HelperMethods {
	
	
	
	public void sliderInitialize(Context context,SliderLayout mDemoSlider ,List<Painting> url_maps){
		
		for (Painting iterable_element : url_maps) {
			TextSliderView textSliderView = new TextSliderView(context);
			textSliderView.description(iterable_element.getDescription()).image(iterable_element.getLocation())
			.setScaleType(BaseSliderView.ScaleType.FitCenterCrop)
			.setOnSliderClickListener((OnSliderClickListener) context);
			textSliderView.getBundle().putString("extra", iterable_element.getTitle());
			textSliderView.getBundle().putString("sliderType", iterable_element.getSliderType());
			mDemoSlider.addSlider(textSliderView);
		}
		/*
		for (String name : url_maps.keySet()) {
			TextSliderView textSliderView = new TextSliderView(context);
			// initialize a SliderLayout
			textSliderView.description(name).image(url_maps.get(name))
					.setScaleType(BaseSliderView.ScaleType.Fit)
					.setOnSliderClickListener((OnSliderClickListener) context);

			// add your extra information
			textSliderView.getBundle().putString("extra", name);

			mDemoSlider.addSlider(textSliderView);
		}
		*/
		mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
		mDemoSlider
				.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
		mDemoSlider.setCustomAnimation(new DescriptionAnimation());
		mDemoSlider.setDuration(4000);
	}

}

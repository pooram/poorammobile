package com.pooram.activities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pooram.library.foldablelayout.items.Painting;
import com.pooram.library.slider.SliderLayout;
import com.pooram.library.slider.SliderTypes.BaseSliderView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;

public class GalleryView extends BaseActivity  implements
BaseSliderView.OnSliderClickListener {
	
	
	private List<Painting> eventImages;
	private List<Painting> images;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.gallery_view);
		HelperMethods helperMethods = new HelperMethods();
		SliderLayout eventImageSlider = (SliderLayout) findViewById(R.id.gallery_slider);
		eventImages = SysytemConstants.eventImagesPassingArray;
//		eventImages = (getIntent().getSerializableExtra("eventImages"));
//		images = new ArrayList<Painting>();
//		DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra("data");
//		List<Painting> list = dw.getParliaments();
//		for(int i=0; i<eventImages.size();i++){
//			 eventImages.get(i).
//		}
		helperMethods.sliderInitialize(GalleryView.this, eventImageSlider, eventImages);
		
	}


	@Override
	public void onSliderClick(BaseSliderView slider) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
	
}

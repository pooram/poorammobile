package com.pooram.library.foldablelayout.items;

import android.content.res.Resources;
import android.content.res.TypedArray;
import com.pooram.activities.R;

public class Painting {

    private final int imageId;
    private final String title;
    private final String location;
    private final String description;
    private final String sliderType;

    public Painting(int imageId, String title, String location, String desc, String type) {
        this.imageId = imageId;
        this.title = title;
        this.location = location;
        this.description = desc;
        this.sliderType = type;
    }
    
    public Painting(int imageId, String title, String location, String desc) {
        this.imageId = imageId;
        this.title = title;
        this.location = location;
        this.description = desc;
        this.sliderType = "";
    }
    
    public Painting(int imageId, String title, String location) {
        this.imageId = imageId;
        this.title = title;
        this.location = location;
        this.description = "";
        this.sliderType = "";
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription(){
    	return description;
    }

    public String getLocation() {
        return location;
    }
    

    public String getSliderType() {
		return sliderType;
	}

	public static Painting[] getAllPaintings(Resources res) {
        String[] titles = res.getStringArray(R.array.paintings_titles);
        String[] years = res.getStringArray(R.array.paintings_years);
        String[] locations = res.getStringArray(R.array.paintings_locations);
        TypedArray images = res.obtainTypedArray(R.array.paintings_images);

        int size = titles.length;
        Painting[] paintings = new Painting[size];

        for (int i = 0; i < size; i++) {
            paintings[i] = new Painting(images.getResourceId(i, -1), titles[i], locations[i]);
        }

        return paintings;
    }

}

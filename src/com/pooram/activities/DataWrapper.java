package com.pooram.activities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pooram.library.foldablelayout.items.Painting;

public class DataWrapper implements Serializable {

	   private List<Painting> parliaments;

	   public DataWrapper(List<Painting> data) {
	      this.parliaments = data;
	   }

	   public List<Painting> getParliaments() {
	      return this.parliaments;
	   }

	}
package com.lego.racers.file.lsb.object;

import java.util.Arrays;

public class LoadingScreen{

	private String mapImage;
	private Icon[] icons;
	private int titleIndex;

	public String getMapImage(){
		return this.mapImage;
	}

	public void setMapImage(String mapImage){
		this.mapImage = mapImage;
	}

	public Icon[] getIcons(){
		return this.icons;
	}

	public void setIcons(Icon[] icons){
		this.icons = icons;
	}

	public int getTitleIndex(){
		return this.titleIndex;
	}

	public void setTitleIndex(int titleIndex){
		this.titleIndex = titleIndex;
	}

	@Override
	public String toString() {
		return "LoadingScreen{" +
				"mapImage='" + mapImage + '\'' +
				", icons=" + Arrays.toString(icons) +
				", titleIndex=" + titleIndex +
				'}';
	}

}
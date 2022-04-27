package com.lego.racers.file.mib.object;

public class Image extends Item{

	private String x28;

	public String getX28(){
		return this.x28;
	}

	public void setX28(String x28){
		this.x28 = x28;
	}

	@Override
	public String toString() {
		return "Image{" +
				"x28='" + x28 + '\'' +
				", positioning=" + positioning +
				'}';
	}

}
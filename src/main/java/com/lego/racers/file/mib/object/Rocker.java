package com.lego.racers.file.mib.object;

public class Rocker{

	private String x28;
	private Arrow arrow;

	public String getX28(){
		return this.x28;
	}

	public void setX28(String x28){
		this.x28 = x28;
	}

	public Arrow getArrow(){
		return this.arrow;
	}

	public void setArrow(Arrow arrow){
		this.arrow = arrow;
	}

	@Override
	public String toString() {
		return "Rocker{" +
				"x28='" + x28 + '\'' +
				", arrow=" + arrow +
				'}';
	}

}
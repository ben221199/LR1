package com.lego.racers.file.lsb.object;

public class Icon{

	private float xFraction;
	private float yFraction;

	public float getXFraction(){
		return this.xFraction;
	}

	public void setXFraction(float xFraction){
		this.xFraction = xFraction;
	}

	public float getYFraction(){
		return this.yFraction;
	}

	public void setYFraction(float yFraction){
		this.yFraction = yFraction;
	}

	@Override
	public String toString() {
		return "Icon{" +
				"xFraction=" + xFraction +
				", yFraction=" + yFraction +
				'}';
	}
}
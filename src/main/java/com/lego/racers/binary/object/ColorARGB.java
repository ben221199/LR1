package com.lego.racers.binary.object;

public class ColorARGB{

	private int alpha;
	private int red;
	private int green;
	private int blue;

	public int getAlpha(){
		return this.alpha;
	}

	public int getRed(){
		return this.red;
	}

	public int getGreen(){
		return this.green;
	}

	public int getBlue(){
		return this.blue;
	}

	public void setAlpha(int alpha){
		this.alpha = alpha;
	}

	public void setRed(int red){
		this.red = red;
	}

	public void setGreen(int green){
		this.green = green;
	}

	public void setBlue(int blue){
		this.blue = blue;
	}

	@Override
	public String toString() {
		return "ColorARGB{" +
				"alpha=" + alpha +
				", red=" + red +
				", green=" + green +
				", blue=" + blue +
				'}';
	}

}
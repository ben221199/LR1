package com.lego.racers.file.tdb.object;

public class TransparentColor{

	private int red;
	private int green;
	private int blue;

	public int getRed(){
		return this.red;
	}

	public int getGreen(){
		return this.green;
	}

	public int getBlue(){
		return this.blue;
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
		return "TransparentColor{" +
				"red=" + red +
				", green=" + green +
				", blue=" + blue +
				'}';
	}

}
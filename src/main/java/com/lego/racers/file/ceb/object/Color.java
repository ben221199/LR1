package com.lego.racers.file.ceb.object;

public class Color{

	protected int red;
	protected int green;
	protected int blue;

	public int getRed() {
		return this.red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return this.green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return this.blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	@Override
	public String toString() {
		return "Color{" +
				"red=" + red +
				", green=" + green +
				", blue=" + blue +
				'}';
	}
}
package com.lego.racers.file.mdb.object;

public abstract class Color{

	protected int red;
	protected int green;
	protected int blue;
	protected int alpha;

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

	public int getAlpha() {
		return this.alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	@Override
	public String toString() {
		return "Color{" +
				"red=" + red +
				", green=" + green +
				", blue=" + blue +
				", alpha=" + alpha +
				'}';
	}

}
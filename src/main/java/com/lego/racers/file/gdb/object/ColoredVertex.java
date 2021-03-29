package com.lego.racers.file.gdb.object;

public class ColoredVertex extends Vertex implements ColoredVertexInterface{

	private byte red;
	private byte green;
	private byte blue;
	private byte alpha;

	public byte getRed() {
		return this.red;
	}

	public void setRed(byte red) {
		this.red = red;
	}

	public byte getGreen() {
		return this.green;
	}

	public void setGreen(byte green) {
		this.green = green;
	}

	public byte getBlue() {
		return this.blue;
	}

	public void setBlue(byte blue) {
		this.blue = blue;
	}

	public byte getAlpha() {
		return this.alpha;
	}

	public void setAlpha(byte alpha) {
		this.alpha = alpha;
	}

	@Override
	public String toString() {
		return "ColoredVertex{" +
				"red=" + red +
				", green=" + green +
				", blue=" + blue +
				", alpha=" + alpha +
				", x=" + x +
				", y=" + y +
				", z=" + z +
				", tu=" + tu +
				", tv=" + tv +
				'}';
	}

}
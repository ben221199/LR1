package com.lego.racers.file.leb.object;

public class TextureTuple {

	private int u;
	private int v;

	public int getU() {
		return this.u;
	}

	public void setU(int u) {
		this.u = u;
	}

	public int getV() {
		return this.v;
	}

	public void setV(int v) {
		this.v = v;
	}

	@Override
	public String toString() {
		return "TextureTuple{" +
				"u=" + u +
				", v=" + v +
				'}';
	}

}
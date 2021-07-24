package com.lego.racers.file.leb.object;

public abstract class Triangle{

	protected int type;

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Triangle{" +
				"type=" + type +
				'}';
	}

}
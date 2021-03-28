package com.lego.racers.file.cpb.object;

public class Location {

	private float x;
	private float y;
	private float z;

	public float getX() {
		return this.x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return this.y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return this.z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Location{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}

}
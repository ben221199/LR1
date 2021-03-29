package com.lego.racers.file.gdb.object;

public abstract class Vertex{

	protected float x;
	protected float y;
	protected float z;
	protected float tu;
	protected float tv;

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

	public float getTU() {
		return this.tu;
	}

	public void setTU(float tu) {
		this.tu = tu;
	}

	public float getTV() {
		return this.tv;
	}

	public void setTV(float tv) {
		this.tv = tv;
	}

	@Override
	public String toString() {
		return "Vertex{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				", tu=" + tu +
				", tv=" + tv +
				'}';
	}

}
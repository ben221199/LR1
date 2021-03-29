package com.lego.racers.file.gdb.object;

public class NormalVertex extends Vertex{

	private float nx;
	private float ny;
	private float nz;

	public float getNX() {
		return this.nx;
	}

	public void setNX(float nx) {
		this.nx = nx;
	}

	public float getNY() {
		return this.ny;
	}

	public void setNY(float ny) {
		this.ny = ny;
	}

	public float getNZ() {
		return this.nz;
	}

	public void setNZ(float nz) {
		this.nz = nz;
	}

	@Override
	public String toString() {
		return "NormalVertex{" +
				"nx=" + nx +
				", ny=" + ny +
				", nz=" + nz +
				", x=" + x +
				", y=" + y +
				", z=" + z +
				", tu=" + tu +
				", tv=" + tv +
				'}';
	}

}
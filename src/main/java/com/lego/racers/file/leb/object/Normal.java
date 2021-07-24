package com.lego.racers.file.leb.object;

public class Normal {

	private float nx;
	private float ny;
	private float nz;

	public Normal(){}

	public Normal(float nx, float ny, float nz){
		this.nx = nx;
		this.ny = ny;
		this.nz = nz;
	}

	public float getNX(){
		return this.nx;
	}

	public float getNY(){
		return this.ny;
	}

	public float getNZ(){
		return this.nz;
	}

	public void setNX(float nx){
		this.nx = nx;
	}

	public void setNY(float ny){
		this.ny = ny;
	}

	public void setNZ(float nz){
		this.nz = nz;
	}

	@Override
	public String toString() {
		return "Normal{" +
				"nx=" + nx +
				", ny=" + ny +
				", nz=" + nz +
				'}';
	}

}
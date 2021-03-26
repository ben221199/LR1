package com.lego.racers.file.pwb.object;

public class Position{

	private float x;
	private float y;
	private float z;

	public Position(){}

	public Position(float x,float y,float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX(){
		return this.x;
	}

	public float getY(){
		return this.y;
	}

	public float getZ(){
		return this.z;
	}

	public void setX(float x){
		this.x = x;
	}

	public void setY(float y){
		this.y = y;
	}

	public void setZ(float z){
		this.z = z;
	}

	@Override
	public String toString() {
		return "Position{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}

}
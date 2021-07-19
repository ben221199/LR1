package com.lego.racers.file.cdb.object;

public class Direction{

	private float x;
	private float y;
	private float z;

	public Direction(){}

	public Direction(float x, float y, float z){
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
		return "Direction{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}

}
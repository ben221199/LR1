package com.lego.racers.file.sdb.object;

public class Rotation{

	private float v1;
	private float v2;
	private float v3;
	private float v4;

	public float getV1(){
		return this.v1;
	}

	public void setV1(float v1){
		this.v1 = v1;
	}

	public float getV2(){
		return this.v2;
	}

	public void setV2(float v2){
		this.v2 = v2;
	}

	public float getV3(){
		return this.v3;
	}

	public void setV3(float v3){
		this.v3 = v3;
	}

	public float getV4(){
		return this.v4;
	}

	public void setV4(float v4){
		this.v4 = v4;
	}

	@Override
	public String toString(){
		return "Rotation{" +
				"v1=" + v1 +
				", v2=" + v2 +
				", v3=" + v3 +
				", v4=" + v4 +
				'}';
	}

}
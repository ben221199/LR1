package com.lego.racers.file.ghb.object;

public class Orientation{

	private float f1;
	private float f2;
	private float f3;
	private float f4;

	public float getF1(){
		return this.f1;
	}

	public void setF1(float f1){
		this.f1 = f1;
	}

	public float getF2(){
		return this.f2;
	}

	public void setF2(float f2){
		this.f2 = f2;
	}

	public float getF3(){
		return this.f3;
	}

	public void setF3(float f3){
		this.f3 = f3;
	}

	public float getF4(){
		return this.f4;
	}

	public void setF4(float f4){
		this.f4 = f4;
	}

	@Override
	public String toString() {
		return "Orientation{" +
				"f1=" + f1 +
				", f2=" + f2 +
				", f3=" + f3 +
				", f4=" + f4 +
				'}';
	}

}
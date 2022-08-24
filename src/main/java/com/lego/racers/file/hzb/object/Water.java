package com.lego.racers.file.hzb.object;

public class Water extends Hazard {

	private String name;
	private Float f1;
	private Float f2;

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Float getF1(){
		return this.f1;
	}

	public void setF1(Float f1){
		this.f1 = f1;
	}

	public Float getF2(){
		return this.f2;
	}

	public void setF2(Float f2){
		this.f2 = f2;
	}

	@Override
	public String toString() {
		return "Water{" +
				"name='" + name + '\'' +
				", f1=" + f1 +
				", f2=" + f2 +
				'}';
	}

}
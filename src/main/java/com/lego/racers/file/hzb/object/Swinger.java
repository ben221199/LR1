package com.lego.racers.file.hzb.object;

public class Swinger extends Hazard{

	private String s1;
	private Integer i1;
	private Float f1;
	private Float f2;
	private Float f3;
	private Float f4;

	public String getS1(){
		return this.s1;
	}

	public void setS1(String s1){
		this.s1 = s1;
	}

	public Integer getI1(){
		return this.i1;
	}

	public void setI1(Integer i1){
		this.i1 = i1;
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

	public Float getF3(){
		return this.f3;
	}

	public void setF3(Float f3){
		this.f3 = f3;
	}

	public Float getF4(){
		return this.f4;
	}

	public void setF4(Float f4){
		this.f4 = f4;
	}

	@Override
	public String toString(){
		return "Swinger{" +
				"s1='" + s1 + '\'' +
				", i1=" + i1 +
				", f1=" + f1 +
				", f2=" + f2 +
				", f3=" + f3 +
				", f4=" + f4 +
				'}';
	}
	
}
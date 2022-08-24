package com.lego.racers.file.hzb.object;

import com.lego.racers.binary.object.Position;

public class Laser extends Hazard{

	private Position position;
	private String name;
	private Integer i1;
	private Integer i2;
	private Float f1;
	private Float f2;
	private Float f3;

	public Position getPosition(){
		return this.position;
	}

	public void setPosition(Position position){
		this.position = position;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Integer getI1(){
		return this.i1;
	}

	public void setI1(Integer i1){
		this.i1 = i1;
	}

	public Integer getI2(){
		return this.i2;
	}

	public void setI2(Integer i2){
		this.i2 = i2;
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

	@Override
	public String toString() {
		return "Laser{" +
				"position=" + position +
				", name='" + name + '\'' +
				", i1=" + i1 +
				", i2=" + i2 +
				", f1=" + f1 +
				", f2=" + f2 +
				", f3=" + f3 +
				'}';
	}

}
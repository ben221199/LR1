package com.lego.racers.file.hzb.object;

import com.lego.racers.binary.object.Position;

public class Cannon extends Hazard {

	private Position p1;
	private Position p2;
	private Position p3;
	private Float f1;
	private Integer i1;

	public Position getP1(){
		return this.p1;
	}

	public void setP1(Position p1){
		this.p1 = p1;
	}

	public Position getP2(){
		return this.p2;
	}

	public void setP2(Position p2){
		this.p2 = p2;
	}

	public Position getP3(){
		return this.p3;
	}

	public void setP3(Position p3){
		this.p3 = p3;
	}

	public Float getF1(){
		return this.f1;
	}

	public void setF1(Float f1){
		this.f1 = f1;
	}

	public Integer getI1(){
		return this.i1;
	}

	public void setI1(Integer i1){
		this.i1 = i1;
	}

	@Override
	public String toString(){
		return "Cannon{" +
				"p1=" + p1 +
				", p2=" + p2 +
				", p3=" + p3 +
				", f1=" + f1 +
				", i1=" + i1 +
				'}';
	}

}
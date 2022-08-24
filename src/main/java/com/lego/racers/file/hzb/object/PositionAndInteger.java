package com.lego.racers.file.hzb.object;

import com.lego.racers.binary.object.Position;

public class PositionAndInteger {

	private Position position;
	private Integer i1;

	public Position getPosition(){
		return this.position;
	}

	public void setPosition(Position position){
		this.position = position;
	}

	public Integer getI1(){
		return this.i1;
	}

	public void setI1(Integer i1){
		this.i1 = i1;
	}

	@Override
	public String toString() {
		return "PositionAndInteger{" +
				"position=" + position +
				", i1=" + i1 +
				'}';
	}

}
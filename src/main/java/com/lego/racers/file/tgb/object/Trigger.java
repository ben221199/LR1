package com.lego.racers.file.tgb.object;

import com.lego.racers.binary.object.Position;

public class Trigger{

	private Position position;
	private Integer p41;

	public Position getPosition(){
		return this.position;
	}

	public void setPosition(Position position){
		this.position = position;
	}

	public Integer getP41(){
		return this.p41;
	}

	public void setP41(Integer p41){
		this.p41 = p41;
	}

	@Override
	public String toString() {
		return "Trigger{" +
				"position=" + position +
				", p41=" + p41 +
				'}';
	}

}
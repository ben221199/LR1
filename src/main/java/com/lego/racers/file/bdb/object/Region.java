package com.lego.racers.file.bdb.object;

import com.lego.racers.binary.object.Position;

public class Region{

	private Position bottomLeftFront;
	private Position upperRightBack;

	public Position getBottomLeftFront(){
		return this.bottomLeftFront;
	}

	public void setBottomLeftFront(Position bottomLeftFront){
		this.bottomLeftFront = bottomLeftFront;
	}

	public Position getUpperRightBack(){
		return this.upperRightBack;
	}

	public void setUpperRightBack(Position upperRightBack){
		this.upperRightBack = upperRightBack;
	}

	@Override
	public String toString(){
		return "Region{" +
				"bottomLeftFront=" + bottomLeftFront +
				", upperRightBack=" + upperRightBack +
				'}';
	}
	
}
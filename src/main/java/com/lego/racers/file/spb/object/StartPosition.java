package com.lego.racers.file.spb.object;

import com.lego.racers.binary.object.Position;

public class StartPosition{

	private Position position;
	private Orientation orientation;

	public StartPosition(){}

	public StartPosition(Position position,Orientation orientation){
		this.position = position;
		this.orientation = orientation;
	}

	public Position getPosition(){
		return this.position;
	}

	public void setPosition(Position position){
		this.position = position;
	}

	public Orientation getOrientation(){
		return this.orientation;
	}

	public void setOrientation(Orientation orientation){
		this.orientation = orientation;
	}

	@Override
	public String toString() {
		return "StartPosition{" +
				"position=" + position +
				", orientation=" + orientation +
				'}';
	}

}
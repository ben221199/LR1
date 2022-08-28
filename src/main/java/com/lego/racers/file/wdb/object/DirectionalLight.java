package com.lego.racers.file.wdb.object;

import com.lego.racers.file.cdb.object.Direction;

public class DirectionalLight extends Light{

	private Direction direction;

	public Direction getDirection(){
		return this.direction;
	}

	public void setDirection(Direction direction){
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "DirectionalLight{" +
				"direction=" + direction +
				", color=" + color +
				'}';
	}

}
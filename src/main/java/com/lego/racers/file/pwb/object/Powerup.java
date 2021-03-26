package com.lego.racers.file.pwb.object;

public abstract class Powerup{

	protected Position position;

	public Powerup(){}

	public Powerup(Position position){
		this.position = position;
	}

	public Position getPosition(){
		return this.position;
	}

	public void setPosition(Position position){
		this.position = position;
	}

	@Override
	public String toString() {
		return "Powerup{" +
				"position=" + position +
				'}';
	}

}
package com.lego.racers.file.cdb.object;

public class DirectionalLight extends Light{

	private Direction direction;
	private int strobeOn;
	private int strobeOff;

	public Direction getDirection(){
		return this.direction;
	}

	public void setDirection(Direction direction){
		this.direction = direction;
	}

	public int getStrobeOn(){
		return this.strobeOn;
	}

	public void setStrobeOn(int strobeOn){
		this.strobeOn = strobeOn;
	}

	public int getStrobeOff(){
		return this.strobeOff;
	}

	public void setStrobeOff(int strobeOff){
		this.strobeOff = strobeOff;
	}

	@Override
	public String toString(){
		return "DirectionalLight{" +
				"direction=" + direction +
				", strobeOn=" + strobeOn +
				", strobeOff=" + strobeOff +
				", startFrame=" + startFrame +
				", duration=" + duration +
				", color=" + color +
				'}';
	}
	
}
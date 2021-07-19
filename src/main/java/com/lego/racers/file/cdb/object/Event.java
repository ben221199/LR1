package com.lego.racers.file.cdb.object;

import com.lego.racers.binary.object.Position;

public class Event{

	private int startFrame;
	private int duration;
	private Position position;
	private Rotation rotation;

	public int getStartFrame(){
		return startFrame;
	}

	public void setStartFrame(int startFrame){
		this.startFrame = startFrame;
	}

	public int getDuration(){
		return duration;
	}

	public void setDuration(int duration){
		this.duration = duration;
	}

	public Position getPosition(){
		return position;
	}

	public void setPosition(Position position){
		this.position = position;
	}

	public Rotation getRotation(){
		return rotation;
	}

	public void setRotation(Rotation rotation){
		this.rotation = rotation;
	}

	@Override
	public String toString(){
		return "Event{" +
				"startFrame=" + startFrame +
				", duration=" + duration +
				", position=" + position +
				", rotation=" + rotation +
				'}';
	}
	
}
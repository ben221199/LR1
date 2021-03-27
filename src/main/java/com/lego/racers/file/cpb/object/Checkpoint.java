package com.lego.racers.file.cpb.object;

public class Checkpoint{

	private Direction direction;
	private float locationX;
	private float locationY;
	private float locationZ;
	private Timing timing;

	public Direction getDirection(){
		return this.direction;
	}

	public float getLocationX(){
		return this.locationX;
	}

	public float getLocationY(){
		return this.locationY;
	}

	public float getLocationZ(){
		return this.locationZ;
	}

	public Timing getTiming(){
		return this.timing;
	}

	public void setDirection(Direction direction){
		this.direction = direction;
	}

	public void setLocationX(float locationX){
		this.locationX = locationX;
	}

	public void setLocationY(float locationY){
		this.locationY = locationY;
	}

	public void setLocationZ(float locationZ){
		this.locationZ = locationZ;
	}

	public void setTiming(Timing timing){
		this.timing = timing;
	}

	@Override
	public String toString() {
		return "Checkpoint{" +
				"direction=" + direction +
				", locationX=" + locationX +
				", locationY=" + locationY +
				", locationZ=" + locationZ +
				", timing=" + timing +
				'}';
	}

}
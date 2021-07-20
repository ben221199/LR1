package com.lego.racers.file.mab.object;

public class Animation{

	private int frameOffset;
	private int frameLimit;

	private int frameCount;
	private int speed;

	public int getFrameOffset(){
		return this.frameOffset;
	}

	public void setFrameOffset(int frameOffset){
		this.frameOffset = frameOffset;
	}

	public int getFrameLimit(){
		return this.frameLimit;
	}

	public void setFrameLimit(int frameLimit){
		this.frameLimit = frameLimit;
	}

	public int getFrameCount(){
		return this.frameCount;
	}

	public void setFrameCount(int frameCount){
		this.frameCount = frameCount;
	}

	public int getSpeed(){
		return this.speed;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "Animation{" +
				"frameOffset=" + frameOffset +
				", frameLimit=" + frameLimit +
				", frameCount=" + frameCount +
				", speed=" + speed +
				'}';
	}

}
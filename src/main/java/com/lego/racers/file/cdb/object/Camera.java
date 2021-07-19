package com.lego.racers.file.cdb.object;

public class Camera{

	private String name;
	private int startFrame;
	private int duration;
	private int animationSequenceId;

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getStartFrame(){
		return this.startFrame;
	}

	public void setStartFrame(int startFrame){
		this.startFrame = startFrame;
	}

	public int getDuration(){
		return this.duration;
	}

	public void setDuration(int duration){
		this.duration = duration;
	}

	public int getAnimationSequenceId(){
		return this.animationSequenceId;
	}

	public void setAnimationSequenceId(int animationSequenceId){
		this.animationSequenceId = animationSequenceId;
	}

	@Override
	public String toString() {
		return "Camera{" +
				"name='" + name + '\'' +
				", startFrame=" + startFrame +
				", duration=" + duration +
				", animationSequenceId=" + animationSequenceId +
				'}';
	}

}
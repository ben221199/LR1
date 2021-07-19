package com.lego.racers.file.cdb.object;

public abstract class Light{

	protected int startFrame;
	protected int duration;
	protected Color color;

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

	public Color getColor(){
		return this.color;
	}

	public void setColor(Color color){
		this.color = color;
	}

	@Override
	public String toString(){
		return "Light{" +
				"startFrame=" + startFrame +
				", duration=" + duration +
				", color=" + color +
				'}';
	}

}
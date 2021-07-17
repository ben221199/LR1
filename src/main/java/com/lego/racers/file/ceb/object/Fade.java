package com.lego.racers.file.ceb.object;

public class Fade{

	private int duration;
	private boolean p98;
	private boolean fadeIn;
	private boolean fadeOut;
	private Color color;

	public int getDuration(){
		return this.duration;
	}

	public void setDuration(int duration){
		this.duration = duration;
	}

	public boolean getP98(){
		return this.p98;
	}

	public void setP98(boolean p98){
		this.p98 = p98;
	}

	public boolean isFadeIn(){
		return this.fadeIn;
	}

	public void setFadeIn(boolean fadeIn){
		this.fadeIn = fadeIn;
	}

	public boolean isFadeOut(){
		return this.fadeIn;
	}

	public void setFadeOut(boolean fadeOut){
		this.fadeOut = fadeOut;
	}

	public Color getColor(){
		return this.color;
	}

	public void setColor(Color color){
		this.color = color;
	}

	@Override
	public String toString() {
		return "Fade{" +
				"duration=" + duration +
				", p98=" + p98 +
				", fadeIn=" + fadeIn +
				", fadeOut=" + fadeOut +
				", color=" + color +
				'}';
	}

}
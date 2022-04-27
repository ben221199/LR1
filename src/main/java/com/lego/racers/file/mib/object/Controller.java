package com.lego.racers.file.mib.object;

public class Controller extends Item{

	private Integer x2C;
	private Integer x33;
	private String frame;
	private Arrow.Pair arrows;

	public Integer getX2C(){
		return this.x2C;
	}

	public void setX2C(Integer x2C){
		this.x2C = x2C;
	}

	public Integer getX33(){
		return this.x33;
	}

	public void setX33(Integer x33){
		this.x33 = x33;
	}

	public String getFrame(){
		return this.frame;
	}

	public void setFrame(String frame){
		this.frame = frame;
	}

	public Arrow.Pair getArrows(){
		return this.arrows;
	}

	public void setArrows(Arrow.Pair arrows){
		this.arrows = arrows;
	}

	@Override
	public String toString() {
		return "Controller{" +
				"x2C=" + x2C +
				", x33=" + x33 +
				", frame='" + frame + '\'' +
				", arrows=" + arrows +
				", positioning=" + positioning +
				'}';
	}

}
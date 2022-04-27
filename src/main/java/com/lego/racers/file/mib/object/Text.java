package com.lego.racers.file.mib.object;

public class Text extends Item{

	private Integer x33;

	public Integer getX33(){
		return this.x33;
	}

	public void setX33(Integer x33){
		this.x33 = x33;
	}

	@Override
	public String toString() {
		return "Text{" +
				"x33=" + x33 +
				", positioning=" + positioning +
				'}';
	}

}
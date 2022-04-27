package com.lego.racers.file.mib.object;

public class Button extends Item{

	private X2B x2B;
	private Integer x32;
	private Integer x33;

	public X2B getX2B(){
		return this.x2B;
	}

	public void setX2B(X2B x2B){
		this.x2B = x2B;
	}

	public Integer getX32(){
		return this.x32;
	}

	public void setX32(Integer x32){
		this.x32 = x32;
	}

	public Integer getX33(){
		return this.x33;
	}

	public void setX33(Integer x33){
		this.x33 = x33;
	}

	@Override
	public String toString() {
		return "Button{" +
				"x2B=" + x2B +
				", x32=" + x32 +
				", x33=" + x33 +
				", positioning=" + positioning +
				'}';
	}

}
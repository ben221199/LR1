package com.lego.racers.file.trb.object;

import com.lego.racers.binary.object.Position;

public class Trigger{

	private Position x29;
	private Float x2A;
	private Integer x2B;
	private String x2D;
	private Integer x2E;
	private boolean x2F;

	public Position getX29(){
		return this.x29;
	}

	public void setX29(Position x29){
		this.x29 = x29;
	}

	public Float getX2A(){
		return this.x2A;
	}

	public void setX2A(Float x2A){
		this.x2A = x2A;
	}

	public Integer getX2B(){
		return this.x2B;
	}

	public void setX2B(Integer x2B){
		this.x2B = x2B;
	}

	public String getX2D(){
		return this.x2D;
	}

	public void setX2D(String x2D){
		this.x2D = x2D;
	}

	public Integer getX2E(){
		return this.x2E;
	}

	public void setX2E(Integer x2E){
		this.x2E = x2E;
	}

	public boolean getX2F(){
		return this.x2F;
	}

	public void setX2F(boolean x2F){
		this.x2F = x2F;
	}

	@Override
	public String toString() {
		return "Trigger{" +
				"x29=" + x29 +
				", x2A=" + x2A +
				", x2B=" + x2B +
				", x2D='" + x2D + '\'' +
				", x2E=" + x2E +
				", x2F=" + x2F +
				'}';
	}

}
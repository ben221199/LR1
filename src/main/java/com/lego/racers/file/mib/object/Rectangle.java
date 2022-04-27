package com.lego.racers.file.mib.object;

public class Rectangle{

	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public int getX1(){
		return this.x1;
	}

	public void setX1(int x1){
		this.x1 = x1;
	}

	public int getY1(){
		return this.y1;
	}

	public void setY1(int y1){
		this.y1 = y1;
	}

	public int getX2(){
		return this.x2;
	}

	public void setX2(int x2){
		this.x2 = x2;
	}

	public int getY2(){
		return this.y2;
	}

	public void setY2(int y2){
		this.y2 = y2;
	}

	@Override
	public String toString() {
		return "Rect{" +
				"x1=" + x1 +
				", y1=" + y1 +
				", x2=" + x2 +
				", y2=" + y2 +
				'}';
	}

}
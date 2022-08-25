package com.lego.racers.file.cmb.object;

import com.lego.racers.binary.object.Position;

import java.util.Arrays;

public class Chassis{

	private Wheels wheels;
	private float[] x2A;
	private Position seat;
	private Float x2C;
	private float[] x2D;
	private float[] x2E;
	private Float x2F;
	private X30 x30;
	private X31 x31;
	private Integer x32;
	private Integer x33;
	private String bricks;
	private Integer x3A;
	private Integer x3B;
	private Integer x3C;

	public Wheels getWheels(){
		return this.wheels;
	}

	public void setWheels(Wheels wheels){
		this.wheels = wheels;
	}

	public float[] getX2A(){
		return this.x2A;
	}

	public void setX2A(float[] x2A){
		this.x2A = x2A;
	}

	public Position getSeat(){
		return this.seat;
	}

	public void setSeat(Position seat){
		this.seat = seat;
	}

	public Float getX2C(){
		return this.x2C;
	}

	public void setX2C(Float x2C){
		this.x2C = x2C;
	}

	public float[] getX2D(){
		return this.x2D;
	}

	public void setX2D(float[] x2D){
		this.x2D = x2D;
	}

	public float[] getX2E(){
		return this.x2E;
	}

	public void setX2E(float[] x2E){
		this.x2E = x2E;
	}

	public Float getX2F(){
		return this.x2F;
	}

	public void setX2F(Float x2F){
		this.x2F = x2F;
	}

	public X30 getX30(){
		return this.x30;
	}

	public void setX30(X30 x30){
		this.x30 = x30;
	}

	public X31 getX31(){
		return this.x31;
	}

	public void setX31(X31 x31){
		this.x31 = x31;
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

	public String getBricks(){
		return this.bricks;
	}

	public void setBricks(String bricks){
		this.bricks = bricks;
	}

	public Integer getX3A(){
		return this.x3A;
	}

	public void setX3A(Integer x3A){
		this.x3A = x3A;
	}

	public Integer getX3B(){
		return this.x3B;
	}

	public void setX3B(Integer x3B){
		this.x3B = x3B;
	}

	public Integer getX3C(){
		return this.x3C;
	}

	public void setX3C(Integer x3C){
		this.x3C = x3C;
	}

	@Override
	public String toString() {
		return "Chassis{" +
				"wheels=" + wheels +
				", x2A=" + Arrays.toString(x2A) +
				", seat=" + seat +
				", x2C=" + x2C +
				", x2D=" + Arrays.toString(x2D) +
				", x2E=" + Arrays.toString(x2E) +
				", x2F=" + x2F +
				", x30=" + x30 +
				", x31=" + x31 +
				", x32=" + x32 +
				", x33=" + x33 +
				", bricks='" + bricks + '\'' +
				", x3A=" + x3A +
				", x3B=" + x3B +
				", x3C=" + x3C +
				'}';
	}

}
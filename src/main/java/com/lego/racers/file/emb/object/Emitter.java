package com.lego.racers.file.emb.object;

import java.util.List;

public class Emitter{

	private float p40;
	private float p41;
	private float startWidth;
	private float startHeight;
	private float endWidth;
	private float endHeight;
	private int p47;
	private int p48;
	private float duration;
	private float gravityX;
	private float gravityY;
	private float gravityZ;
	private List<TMPEmitterObject> p43;
	private int p53;
	private String material;

	public float get40(){
		return this.p40;
	}

	public float get41(){
		return this.p41;
	}

	public float getStartWidth(){
		return this.startWidth;
	}

	public float getStartHeight(){
		return this.startHeight;
	}

	public float getEndWidth(){
		return this.endWidth;
	}

	public float getEndHeight(){
		return this.endHeight;
	}

	public int get47(){
		return this.p47;
	}

	public int get48(){
		return this.p48;
	}

	public float getDuration(){
		return this.duration;
	}

	public float getGravityX(){
		return this.gravityX;
	}

	public float getGravityY(){
		return this.gravityY;
	}

	public float getGravityZ(){
		return this.gravityZ;
	}

	public List<TMPEmitterObject> get43(){
		return this.p43;
	}

	public int get53(){
		return this.p53;
	}

	public String getMaterial(){
		return this.material;
	}

	public void set40(float p40){
		this.p40 = p40;
	}

	public void set41(float p41){
		this.p41 = p41;
	}

	public void setStartWidth(float startWidth){
		this.startWidth = startWidth;
	}

	public void setStartHeight(float startHeight){
		this.startHeight = startHeight;
	}

	public void setEndWidth(float endWidth){
		this.endWidth = endWidth;
	}

	public void setEndHeight(float endHeight){
		this.endHeight = endHeight;
	}

	public void set47(int p47){
		this.p47 = p47;
	}

	public void set48(int p48){
		this.p48 = p48;
	}

	public void setDuration(float duration){
		this.duration = duration;
	}

	public void setGravityX(float gravityX){
		this.gravityX = gravityX;
	}

	public void setGravityY(float gravityY){
		this.gravityY = gravityY;
	}

	public void setGravityZ(float gravityZ){
		this.gravityZ = gravityZ;
	}

	public void set43(List<TMPEmitterObject> p43){
		this.p43 = p43;
	}

	public void set53(int p53){
		this.p53 = p53;
	}

	public void setMaterial(String material){
		this.material = material;
	}

	@Override
	public String toString() {
		return "Emitter{" +
				"p40=" + p40 +
				", p41=" + p41 +
				", startWidth=" + startWidth +
				", startHeight=" + startHeight +
				", endWidth=" + endWidth +
				", endHeight=" + endHeight +
				", p47=" + p47 +
				", p48=" + p48 +
				", duration=" + duration +
				", gravityX=" + gravityX +
				", gravityY=" + gravityY +
				", gravityZ=" + gravityZ +
				", p43=" + p43 +
				", p53=" + p53 +
				", material='" + material + '\'' +
				'}';
	}

}
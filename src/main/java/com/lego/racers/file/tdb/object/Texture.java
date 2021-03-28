package com.lego.racers.file.tdb.object;

import com.lego.racers.binary.object.TransparentColor;

public class Texture{

	private boolean isBitmap;
	private TransparentColor transparentColor;
	private boolean p40;
	private boolean p45;

	public boolean getIsBitmap(){
		return this.isBitmap;
	}

	public TransparentColor getTransparentColor(){
		return this.transparentColor;
	}

	public boolean get40(){
		return this.p40;
	}

	public boolean get45(){
		return this.p45;
	}

	public void set40(boolean p40){
		this.p40 = p40;
	}

	public void set45(boolean p45){
		this.p45 = p45;
	}

	public void setIsBitmap(boolean isBitmap){
		this.isBitmap = isBitmap;
	}

	public void setTransparentColor(TransparentColor transparentColor){
		this.transparentColor = transparentColor;
	}

	@Override
	public String toString() {
		return "Texture{" +
				"isBitmap=" + isBitmap +
				", transparentColor=" + transparentColor +
				", p40=" + p40 +
				", p45=" + p45 +
				'}';
	}

}
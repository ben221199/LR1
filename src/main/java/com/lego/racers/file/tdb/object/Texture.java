package com.lego.racers.file.tdb.object;

import com.lego.racers.binary.object.TransparentColor;

public class Texture{

	private Boolean p40;
	private Boolean bitmap;
	private Boolean p43;
	private TransparentColor transparentColor;
	private Boolean p45;

	public Boolean get40() {
		return this.p40;
	}

	public void set40(Boolean p40) {
		this.p40 = p40;
	}

	public Boolean getBitmap() {
		return this.bitmap;
	}

	public void setBitmap(Boolean bitmap) {
		this.bitmap = bitmap;
	}

	public Boolean get43() {
		return this.p43;
	}

	public void set43(Boolean p43) {
		this.p43 = p43;
	}

	public TransparentColor getTransparentColor() {
		return this.transparentColor;
	}

	public void setTransparentColor(TransparentColor transparentColor) {
		this.transparentColor = transparentColor;
	}

	public Boolean get45() {
		return this.p45;
	}

	public void set45(Boolean p45) {
		this.p45 = p45;
	}

	@Override
	public String toString() {
		return "Texture{" +
				"p40=" + p40 +
				", bitmap=" + bitmap +
				", p43=" + p43 +
				", transparentColor=" + transparentColor +
				", p45=" + p45 +
				'}';
	}

}
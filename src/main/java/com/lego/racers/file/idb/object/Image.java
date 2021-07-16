package com.lego.racers.file.idb.object;

public class Image{

	private boolean p41;
	private Color transparentColor;

	public boolean getP41(){
		return this.p41;
	}

	public void setP41(boolean p41){
		this.p41 = p41;
	}

	public Color getTransparentColor(){
		return this.transparentColor;
	}

	public void setTransparentColor(Color transparentColor){
		this.transparentColor = transparentColor;
	}

	@Override
	public String toString() {
		return "Image{" +
				"p41=" + p41 +
				", transparentColor=" + transparentColor +
				'}';
	}

}
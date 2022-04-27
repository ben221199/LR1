package com.lego.racers.file.mib.object;

import com.lego.racers.binary.object.ColorARGB;

public class Coloring{

	private ColorARGB tint;
	private Rectangle rectangle;
	private String parent;

	public ColorARGB getTint(){
		return this.tint;
	}

	public void setTint(ColorARGB tint){
		this.tint = tint;
	}

	public Rectangle getRectangle(){
		return this.rectangle;
	}

	public void setRectangle(Rectangle rectangle){
		this.rectangle = rectangle;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent){
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Coloring{" +
				"tint=" + tint +
				", rectangle=" + rectangle +
				", parent='" + parent + '\'' +
				'}';
	}

}
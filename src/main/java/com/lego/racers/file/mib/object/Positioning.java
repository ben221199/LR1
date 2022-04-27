package com.lego.racers.file.mib.object;

public class Positioning{

	private Rectangle rectangle;
	private String parent;
	private Integer x32;

	public Rectangle getRectangle(){
		return this.rectangle;
	}

	public void setRectangle(Rectangle rectangle){
		this.rectangle = rectangle;
	}

	public String getParent(){
		return this.parent;
	}

	public void setParent(String parent){
		this.parent = parent;
	}

	public Integer getX32(){
		return this.x32;
	}

	public void setX32(Integer x32){
		this.x32 = x32;
	}

	@Override
	public String toString() {
		return "Positioning{" +
				"rectangle=" + rectangle +
				", parent='" + parent + '\'' +
				", x32=" + x32 +
				'}';
	}

}
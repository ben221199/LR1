package com.lego.racers.file.wdb.object;

import com.lego.racers.file.cdb.object.Color;

abstract public class Light{

	protected Color color;

	public Color getColor(){
		return this.color;
	}

	public void setColor(Color color){
		this.color = color;
	}

	@Override
	public String toString() {
		return "Light{" +
				"color=" + color +
				'}';
	}

}
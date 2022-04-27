package com.lego.racers.file.mib.object;

public class Item{

	protected Positioning positioning;

	public Positioning getPositioning(){
		return this.positioning;
	}

	public void setPositioning(Positioning positioning){
		this.positioning = positioning;
	}

	@Override
	public String toString() {
		return "Item{" +
				"positioning=" + positioning +
				'}';
	}

}
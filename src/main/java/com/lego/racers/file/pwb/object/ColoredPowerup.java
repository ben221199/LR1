package com.lego.racers.file.pwb.object;

import com.lego.racers.binary.object.Position;

public class ColoredPowerup extends Powerup{

	public enum Color{
		RED,
		YELLOW,
		BLUE,
		GREEN,
	}

	private Boolean colorRed;
	private Boolean colorYellow;
	private Boolean colorBlue;
	private Boolean colorGreen;

	public ColoredPowerup(){}

	public ColoredPowerup(Position position){
		super(position);
	}

	public ColoredPowerup(Position position,Color color){
		super(position);
		this.setColor(color);
	}

	public ColoredPowerup.Color getColor(){
		if(this.colorRed){
			return Color.RED;
		}
		if(this.colorYellow){
			return Color.YELLOW;
		}
		if(this.colorBlue){
			return Color.BLUE;
		}
		if(this.colorGreen){
			return Color.GREEN;
		}
		return null;
	}

	public void setColor(ColoredPowerup.Color color){
		this.colorRed = null;
		this.colorYellow = null;
		this.colorBlue = null;
		this.colorGreen = null;
		switch(color){
			case RED:this.colorRed = true;break;
			case YELLOW:this.colorYellow = true;break;
			case BLUE:this.colorBlue = true;break;
			case GREEN:this.colorGreen = true;break;
		}
	}

	public Boolean getColorRed() {
		return colorRed;
	}

	public void setColorRed(Boolean colorRed) {
		this.colorRed = colorRed;
	}

	public Boolean getColorYellow() {
		return colorYellow;
	}

	public void setColorYellow(Boolean colorYellow) {
		this.colorYellow = colorYellow;
	}

	public Boolean getColorBlue() {
		return colorBlue;
	}

	public void setColorBlue(Boolean colorBlue) {
		this.colorBlue = colorBlue;
	}

	public Boolean getColorGreen() {
		return colorGreen;
	}

	public void setColorGreen(Boolean colorGreen) {
		this.colorGreen = colorGreen;
	}

	@Override
	public String toString() {
		return "ColoredPowerup{" +
				"colorRed=" + colorRed +
				", colorYellow=" + colorYellow +
				", colorBlue=" + colorBlue +
				", colorGreen=" + colorGreen +
				", position=" + position +
				'}';
	}

}

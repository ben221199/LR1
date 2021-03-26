package com.lego.racers.file.pwb.object;

public class ColoredPowerup extends Powerup{

	public static final byte COLOR_RED = 0x2A;
	public static final byte COLOR_YELLOW = 0x2B;
	public static final byte COLOR_BLUE = 0x2C;
	public static final byte COLOR_GREEN = 0x2D;

	private byte color;

	public ColoredPowerup(){}

	public ColoredPowerup(Position position){
		super(position);
	}

	public ColoredPowerup(Position position,byte color){
		super(position);
		this.color = color;
	}

	public byte getColor(){
		return this.color;
	}

	public void setColor(byte color){
		this.color = color;
	}

	@Override
	public String toString() {
		return "ColoredPowerup{" +
				"color=" + color +
				", position=" + position +
				'}';
	}

}

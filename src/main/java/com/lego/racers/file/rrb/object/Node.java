package com.lego.racers.file.rrb.object;

public class Node{

	private short deltaX;
	private short deltaY;
	private byte deltaZ;

	private byte v4;
	private byte v5;

	private byte v6;
	private byte v7;

	private byte v8;
	private byte v9;

	private byte v10;

	public short getDeltaX(){
		return this.deltaX;
	}

	public void setDeltaX(short deltaX){
		this.deltaX = deltaX;
	}

	public short getDeltaY(){
		return this.deltaY;
	}

	public void setDeltaY(short deltaY){
		this.deltaY = deltaY;
	}

	public byte getDeltaZ(){
		return this.deltaZ;
	}

	public void setDeltaZ(byte deltaZ){
		this.deltaZ = deltaZ;
	}

	public byte getV4(){
		return this.v4;
	}

	public void setV4(byte v4){
		this.v4 = v4;
	}

	public byte getV5(){
		return this.v5;
	}

	public void setV5(byte v5){
		this.v5 = v5;
	}

	public byte getV6(){
		return this.v6;
	}

	public void setV6(byte v6){
		this.v6 = v6;
	}

	public byte getV7(){
		return this.v7;
	}

	public void setV7(byte v7){
		this.v7 = v7;
	}

	public byte getV8(){
		return this.v8;
	}

	public void setV8(byte v8){
		this.v8 = v8;
	}

	public byte getV9(){
		return this.v9;
	}

	public void setV9(byte v9){
		this.v9 = v9;
	}

	public byte getV10(){
		return this.v10;
	}

	public void setV10(byte v10){
		this.v10 = v10;
	}

	@Override
	public String toString() {
		return "Node{" +
				"deltaX=" + deltaX +
				", deltaY=" + deltaY +
				", deltaZ=" + deltaZ +
				", v4=" + v4 +
				", v5=" + v5 +
				", v6=" + v6 +
				", v7=" + v7 +
				", v8=" + v8 +
				", v9=" + v9 +
				", v10=" + v10 +
				'}';
	}

}
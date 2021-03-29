package com.lego.racers.file.gdb.object;

public class Index implements ColoredVertexInterface{

	private byte varA;
	private byte varB;
	private byte varC;

	public byte getVarA() {
		return this.varA;
	}

	public void setVarA(byte varA) {
		this.varA = varA;
	}

	public byte getVarB() {
		return this.varB;
	}

	public void setVarB(byte varB) {
		this.varB = varB;
	}

	public byte getVarC() {
		return this.varC;
	}

	public void setVarC(byte varC) {
		this.varC = varC;
	}

	@Override
	public String toString() {
		return "Index{" +
				"varA=" + varA +
				", varB=" + varB +
				", varC=" + varC +
				'}';
	}

}
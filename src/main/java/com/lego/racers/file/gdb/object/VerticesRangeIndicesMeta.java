package com.lego.racers.file.gdb.object;

public class VerticesRangeIndicesMeta extends IndicesMeta{

	private Byte varA;
	private Short varB;
	private Short varC;

	public Byte getVarA() {
		return varA;
	}

	public void setVarA(Byte varA) {
		this.varA = varA;
	}

	public Short getVarB() {
		return varB;
	}

	public void setVarB(Short varB) {
		this.varB = varB;
	}

	public Short getVarC() {
		return varC;
	}

	public void setVarC(Short varC) {
		this.varC = varC;
	}

	@Override
	public String toString() {
		return "VerticesRangeIndicesMeta{" +
				"varA=" + varA +
				", varB=" + varB +
				", varC=" + varC +
				'}';
	}

}

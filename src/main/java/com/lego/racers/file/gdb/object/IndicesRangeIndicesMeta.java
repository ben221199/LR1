package com.lego.racers.file.gdb.object;

public class IndicesRangeIndicesMeta extends IndicesMeta{

	private Short varA;
	private Short varB;

	public Short getVarA() {
		return varA;
	}

	public void setVarA(Short varA) {
		this.varA = varA;
	}

	public Short getVarB() {
		return varB;
	}

	public void setVarB(Short varB) {
		this.varB = varB;
	}

	@Override
	public String toString() {
		return "IndicesRangeIndicesMeta{" +
				"varA=" + varA +
				", varB=" + varB +
				'}';
	}

}

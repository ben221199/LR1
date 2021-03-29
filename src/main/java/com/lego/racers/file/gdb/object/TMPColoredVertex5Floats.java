package com.lego.racers.file.gdb.object;

public class TMPColoredVertex5Floats implements ColoredVertexInterface {

	private float varA;
	private float varB;
	private float varC;
	private float varD;
	private float varE;

	public float getVarA() {
		return varA;
	}

	public void setVarA(float varA) {
		this.varA = varA;
	}

	public float getVarB() {
		return varB;
	}

	public void setVarB(float varB) {
		this.varB = varB;
	}

	public float getVarC() {
		return varC;
	}

	public void setVarC(float varC) {
		this.varC = varC;
	}

	public float getVarD() {
		return varD;
	}

	public void setVarD(float varD) {
		this.varD = varD;
	}

	public float getVarE() {
		return varE;
	}

	public void setVarE(float varE) {
		this.varE = varE;
	}

	@Override
	public String toString() {
		return "TMPColoredVertex5Floats{" +
				"varA=" + varA +
				", varB=" + varB +
				", varC=" + varC +
				", varD=" + varD +
				", varE=" + varE +
				'}';
	}

}

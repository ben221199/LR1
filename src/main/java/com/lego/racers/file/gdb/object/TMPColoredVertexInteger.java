package com.lego.racers.file.gdb.object;

public class TMPColoredVertexInteger implements ColoredVertexInterface {

	private int varA;

	public int getVarA() {
		return varA;
	}

	public void setVarA(int varA) {
		this.varA = varA;
	}

	@Override
	public String toString() {
		return "TMPColoredVertexInteger{" +
				"varA=" + varA +
				'}';
	}

}

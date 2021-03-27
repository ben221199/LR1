package com.lego.racers.file.cpb.object;

public class Timing{

	private int varA;
	private int varB;
	private int varC;
	private int varD;

	public int getVarA(){
		return this.varA;
	}

	public int getVarB(){
		return this.varB;
	}

	public int getVarC(){
		return this.varC;
	}

	public int getVarD(){
		return this.varD;
	}

	public void setVarA(int varA){
		this.varA = varA;
	}

	public void setVarB(int varB){
		this.varB = varB;
	}

	public void setVarC(int varC){
		this.varC = varC;
	}

	public void setVarD(int varD){
		this.varD = varD;
	}

	@Override
	public String toString() {
		return "Timing{" +
				"varA=" + varA +
				", varB=" + varB +
				", varC=" + varC +
				", varD=" + varD +
				'}';
	}

}
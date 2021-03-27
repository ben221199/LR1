package com.lego.racers.file.cpb.object;

public class Direction{

	private float varA;//Unknown?
	private float varB;//Unknown?
	private float varC;//Unknown?
	private float varD;//Unknown?

	public float getVarA(){
		return this.varA;
	}

	public float getVarB(){
		return this.varB;
	}

	public float getVarC(){
		return this.varC;
	}

	public float getVarD(){
		return this.varD;
	}

	public void setVarA(float varA){
		this.varA = varA;
	}

	public void setVarB(float varB){
		this.varB = varB;
	}

	public void setVarC(float varC){
		this.varC = varC;
	}

	public void setVarD(float varD){
		this.varD = varD;
	}

	@Override
	public String toString() {
		return "Direction{" +
				"varA=" + varA +
				", varB=" + varB +
				", varC=" + varC +
				", varD=" + varD +
				'}';
	}
}
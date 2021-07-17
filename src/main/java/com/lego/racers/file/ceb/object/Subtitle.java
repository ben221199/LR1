package com.lego.racers.file.ceb.object;

public class Subtitle{

	private int stringReferenceId;
	private int stringReferenceLine;
	private String font;
	private boolean p69;
	private float verticalPosition;

	public int getStringReferenceId(){
		return this.stringReferenceId;
	}

	public void setStringReferenceId(int stringReferenceId){
		this.stringReferenceId = stringReferenceId;
	}

	public int getStringReferenceLine(){
		return this.stringReferenceLine;
	}

	public void setStringReferenceLine(int stringReferenceLine){
		this.stringReferenceLine = stringReferenceLine;
	}

	public String getFont(){
		return this.font;
	}

	public void setFont(String font){
		this.font = font;
	}

	public boolean isP69(){
		return this.p69;
	}

	public void setP69(boolean p69){
		this.p69 = p69;
	}

	public float getVerticalPosition(){
		return this.verticalPosition;
	}

	public void setVerticalPosition(float verticalPosition){
		this.verticalPosition = verticalPosition;
	}

	@Override
	public String toString() {
		return "Subtitle{" +
				"stringReferenceId=" + stringReferenceId +
				", stringReferenceLine=" + stringReferenceLine +
				", font='" + font + '\'' +
				", p69=" + p69 +
				", verticalPosition=" + verticalPosition +
				'}';
	}

}
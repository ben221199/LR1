package com.lego.racers.file.leb.object;

public class StudTuple{

	private int upper;
	private int lower;

	public int getUpper() {
		return this.upper;
	}

	public void setUpper(int upper) {
		this.upper = upper;
	}

	public int getLower() {
		return this.lower;
	}

	public void setLower(int lower) {
		this.lower = lower;
	}

	@Override
	public String toString() {
		return "StudTuple{" +
				"upper=" + upper +
				", lower=" + lower +
				'}';
	}

}
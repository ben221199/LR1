package com.lego.racers.file.adb.object;

import java.util.Arrays;

public class Data{

	private Offsets offsets;
	private Rotations rotations;
	private int[] times;

	public Offsets getOffsets(){
		return this.offsets;
	}

	public void setOffsets(Offsets offsets){
		this.offsets = offsets;
	}

	public int[] getTimes(){
		return this.times;
	}

	public Rotations getRotations(){
		return this.rotations;
	}

	public void setRotations(Rotations rotations){
		this.rotations = rotations;
	}

	public void setTimes(int[] times){
		this.times = times;
	}

	@Override
	public String toString() {
		return "Data{" +
				"offsets=" + offsets +
				", rotations=" + rotations +
				", times=" + Arrays.toString(times) +
				'}';
	}

}
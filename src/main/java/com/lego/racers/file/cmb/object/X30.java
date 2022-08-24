package com.lego.racers.file.cmb.object;

import java.util.Arrays;

public class X30{

	private float[] value;

	public float[] getValue(){
		return this.value;
	}

	public void setValue(float[] value){
		this.value = value;
	}

	@Override
	public String toString() {
		return "X30{" +
				"value=" + Arrays.toString(value) +
				'}';
	}

}
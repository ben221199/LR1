package com.lego.racers.file.wdb.object;

import java.util.Arrays;

public class BoxModel extends Model{

	private int[] box;
	private Float boxFloat;

	public int[] getBox(){
		return this.box;
	}

	public void setBox(int[] box){
		this.box = box;
	}

	public Float getBoxFloat(){
		return this.boxFloat;
	}

	public void setBoxFloat(Float boxFloat){
		this.boxFloat = boxFloat;
	}

	@Override
	public float[] getX3F(){
		return null;
	}

	@Override
	public void setX3F(float[] x3F){
	}

	@Override
	public String toString() {
		return "BoxModel{" +
				"box=" + Arrays.toString(box) +
				", boxFloat=" + boxFloat +
				", position=" + position +
				", rotation=" + rotation +
				", x3Es=" + x3Es +
				", x42=" + x42 +
				'}';
	}

}
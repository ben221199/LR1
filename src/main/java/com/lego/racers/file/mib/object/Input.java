package com.lego.racers.file.mib.object;

import java.util.Arrays;

public class Input extends Item{

	private String font;
	private Integer[] x2B;
	private Integer limit;

	public String getFont(){
		return this.font;
	}

	public void setFont(String font){
		this.font = font;
	}

	public Integer[] getX2B(){
		return this.x2B;
	}

	public void setX2B(Integer[] x2B){
		this.x2B = x2B;
	}

	public Integer getLimit(){
		return this.limit;
	}

	public void setLimit(Integer limit){
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "Input{" +
				"font='" + font + '\'' +
				", x2B=" + Arrays.toString(x2B) +
				", limit=" + limit +
				", positioning=" + positioning +
				'}';
	}

}
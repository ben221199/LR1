package com.lego.racers.file.hzb.object;

import java.util.ArrayList;
import java.util.List;

public class Obstacle extends Hazard{

	private Integer x3B;
	private String x41;
	private List<String> x42;

	public Integer getX3B(){
		return this.x3B;
	}

	public void setX3B(Integer x3B){
		this.x3B = x3B;
	}

	public String getX41(){
		return this.x41;
	}

	public void setX41(String x41){
		this.x41 = x41;
	}

	public List<String> getX42(){
		return this.x42;
	}

	public void setX42(List<String> x42){
		this.x42 = x42;
	}

	@Override
	public String toString() {
		return "Obstacle{" +
				"x3B=" + x3B +
				", x41='" + x41 + '\'' +
				", x42=" + x42 +
				'}';
	}
	
}
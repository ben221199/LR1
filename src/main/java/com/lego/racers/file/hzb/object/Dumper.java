package com.lego.racers.file.hzb.object;

public class Dumper extends Hazard{

	private Integer x3B;
	private String x42;
	private Integer x44;

	public Integer getX3B(){
		return this.x3B;
	}

	public void setX3B(Integer x3B){
		this.x3B = x3B;
	}

	public String getX42(){
		return this.x42;
	}

	public void setX42(String x42){
		this.x42 = x42;
	}

	public Integer getX44(){
		return this.x44;
	}

	public void setX44(Integer x44){
		this.x44 = x44;
	}

	@Override
	public String toString() {
		return "Dumper{" +
				"x3B=" + x3B +
				", x42='" + x42 + '\'' +
				", x44=" + x44 +
				'}';
	}
	
}
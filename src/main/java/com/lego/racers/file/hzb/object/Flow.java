package com.lego.racers.file.hzb.object;

import java.util.List;

public class Flow extends Hazard{

	private Cannon x33;
	private List<PositionAndInteger> x37;
	private List<PositionAndInteger> x38;

	public Cannon getX33(){
		return this.x33;
	}

	public void setX33(Cannon x33){
		this.x33 = x33;
	}

	public List<PositionAndInteger> getX37(){
		return this.x37;
	}

	public void setX37(List<PositionAndInteger> x37){
		this.x37 = x37;
	}

	public List<PositionAndInteger> getX38(){
		return this.x38;
	}

	public void setX38(List<PositionAndInteger> x38){
		this.x38 = x38;
	}

	@Override
	public String toString() {
		return "Flow{" +
				"x33=" + x33 +
				", x37=" + x37 +
				", x38=" + x38 +
				'}';
	}

}
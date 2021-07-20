package com.lego.racers.file.ghb.object;

import com.lego.racers.binary.object.Position;

public class Ghost{

	private Nodes nodes;
	private Position startPosition;
	private Orientation startOrientation;
	private int p42_1;
	private int p42_2;
	private int p42_3;
	private int p43;

	public Nodes getNodes(){
		return this.nodes;
	}

	public void setNodes(Nodes nodes){
		this.nodes = nodes;
	}

	public Position getStartPosition(){
		return this.startPosition;
	}

	public void setStartPosition(Position startPosition){
		this.startPosition = startPosition;
	}

	public Orientation getStartOrientation(){
		return this.startOrientation;
	}

	public void setStartOrientation(Orientation startOrientation){
		this.startOrientation = startOrientation;
	}

	public int getP42_1() {
		return this.p42_1;
	}

	public void setP42_1(int p42_1) {
		this.p42_1 = p42_1;
	}

	public int getP42_2() {
		return this.p42_2;
	}

	public void setP42_2(int p42_2) {
		this.p42_2 = p42_2;
	}

	public int getP42_3() {
		return this.p42_3;
	}

	public void setP42_3(int p42_3) {
		this.p42_3 = p42_3;
	}

	public int getP43() {
		return this.p43;
	}

	public void setP43(int p43) {
		this.p43 = p43;
	}

	@Override
	public String toString() {
		return "Ghost{" +
				"nodes=" + nodes +
				", startPosition=" + startPosition +
				", startOrientation=" + startOrientation +
				", p42_1=" + p42_1 +
				", p42_2=" + p42_2 +
				", p42_3=" + p42_3 +
				", p43=" + p43 +
				'}';
	}

}
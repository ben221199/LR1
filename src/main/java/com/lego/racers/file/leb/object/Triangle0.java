package com.lego.racers.file.leb.object;

public class Triangle0 extends Triangle{

	private int p1;
	private int n1;
	private int p2;
	private int p3;

	public int getP1() {
		return this.p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getN1() {
		return this.n1;
	}

	public void setN1(int n1) {
		this.n1 = n1;
	}

	public int getP2() {
		return this.p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
	}

	public int getP3() {
		return this.p3;
	}

	public void setP3(int p3) {
		this.p3 = p3;
	}

	@Override
	public String toString() {
		return "Triangle0{" +
				"type=" + type +
				", p1=" + p1 +
				", n1=" + n1 +
				", p2=" + p2 +
				", p3=" + p3 +
				'}';
	}

}
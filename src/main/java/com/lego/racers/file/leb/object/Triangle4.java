package com.lego.racers.file.leb.object;

public class Triangle4 extends Triangle{

	private int p1;
	private int n1;
	private int p2;
	private int n2;
	private int p3;
	private int n3;

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

	public int getN2() {
		return this.n2;
	}

	public void setN2(int n2) {
		this.n2 = n2;
	}

	public int getP3() {
		return this.p3;
	}

	public void setP3(int p3) {
		this.p3 = p3;
	}

	public int getN3() {
		return this.n3;
	}

	public void setN3(int n3) {
		this.n3 = n3;
	}

	@Override
	public String toString() {
		return "Triangle4{" +
				"type=" + type +
				", p1=" + p1 +
				", n1=" + n1 +
				", p2=" + p2 +
				", n2=" + n2 +
				", p3=" + p3 +
				", n3=" + n3 +
				'}';
	}
}
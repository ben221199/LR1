package com.lego.racers.file.leb.object;

public class Part{

	private String name;
	private int v1;
	private int v2;
	private int v3;
	private int v4;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getV1() {
		return this.v1;
	}

	public void setV1(int v1) {
		this.v1 = v1;
	}

	public int getV2() {
		return this.v2;
	}

	public void setV2(int v2) {
		this.v2 = v2;
	}

	public int getV3() {
		return this.v3;
	}

	public void setV3(int v3) {
		this.v3 = v3;
	}

	public int getV4() {
		return this.v4;
	}

	public void setV4(int v4) {
		this.v4 = v4;
	}

	@Override
	public String toString() {
		return "Part{" +
				"name='" + name + '\'' +
				", v1=" + v1 +
				", v2=" + v2 +
				", v3=" + v3 +
				", v4=" + v4 +
				'}';
	}
	
}
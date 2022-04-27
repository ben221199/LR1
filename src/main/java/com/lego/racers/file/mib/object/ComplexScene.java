package com.lego.racers.file.mib.object;

import java.util.Arrays;

public class ComplexScene extends Scene{

	private Float[] x2E;
	private ComplexScene.X33 x33;
	private String frame;

	public Float[] getX2E(){
		return this.x2E;
	}

	public void setX2E(Float[] x2E){
		this.x2E = x2E;
	}

	public ComplexScene.X33 getX33(){
		return this.x33;
	}

	public void setX33(ComplexScene.X33 x33){
		this.x33 = x33;
	}

	public String getFrame(){
		return this.frame;
	}

	public void setFrame(String frame){
		this.frame = frame;
	}

	@Override
	public String toString() {
		return "ComplexScene{" +
				"x2E=" + Arrays.toString(x2E) +
				", x33=" + x33 +
				", frame='" + frame + '\'' +
				", name='" + name + '\'' +
				", positioning=" + positioning +
				'}';
	}

	public static class X33{

		private Integer p1;
		private Integer p2;

		public Integer getP1(){
			return this.p1;
		}

		public void setP1(Integer p1){
			this.p1 = p1;
		}

		public Integer getP2(){
			return this.p2;
		}

		public void setP2(Integer p2){
			this.p2 = p2;
		}

		@Override
		public String toString() {
			return "X33{" +
					"p1=" + p1 +
					", p2=" + p2 +
					'}';
		}

	}

}
package com.lego.racers.file.mib.object;

import java.util.Arrays;

public class Selector extends Item{

	private Float[] x2E;
	private Integer[] x2F;
	private X33 x33;

	public Float[] getX2E(){
		return this.x2E;
	}

	public void setX2E(Float[] x2E){
		this.x2E = x2E;
	}

	public Integer[] getX2F(){
		return this.x2F;
	}

	public void setX2F(Integer[] x2F){
		this.x2F = x2F;
	}

	public Selector.X33 getX33(){
		return this.x33;
	}

	public void setX33(Selector.X33 x33){
		this.x33 = x33;
	}

	@Override
	public String toString() {
		return "Selector{" +
				"x2E=" + Arrays.toString(x2E) +
				", x2F=" + Arrays.toString(x2F) +
				", x33=" + x33 +
				", positioning=" + positioning +
				'}';
	}

	public static class X33{

		private Integer p1;
		private Integer p2;
		private Float p3;

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

		public Float getP3(){
			return this.p3;
		}

		public void setP3(Float p3){
			this.p3 = p3;
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
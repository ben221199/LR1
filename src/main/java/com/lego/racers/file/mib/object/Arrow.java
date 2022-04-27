package com.lego.racers.file.mib.object;

public class Arrow extends Item{

	private States x28;
	private X2B x2B;
	private Integer x2C;
	private Integer x32;

	public States getX28(){
		return this.x28;
	}

	public void setX28(States x28){
		this.x28 = x28;
	}

	public X2B getX2B(){
		return this.x2B;
	}

	public void setX2B(X2B x2B){
		this.x2B = x2B;
	}

	public Integer getX2C(){
		return this.x2C;
	}

	public void setX2C(Integer x2C){
		this.x2C = x2C;
	}

	public Integer getX32(){
		return this.x32;
	}

	public void setX32(Integer x32){
		this.x32 = x32;
	}

	@Override
	public String toString() {
		return "Arrow{" +
				"x28=" + x28 +
				", x2B=" + x2B +
				", x2C=" + x2C +
				", x32=" + x32 +
				", positioning=" + positioning +
				'}';
	}

	public static class Pair{

		private String arrow1;
		private String arrow2;

		public String getArrow1(){
			return this.arrow1;
		}

		public void setArrow1(String arrow1){
			this.arrow1 = arrow1;
		}

		public String getArrow2(){
			return this.arrow2;
		}

		public void setArrow2(String arrow2){
			this.arrow2 = arrow2;
		}

		@Override
		public String toString() {
			return "Pair{" +
					"arrow1='" + arrow1 + '\'' +
					", arrow2='" + arrow2 + '\'' +
					'}';
		}

	}
	
	public static class States{
		
		private String p1;
		private String p2;
		private String p3;
		private String p4;
		private String p5;
		private String p6;

		public String getP1(){
			return this.p1;
		}

		public void setP1(String p1){
			this.p1 = p1;
		}

		public String getP2(){
			return this.p2;
		}

		public void setP2(String p2){
			this.p2 = p2;
		}

		public String getP3(){
			return this.p3;
		}

		public void setP3(String p3){
			this.p3 = p3;
		}

		public String getP4(){
			return this.p4;
		}

		public void setP4(String p4){
			this.p4 = p4;
		}

		public String getP5(){
			return this.p5;
		}

		public void setP5(String p5){
			this.p5 = p5;
		}

		public String getP6(){
			return this.p6;
		}

		public void setP6(String p6){
			this.p6 = p6;
		}

		@Override
		public String toString() {
			return "States{" +
					"p1='" + p1 + '\'' +
					", p2='" + p2 + '\'' +
					", p3='" + p3 + '\'' +
					", p4='" + p4 + '\'' +
					", p5='" + p5 + '\'' +
					", p6='" + p6 + '\'' +
					'}';
		}
		
	}

}
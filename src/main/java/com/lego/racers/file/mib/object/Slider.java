package com.lego.racers.file.mib.object;

public class Slider extends Item{

	private X33 x33;
	private Images images;
	private Arrow.Pair arrows;

	public X33 getX33(){
		return this.x33;
	}

	public void setX33(X33 x33){
		this.x33 = x33;
	}

	public Images getImages(){
		return this.images;
	}

	public void setImages(Images images){
		this.images = images;
	}

	public Arrow.Pair getArrows(){
		return this.arrows;
	}

	public void setArrows(Arrow.Pair arrows){
		this.arrows = arrows;
	}

	@Override
	public String toString() {
		return "Slider{" +
				"x33=" + x33 +
				", images=" + images +
				", arrows=" + arrows +
				", positioning=" + positioning +
				'}';
	}

	public static class Images{

		private String tab;
		private String bar;

		public String getTab(){
			return this.tab;
		}

		public void setTab(String tab){
			this.tab = tab;
		}

		public String getBar(){
			return this.bar;
		}

		public void setBar(String bar){
			this.bar = bar;
		}

		@Override
		public String toString() {
			return "Images{" +
					"tab='" + tab + '\'' +
					", bar='" + bar + '\'' +
					'}';
		}

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
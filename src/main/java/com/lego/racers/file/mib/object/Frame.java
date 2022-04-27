package com.lego.racers.file.mib.object;

import com.lego.racers.binary.object.ColorARGB;

public class Frame extends Item{

	private Border border;
	private Tint tint;
	private Integer x33;

	public Border getBorder(){
		return this.border;
	}

	public void setBorder(Border border){
		this.border = border;
	}

	public Tint getTint(){
		return this.tint;
	}

	public void setTint(Tint tint){
		this.tint = tint;
	}

	public Integer getX33(){
		return this.x33;
	}

	public void setX33(Integer x33){
		this.x33 = x33;
	}

	@Override
	public String toString() {
		return "Frame{" +
				"border=" + border +
				", tint=" + tint +
				", x33=" + x33 +
				", positioning=" + positioning +
				'}';
	}

	public static class Border{

		private String upperLeft;
		private String top;
		private String upperRight;
		private String right;
		private String bottomRight;
		private String bottom;
		private String bottomLeft;
		private String left;

		public String getUpperLeft(){
			return this.upperLeft;
		}

		public void setUpperLeft(String upperLeft){
			this.upperLeft = upperLeft;
		}

		public String getTop(){
			return this.top;
		}

		public void setTop(String top){
			this.top = top;
		}

		public String getUpperRight(){
			return this.upperRight;
		}

		public void setUpperRight(String upperRight){
			this.upperRight = upperRight;
		}

		public String getRight(){
			return this.right;
		}

		public void setRight(String right){
			this.right = right;
		}

		public String getBottomRight(){
			return this.bottomRight;
		}

		public void setBottomRight(String bottomRight){
			this.bottomRight = bottomRight;
		}

		public String getBottom(){
			return this.bottom;
		}

		public void setBottom(String bottom){
			this.bottom = bottom;
		}

		public String getBottomLeft(){
			return this.bottomLeft;
		}

		public void setBottomLeft(String bottomLeft){
			this.bottomLeft = bottomLeft;
		}

		public String getLeft(){
			return this.left;
		}

		public void setLeft(String left){
			this.left = left;
		}

		@Override
		public String toString(){
			return "Border{" +
					"upperLeft='" + upperLeft + '\'' +
					", top='" + top + '\'' +
					", upperRight='" + upperRight + '\'' +
					", right='" + right + '\'' +
					", bottomRight='" + bottomRight + '\'' +
					", bottom='" + bottom + '\'' +
					", bottomLeft='" + bottomLeft + '\'' +
					", left='" + left + '\'' +
					'}';
		}

	}

	public static class Tint{

		private ColorARGB border;
		private ColorARGB background;

		public ColorARGB getBorder(){
			return this.border;
		}

		public void setBorder(ColorARGB border){
			this.border = border;
		}

		public ColorARGB getBackground(){
			return this.background;
		}

		public void setBackground(ColorARGB background){
			this.background = background;
		}

		@Override
		public String toString() {
			return "Tint{" +
					"border=" + border +
					", background=" + background +
					'}';
		}

	}

}
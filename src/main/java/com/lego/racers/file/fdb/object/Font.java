package com.lego.racers.file.fdb.object;

import java.util.Arrays;
import java.util.List;

public class Font{

	private boolean p40;
	private int transparentColorRed;
	private int transparentColorGreen;
	private int transparentColorBlue;
	private int spacing;
	private List<Object> order;

	public boolean get40(){
		return this.p40;
	}

	public int getTransparentColorRed(){
		return this.transparentColorRed;
	}

	public int getTransparentColorGreen(){
		return this.transparentColorGreen;
	}

	public int getTransparentColorBlue(){
		return this.transparentColorBlue;
	}

	public int getSpacing(){
		return this.spacing;
	}

	public List<Object> getOrder(){
		return this.order;
	}

	public void set40(boolean p40){
		this.p40 = p40;
	}

	public void setTransparentColorRed(int red){
		this.transparentColorRed = red;
	}

	public void setTransparentColorGreen(int green){
		this.transparentColorGreen = green;
	}

	public void setTransparentColorBlue(int blue){
		this.transparentColorBlue = blue;
	}

	public void setSpacing(int spacing){
		this.spacing = spacing;
	}

	public void setOrder(List<Object> order){
		this.order = order;
	}

	@Override
	public String toString(){
		return "Font{" +
				"p40=" + p40 +
				", transparentColorRed=" + transparentColorRed +
				", transparentColorGreen=" + transparentColorGreen +
				", transparentColorBlue=" + transparentColorBlue +
				", spacing=" + spacing +
				", order=" + Arrays.deepToString(order.toArray()) +
				'}';
	}

}
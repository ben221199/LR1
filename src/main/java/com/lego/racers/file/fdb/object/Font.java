package com.lego.racers.file.fdb.object;

import com.lego.racers.binary.object.TransparentColor;

import java.util.List;

public class Font{

	private Boolean p40;
	private TransparentColor transparentColor;
	private List<Object> order;
	private Integer spacing;

	public Boolean get40() {
		return this.p40;
	}

	public void set40(Boolean p40) {
		this.p40 = p40;
	}

	public TransparentColor getTransparentColor() {
		return this.transparentColor;
	}

	public void setTransparentColor(TransparentColor transparentColor) {
		this.transparentColor = transparentColor;
	}

	public List<Object> getOrder() {
		return this.order;
	}

	public void setOrder(List<Object> order) {
		this.order = order;
	}

	public Integer getSpacing() {
		return this.spacing;
	}

	public void setSpacing(Integer spacing) {
		this.spacing = spacing;
	}

	@Override
	public String toString() {
		return "Font{" +
				"p40=" + p40 +
				", transparentColor=" + transparentColor +
				", order=" + order +
				", spacing=" + spacing +
				'}';
	}

}
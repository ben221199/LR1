package com.lego.racers.file.skb.object;

public class SkyGradient {

	private Integer p40;
	private Color colorStart;
	private Color colorMiddle;
	private Color colorEnd;

	public Integer get40() {
		return this.p40;
	}

	public void set40(Integer p40) {
		this.p40 = p40;
	}

	public Color getColorStart() {
		return this.colorStart;
	}

	public void setColorStart(Color colorStart) {
		this.colorStart = colorStart;
	}

	public Color getColorMiddle() {
		return this.colorMiddle;
	}

	public void setColorMiddle(Color colorMiddle) {
		this.colorMiddle = colorMiddle;
	}

	public Color getColorEnd() {
		return this.colorEnd;
	}

	public void setColorEnd(Color colorEnd) {
		this.colorEnd = colorEnd;
	}

	@Override
	public String toString() {
		return "SkyGradient{" +
				"p40=" + p40 +
				", colorStart=" + colorStart +
				", colorMiddle=" + colorMiddle +
				", colorEnd=" + colorEnd +
				'}';
	}

}

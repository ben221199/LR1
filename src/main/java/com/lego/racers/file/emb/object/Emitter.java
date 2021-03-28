package com.lego.racers.file.emb.object;

import java.util.List;

public class Emitter{

	private Float p40;
	private Float p41;
	private Gravity gravity;
	private List<TMPEmitterObject> p43;
	private Float startWidth;
	private Float startHeight;
	private Integer p46;
	private Integer p47;
	private Integer p48;
	private Float endWidth;
	private Float endHeight;
	private Float duration;
	private String material;
	private Integer p53;

	public Float get40() {
		return this.p40;
	}

	public void set40(Float p40) {
		this.p40 = p40;
	}

	public Float get41() {
		return this.p41;
	}

	public void set41(Float p41) {
		this.p41 = p41;
	}

	public Gravity getGravity() {
		return this.gravity;
	}

	public void setGravity(Gravity gravity) {
		this.gravity = gravity;
	}

	public List<TMPEmitterObject> get43() {
		return this.p43;
	}

	public void set43(List<TMPEmitterObject> p43) {
		this.p43 = p43;
	}

	public Float getStartWidth() {
		return this.startWidth;
	}

	public void setStartWidth(Float startWidth) {
		this.startWidth = startWidth;
	}

	public Float getStartHeight() {
		return this.startHeight;
	}

	public void setStartHeight(Float startHeight) {
		this.startHeight = startHeight;
	}

	public Integer get46() {
		return this.p46;
	}

	public void set46(Integer p46) {
		this.p46 = p46;
	}

	public Integer get47() {
		return this.p47;
	}

	public void set47(Integer p47) {
		this.p47 = p47;
	}

	public Integer get48() {
		return this.p48;
	}

	public void set48(Integer p48) {
		this.p48 = p48;
	}

	public Float getEndWidth() {
		return this.endWidth;
	}

	public void setEndWidth(Float endWidth) {
		this.endWidth = endWidth;
	}

	public Float getEndHeight() {
		return this.endHeight;
	}

	public void setEndHeight(Float endHeight) {
		this.endHeight = endHeight;
	}

	public Float getDuration() {
		return this.duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	public String getMaterial() {
		return this.material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Integer get53() {
		return this.p53;
	}

	public void set53(Integer p53) {
		this.p53 = p53;
	}

	@Override
	public String toString() {
		return "Emitter{" +
				"p40=" + p40 +
				", p41=" + p41 +
				", gravity=" + gravity +
				", p43=" + p43 +
				", startWidth=" + startWidth +
				", startHeight=" + startHeight +
				", p46=" + p46 +
				", p47=" + p47 +
				", p48=" + p48 +
				", endWidth=" + endWidth +
				", endHeight=" + endHeight +
				", duration=" + duration +
				", material='" + material + '\'' +
				", p53=" + p53 +
				'}';
	}

}
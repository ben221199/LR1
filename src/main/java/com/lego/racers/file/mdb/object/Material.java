package com.lego.racers.file.mdb.object;

public class Material{

	private AmbientColor ambientColor;
	private DiffuseColor diffuseColor;
	private Boolean p43;
	private String texture;
	private Boolean p45;
	private Boolean p46;
	private Boolean p56;
	private Boolean p63;
	private Boolean p65;
	private Boolean p69;
	private Integer alpha;
	private Boolean p74;

	public AmbientColor getAmbientColor() {
		return this.ambientColor;
	}

	public void setAmbientColor(AmbientColor ambientColor) {
		this.ambientColor = ambientColor;
	}

	public DiffuseColor getDiffuseColor() {
		return this.diffuseColor;
	}

	public void setDiffuseColor(DiffuseColor diffuseColor) {
		this.diffuseColor = diffuseColor;
	}

	public Boolean get43() {
		return this.p43;
	}

	public void set43(Boolean p43) {
		this.p43 = p43;
	}

	public String getTexture() {
		return this.texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public Boolean get45() {
		return this.p45;
	}

	public void set45(Boolean p45) {
		this.p45 = p45;
	}

	public Boolean get46() {
		return this.p46;
	}

	public void set46(Boolean p46) {
		this.p46 = p46;
	}

	public Boolean get56() {
		return this.p56;
	}

	public void set56(Boolean p56) {
		this.p56 = p56;
	}

	public Boolean get63() {
		return this.p63;
	}

	public void set63(Boolean p63) {
		this.p63 = p63;
	}

	public Boolean get65() {
		return this.p65;
	}

	public void set65(Boolean p65) {
		this.p65 = p65;
	}

	public Boolean get69() {
		return this.p69;
	}

	public void set69(Boolean p69) {
		this.p69 = p69;
	}

	public Integer getAlpha() {
		return this.alpha;
	}

	public void setAlpha(Integer alpha) {
		this.alpha = alpha;
	}

	public Boolean get74() {
		return this.p74;
	}

	public void set74(Boolean p74) {
		this.p74 = p74;
	}

	@Override
	public String toString() {
		return "Material{" +
				"ambientColor=" + ambientColor +
				", diffuseColor=" + diffuseColor +
				", p43=" + p43 +
				", texture='" + texture + '\'' +
				", p45=" + p45 +
				", p46=" + p46 +
				", p56=" + p56 +
				", p63=" + p63 +
				", p65=" + p65 +
				", p69=" + p69 +
				", alpha=" + alpha +
				", p74=" + p74 +
				'}';
	}
	
}
package com.lego.racers.file.ceb.object;

public class Event{

	private boolean _unknown3;
	private boolean soundEffect;
	private boolean _unknown4;
	private boolean subtitle;
	private boolean _unknown5;
	private String p78;
	private String p79;
	private boolean fade;

	public boolean get_Unknown3(){
		return this._unknown3;
	}

	public void set_Unknown3(boolean _unknown3){
		this._unknown3 = _unknown3;
	}

	public boolean isSoundEffect(){
		return this.soundEffect;
	}

	public void setSoundEffect(boolean soundEffect){
		this.soundEffect = soundEffect;
	}

	public boolean getUnknown4(){
		return this._unknown4;
	}

	public void set_Unknown4(boolean _unknown4){
		this._unknown4 = _unknown4;
	}

	public boolean isSubtitle(){
		return this.subtitle;
	}

	public void setSubtitle(boolean subtitle){
		this.subtitle = subtitle;
	}

	public boolean getUnknown5(){
		return this._unknown5;
	}

	public void set_Unknown5(boolean _unknown5){
		this._unknown5 = _unknown5;
	}

	public String getP78(){
		return this.p78;
	}

	public void setP78(String p78){
		this.p78 = p78;
	}

	public String getP79(){
		return this.p79;
	}

	public void setP79(String p79){
		this.p79 = p79;
	}

	public boolean isFade(){
		return this.fade;
	}

	public void setFade(boolean fade){
		this.fade = fade;
	}

	@Override
	public String toString() {
		return "Event{" +
				"_unknown3=" + _unknown3 +
				", soundEffect=" + soundEffect +
				", _unknown4=" + _unknown4 +
				", subtitle=" + subtitle +
				", _unknown5=" + _unknown5 +
				", p78='" + p78 + '\'' +
				", p79='" + p79 + '\'' +
				", fade=" + fade +
				'}';
	}

}
package com.lego.racers.file.ceb.object;

public class SoundEffect {

	private int soundBankId;
	private int soundBankLine;
	private float volume;
	private float p51;
	private float p52;
	private boolean loop;

	public int getSoundBankId(){
		return this.soundBankId;
	}

	public void setSoundBankId(int soundBankId){
		this.soundBankId = soundBankId;
	}

	public int getSoundBankLine(){
		return this.soundBankLine;
	}

	public void setSoundBankLine(int soundBankLine){
		this.soundBankLine = soundBankLine;
	}

	public float getVolume(){
		return this.volume;
	}

	public void setVolume(float volume){
		this.volume = volume;
	}

	public float getP51(){
		return this.p51;
	}

	public void setP51(float p51){
		this.p51 = p51;
	}

	public float getP52(){
		return this.p52;
	}

	public void setP52(float p52){
		this.p52 = p52;
	}

	public boolean getLoop(){
		return this.loop;
	}

	public void setLoop(boolean loop){
		this.loop = loop;
	}

	@Override
	public String toString() {
		return "SoundEffect{" +
				"soundBankId=" + soundBankId +
				", soundBankLine=" + soundBankLine +
				", volume=" + volume +
				", p51=" + p51 +
				", p52=" + p52 +
				", loop=" + loop +
				'}';
	}

}
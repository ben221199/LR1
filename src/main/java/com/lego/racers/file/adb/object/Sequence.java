package com.lego.racers.file.adb.object;

import com.lego.racers.binary.object.Position;

public class Sequence{

	private Integer pointer;
	private Integer duration;
	private Integer duration2;
	private Integer speed;
	private Position initialOffset;
	private Rotation initialRotation;

	public Integer getPointer(){
		return this.pointer;
	}

	public void setPointer(Integer pointer){
		this.pointer = pointer;
	}

	public Integer getDuration(){
		return this.duration;
	}

	public void setDuration(Integer duration){
		this.duration = duration;
	}

	public Integer getDuration2(){
		return this.duration2;
	}

	public void setDuration2(Integer duration2){
		this.duration2 = duration2;
	}

	public Integer getSpeed(){
		return this.speed;
	}

	public void setSpeed(Integer speed){
		this.speed = speed;
	}

	public Position getInitialOffset(){
		return this.initialOffset;
	}

	public void setInitialOffset(Position initialOffset){
		this.initialOffset = initialOffset;
	}

	public Rotation getInitialRotation(){
		return this.initialRotation;
	}

	public void setInitialRotation(Rotation initialRotation){
		this.initialRotation = initialRotation;
	}

	@Override
	public String toString() {
		return "Sequence{" +
				"pointer=" + pointer +
				", duration=" + duration +
				", duration2=" + duration2 +
				", speed=" + speed +
				", initialOffset=" + initialOffset +
				", initialRotation=" + initialRotation +
				'}';
	}

}
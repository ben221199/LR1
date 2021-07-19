package com.lego.racers.file.cdb.object;

import com.lego.racers.binary.object.Position;

public class Model{

	private int startFrame;
	private int duration;
	private int animationSequenceId;
	private String nameStatic;
	private String name;
	private int p50_1;
	private int p50_2;
	private Position position;
	private Rotation rotation;
	private _Unknowns54 p54;

	public int getStartFrame(){
		return this.startFrame;
	}

	public void setStartFrame(int startFrame){
		this.startFrame = startFrame;
	}

	public int getDuration(){
		return this.duration;
	}

	public void setDuration(int duration){
		this.duration = duration;
	}

	public int getAnimationSequenceId(){
		return this.animationSequenceId;
	}

	public void setAnimationSequenceId(int animationSequenceId){
		this.animationSequenceId = animationSequenceId;
	}

	public String getNameStatic(){
		return this.nameStatic;
	}

	public void setNameStatic(String nameStatic){
		this.nameStatic = nameStatic;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getP50_1(){
		return this.p50_1;
	}

	public void setP50_1(int p50_1){
		this.p50_1 = p50_1;
	}

	public int getP50_2(){
		return this.p50_2;
	}

	public void setP50_2(int p50_2){
		this.p50_2 = p50_2;
	}

	public Position getPosition(){
		return this.position;
	}

	public void setPosition(Position position){
		this.position = position;
	}

	public Rotation getRotation(){
		return this.rotation;
	}

	public void setRotation(Rotation rotation){
		this.rotation = rotation;
	}

	public _Unknowns54 getP54(){
		return this.p54;
	}

	public void setP54(_Unknowns54 p54){
		this.p54 = p54;
	}

	@Override
	public String toString(){
		return "Model{" +
				"startFrame=" + startFrame +
				", duration=" + duration +
				", animationSequenceId=" + animationSequenceId +
				", nameStatic='" + nameStatic + '\'' +
				", name='" + name + '\'' +
				", p50_1=" + p50_1 +
				", p50_2=" + p50_2 +
				", position=" + position +
				", p54=" + p54 +
				'}';
	}
	
}
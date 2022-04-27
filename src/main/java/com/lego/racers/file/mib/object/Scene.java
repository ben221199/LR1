package com.lego.racers.file.mib.object;

public class Scene extends Item{

	protected String name;

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return "Scene{" +
				"name='" + name + '\'' +
				", positioning=" + positioning +
				'}';
	}

}
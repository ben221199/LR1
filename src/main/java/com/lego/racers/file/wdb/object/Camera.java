package com.lego.racers.file.wdb.object;

import com.lego.racers.binary.object.Position;
import com.lego.racers.file.cdb.object.Rotation;

import java.util.Arrays;

public class Camera{

	private int[] dynamicModel;
	private Position position;
	private Rotation rotation;
	private Integer x35;
	private Float nearPlane;
	private Float farPlane;
	private Float fieldOfView;

	public int[] getDynamicModel(){
		return this.dynamicModel;
	}

	public void setDynamicModel(int[] dynamicModel){
		this.dynamicModel = dynamicModel;
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

	public Integer getX35(){
		return this.x35;
	}

	public void setX35(Integer x35){
		this.x35 = x35;
	}

	public Float getNearPlane(){
		return this.nearPlane;
	}

	public void setNearPlane(Float nearPlane){
		this.nearPlane = nearPlane;
	}

	public Float getFarPlane(){
		return this.farPlane;
	}

	public void setFarPlane(Float farPlane){
		this.farPlane = farPlane;
	}

	public Float getFieldOfView(){
		return this.fieldOfView;
	}

	public void setFieldOfView(Float fieldOfView){
		this.fieldOfView = fieldOfView;
	}

	@Override
	public String toString() {
		return "Camera{" +
				"dynamicModel=" + Arrays.toString(dynamicModel) +
				", position=" + position +
				", rotation=" + rotation +
				", x35=" + x35 +
				", nearPlane=" + nearPlane +
				", farPlane=" + farPlane +
				", fieldOfView=" + fieldOfView +
				'}';
	}

}
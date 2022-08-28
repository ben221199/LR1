package com.lego.racers.file.wdb.object;

import com.lego.racers.binary.object.Position;
import com.lego.racers.file.cdb.object.Rotation;

public class BoundingVolumeModel{

	private Position position;
	private Rotation rotation;
	private Integer boundingVolume;

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

	public Integer getBoundingVolume(){
		return this.boundingVolume;
	}

	public void setBoundingVolume(Integer boundingVolume){
		this.boundingVolume = boundingVolume;
	}

	@Override
	public String toString() {
		return "BoundingVolumeModel{" +
				"position=" + position +
				", rotation=" + rotation +
				", boundingVolume=" + boundingVolume +
				'}';
	}

}
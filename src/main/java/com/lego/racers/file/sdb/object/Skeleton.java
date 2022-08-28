package com.lego.racers.file.sdb.object;

import com.lego.racers.binary.object.Position;

public class Skeleton{

	private Position offset;
	private Rotation rotation;
	private String parent;

	public Position getOffset(){
		return this.offset;
	}

	public void setOffset(Position offset){
		this.offset = offset;
	}

	public Rotation getRotation(){
		return this.rotation;
	}

	public void setRotation(Rotation rotation){
		this.rotation = rotation;
	}

	public String getParent(){
		return this.parent;
	}

	public void setParent(String parent){
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Skeleton{" +
				"offset=" + offset +
				", rotation=" + rotation +
				", parent='" + parent + '\'' +
				'}';
	}

}
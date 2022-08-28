package com.lego.racers.file.wdb.object;

import com.lego.racers.binary.object.Position;
import com.lego.racers.file.cdb.object.Rotation;

import java.util.Arrays;

abstract public class Model{

	protected Position position;
	protected Rotation rotation;
	protected X3Es x3Es;
	protected float[] x3F;
	protected Boolean x42;

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

	public X3Es getX3Es(){
		return this.x3Es;
	}

	public void setX3Es(X3Es x3Es){
		this.x3Es = x3Es;
	}

	public float[] getX3F(){
		return this.x3F;
	}

	public void setX3F(float[] x3F){
		this.x3F = x3F;
	}

	public Boolean getX42(){
		return this.x42;
	}

	public void setX42(Boolean x42){
		this.x42 = x42;
	}

	@Override
	public String toString() {
		return "Model{" +
				"position=" + position +
				", rotation=" + rotation +
				", x3Es=" + x3Es +
				", x3F=" + Arrays.toString(x3F) +
				", x42=" + x42 +
				'}';
	}

}
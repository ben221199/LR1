package com.lego.racers.file.spb.object;

public class Orientation{

	private float rotationX;
	private float rotationY;
	private float rotationZ;

	private float flipX;//Unknown
	private float flipY;//Unknown
	private float flipZ;

	public float getRotationX(){
		return this.rotationX;
	}

	public float getRotationY(){
		return this.rotationY;
	}

	public float getRotationZ(){
		return this.rotationZ;
	}

	public float getFlipX(){
		return this.flipX;
	}

	public float getFlipY(){
		return this.flipY;
	}

	public float getFlipZ(){
		return this.flipZ;
	}

	public void setRotationX(float rotationX){
		this.rotationX = rotationX;
	}

	public void setRotationY(float rotationY){
		this.rotationY = rotationY;
	}

	public void setRotationZ(float rotationZ){
		this.rotationZ = rotationZ;
	}

	public void setFlipX(float flipX){
		this.flipX = flipX;
	}

	public void setFlipY(float flipY){
		this.flipY = flipY;
	}

	public void setFlipZ(float flipZ){
		this.flipZ = flipZ;
	}

	@Override
	public String toString() {
		return "Orientation{" +
				"rotationX=" + rotationX +
				", rotationY=" + rotationY +
				", rotationZ=" + rotationZ +
				", flipX=" + flipX +
				", flipY=" + flipY +
				", flipZ=" + flipZ +
				'}';
	}

}
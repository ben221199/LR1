package com.lego.racers.file.wdb.object;

import com.lego.racers.binary.object.Position;

import java.util.Arrays;

public class TextureModel extends Model{

	private int[] graphic2s;
	private Position pivot;
	private String texture;
	private Float width;
	private Float height;
	private Float cullRadius;
	private int[] x3E;

	public int[] getGraphic2s(){
		return this.graphic2s;
	}

	public void setGraphic2s(int[] graphic2s){
		this.graphic2s = graphic2s;
	}

	public Position getPivot(){
		return this.pivot;
	}

	public void setPivot(Position pivot){
		this.pivot = pivot;
	}

	public String getTexture(){
		return this.texture;
	}

	public void setTexture(String texture){
		this.texture = texture;
	}

	public Float getWidth(){
		return this.width;
	}

	public void setWidth(Float width){
		this.width = width;
	}

	public Float getHeight(){
		return this.height;
	}

	public void setHeight(Float height){
		this.height = height;
	}

	public Float getCullRadius(){
		return this.cullRadius;
	}

	public void setCullRadius(Float cullRadius){
		this.cullRadius = cullRadius;
	}

	public int[] getX3E(){
		return this.x3E;
	}

	public void setX3E(int[] x3E){
		this.x3E = x3E;
	}

	@Override
	public String toString() {
		return "TextureModel{" +
				"graphic2s=" + Arrays.toString(graphic2s) +
				", x3E=" + Arrays.toString(x3E) +
				", position=" + position +
				'}';
	}

}
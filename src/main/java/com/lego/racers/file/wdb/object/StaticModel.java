package com.lego.racers.file.wdb.object;

import java.util.Arrays;

public class StaticModel extends Model{

	private Integer graphic;
	private Float graphicFloat;

	public Integer getGraphic(){
		return this.graphic;
	}

	public void setGraphic(Integer graphic){
		this.graphic = graphic;
	}

	public Float getGraphicFloat(){
		return this.graphicFloat;
	}

	public void setGraphicFloat(Float graphicFloat){
		this.graphicFloat = graphicFloat;
	}

	@Override
	public String toString() {
		return "StaticModel{" +
				"graphic=" + graphic +
				", graphicFloat=" + graphicFloat +
				", position=" + position +
				", rotation=" + rotation +
				", x3Es=" + x3Es +
				", x3F=" + Arrays.toString(x3F) +
				", x42=" + x42 +
				'}';
	}

}
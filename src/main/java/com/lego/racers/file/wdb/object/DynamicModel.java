package com.lego.racers.file.wdb.object;

import java.util.Arrays;

public class DynamicModel extends Model{

	private int[] animationSkeleton;
	private Float animationSkeletonFloat;
	private int[] graphicAnimationSkeleton;
	private Float graphicAnimationSkeletonFloat;
	private Integer x35;
	private Boolean x4C;

	public int[] getAnimationSkeleton(){
		return this.animationSkeleton;
	}

	public void setAnimationSkeleton(int[] animationSkeleton){
		this.animationSkeleton = animationSkeleton;
	}

	public Float getAnimationSkeletonFloat(){
		return this.animationSkeletonFloat;
	}

	public void setAnimationSkeletonFloat(Float animationSkeletonFloat){
		this.animationSkeletonFloat = animationSkeletonFloat;
	}

	public int[] getGraphicAnimationSkeleton(){
		return this.graphicAnimationSkeleton;
	}

	public void setGraphicAnimationSkeleton(int[] graphicAnimationSkeleton){
		this.graphicAnimationSkeleton = graphicAnimationSkeleton;
	}

	public Float getGraphicAnimationSkeletonFloat(){
		return this.graphicAnimationSkeletonFloat;
	}

	public void setGraphicAnimationSkeletonFloat(Float graphicAnimationSkeletonFloat){
		this.graphicAnimationSkeletonFloat = graphicAnimationSkeletonFloat;
	}

	public Integer getX35(){
		return this.x35;
	}

	public void setX35(Integer x35){
		this.x35 = x35;
	}

	public Boolean getX4C(){
		return this.x4C;
	}

	public void setX4C(Boolean x4C){
		this.x4C = x4C;
	}

	@Override
	public String toString() {
		return "DynamicModel{" +
				"animationSkeleton=" + Arrays.toString(animationSkeleton) +
				", animationSkeletonFloat=" + animationSkeletonFloat +
				", graphicAnimationSkeleton=" + Arrays.toString(graphicAnimationSkeleton) +
				", graphicAnimationSkeletonFloat=" + graphicAnimationSkeletonFloat +
				", x35=" + x35 +
				", x4C=" + x4C +
				", position=" + position +
				", rotation=" + rotation +
				", x3Es=" + x3Es +
				", x3F=" + Arrays.toString(x3F) +
				", x42=" + x42 +
				'}';
	}

}
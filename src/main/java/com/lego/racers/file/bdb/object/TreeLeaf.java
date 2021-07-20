package com.lego.racers.file.bdb.object;

public class TreeLeaf extends TreeNode{

	private short parent;
	private short graphOffset;
	private short graphLength;
	private short carInRegion;
	private short visibleRegionOffset;
	private short visibleRegionLength;

	public short getParent() {
		return this.parent;
	}

	public void setParent(short parent) {
		this.parent = parent;
	}

	public short getGraphOffset() {
		return this.graphOffset;
	}

	public void setGraphOffset(short graphOffset) {
		this.graphOffset = graphOffset;
	}

	public short getGraphLength() {
		return this.graphLength;
	}

	public void setGraphLength(short graphLength) {
		this.graphLength = graphLength;
	}

	public short getCarInRegion() {
		return this.carInRegion;
	}

	public void setCarInRegion(short carInRegion) {
		this.carInRegion = carInRegion;
	}

	public short getVisibleRegionOffset() {
		return this.visibleRegionOffset;
	}

	public void setVisibleRegionOffset(short visibleRegionOffset) {
		this.visibleRegionOffset = visibleRegionOffset;
	}

	public short getVisibleRegionLength() {
		return this.visibleRegionLength;
	}

	public void setVisibleRegionLength(short visibleRegionLength) {
		this.visibleRegionLength = visibleRegionLength;
	}

	@Override
	public String toString() {
		return "TreeLeaf{" +
				"parent=" + parent +
				", graphOffset=" + graphOffset +
				", graphLength=" + graphLength +
				", carInRegion=" + carInRegion +
				", visibleRegionOffset=" + visibleRegionOffset +
				", visibleRegionLength=" + visibleRegionLength +
				'}';
	}
	
}
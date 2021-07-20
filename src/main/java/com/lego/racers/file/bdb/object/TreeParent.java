package com.lego.racers.file.bdb.object;

import com.lego.racers.binary.object.Position;

public class TreeParent extends TreeNode{

	private int parent;
	private int child1;
	private int child2;
	private Position selectorPosition;
	private float selectorValue;

	public int getParent() {
		return this.parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getChild1() {
		return this.child1;
	}

	public void setChild1(int child1) {
		this.child1 = child1;
	}

	public int getChild2() {
		return this.child2;
	}

	public void setChild2(int child2) {
		this.child2 = child2;
	}

	public Position getSelectorPosition() {
		return this.selectorPosition;
	}

	public void setSelectorPosition(Position selectorPosition) {
		this.selectorPosition = selectorPosition;
	}

	public float getSelectorValue() {
		return this.selectorValue;
	}

	public void setSelectorValue(float selectorValue) {
		this.selectorValue = selectorValue;
	}

	@Override
	public String toString() {
		return "TreeParent{" +
				"parent=" + parent +
				", child1=" + child1 +
				", child2=" + child2 +
				", selectorPosition=" + selectorPosition +
				", selectorValue=" + selectorValue +
				'}';
	}
	
}
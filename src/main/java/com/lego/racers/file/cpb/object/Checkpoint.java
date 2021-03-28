package com.lego.racers.file.cpb.object;

public class Checkpoint{

	private Direction direction;
	private Timing timing;
	private Location location;

	public Direction getDirection(){
		return this.direction;
	}

	public Location getLocation(){
		return this.location;
	}

	public Timing getTiming(){
		return this.timing;
	}

	public void setDirection(Direction direction){
		this.direction = direction;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public void setTiming(Timing timing){
		this.timing = timing;
	}

	@Override
	public String toString() {
		return "Checkpoint{" +
				"direction=" + direction +
				", timing=" + timing +
				", location=" + location +
				'}';
	}

}
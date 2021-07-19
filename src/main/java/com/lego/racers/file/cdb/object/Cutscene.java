package com.lego.racers.file.cdb.object;

public class Cutscene{

	private Cameras cameras;
	private int duration;
	private Models models;
	private AmbientLights ambientLights;
	private Events events;
	private DirectionalLights directionalLights;
	private int speed;

	public Cameras getCameras(){
		return this.cameras;
	}

	public void setCameras(Cameras cameras){
		this.cameras = cameras;
	}

	public int getDuration(){
		return this.duration;
	}

	public void setDuration(int duration){
		this.duration = duration;
	}

	public Models getModels(){
		return this.models;
	}

	public void setModels(Models models){
		this.models = models;
	}

	public AmbientLights getAmbientLights(){
		return this.ambientLights;
	}

	public void setAmbientLights(AmbientLights ambientLights){
		this.ambientLights = ambientLights;
	}

	public Events getEvents(){
		return this.events;
	}

	public void setEvents(Events events){
		this.events = events;
	}

	public DirectionalLights getDirectionalLights(){
		return this.directionalLights;
	}

	public void setDirectionalLights(DirectionalLights directionalLights){
		this.directionalLights = directionalLights;
	}

	public int getSpeed(){
		return this.speed;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "Cutscene{" +
				"cameras=" + cameras +
				", duration=" + duration +
				", models=" + models +
				", ambientLights=" + ambientLights +
				", events=" + events +
				", directionalLights=" + directionalLights +
				", speed=" + speed +
				'}';
	}

}
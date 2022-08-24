package com.lego.racers.file.cmb.object;

public class Wheels{

	private String materials;
	private String textures;
	private String _gdb;
	private String skeletons;
	private String animations;

	public String getMaterials(){
		return this.materials;
	}

	public void setMaterials(String materials){
		this.materials = materials;
	}

	public String getTextures(){
		return this.textures;
	}

	public void setTextures(String textures){
		this.textures = textures;
	}

	public String get_GDB(){
		return this._gdb;
	}

	public void set_GDB(String _gdb){
		this._gdb = _gdb;
	}

	public String getSkeletons(){
		return this.skeletons;
	}

	public void setSkeletons(String skeletons){
		this.skeletons = skeletons;
	}

	public String getAnimations(){
		return this.animations;
	}

	public void setAnimations(String animations){
		this.animations = animations;
	}

	@Override
	public String toString() {
		return "Wheels{" +
				"materials='" + materials + '\'' +
				", textures='" + textures + '\'' +
				", _gdb='" + _gdb + '\'' +
				", skeletons='" + skeletons + '\'' +
				", animations='" + animations + '\'' +
				'}';
	}

}
package com.lego.racers.jam;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class JAMListFolder{

	private String name;
	private int offset;

	public JAMListFolder(){}

	public JAMListFolder(String name,int offset){
		this.name = name;
		this.offset = offset;
	}

	public String getName(){
		return this.name;
	}

	public int getOffset(){
		return this.offset;
	}

	public byte[] toBytes(){
		ByteBuffer bb = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN);
		byte[] nameBytes = this.name==null?new byte[0]:this.name.getBytes();
		byte[] name = new byte[12];
		System.arraycopy(nameBytes,0,name,0,nameBytes.length);
		bb.put(name);
		bb.putInt(this.offset);
		return bb.array();
	}

	@Override
	public String toString() {
		return "JAMListFolder{" +
				"name='" + name + '\'' +
				", offset=" + offset +
				'}';
	}

	public static JAMListFolder from(ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		JAMListFolder listFolder = new JAMListFolder();
		byte[] name = new byte[12];
		bb.get(name);
		listFolder.name = new String(name).trim();
		listFolder.offset = bb.getInt();
		return listFolder;
	}

}
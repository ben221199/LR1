package com.lego.racers.file.jam;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class JAMListFile{

	private String name;
	private int offset;
	private int length;

	public JAMListFile(){}

	public JAMListFile(String name,int offset,int length){
		this.name = name;
		this.offset = offset;
		this.length = length;
	}

	public String getName(){
		return this.name;
	}

	public int getOffset(){
		return this.offset;
	}

	public int getLength(){
		return this.length;
	}

	public byte[] toBytes(){
		ByteBuffer bb = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN);
		byte[] nameBytes = this.name.getBytes();
		byte[] name = new byte[12];
		System.arraycopy(nameBytes,0,name,0,nameBytes.length);
		bb.put(name);
		bb.putInt(this.offset);
		bb.putInt(this.length);
		return bb.array();
	}

	@Override
	public String toString() {
		return "JAMListFile{" +
				"name='" + name + '\'' +
				", offset=" + offset +
				", length=" + length +
				'}';
	}

	public static JAMListFile from(ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		JAMListFile listFolder = new JAMListFile();
		byte[] name = new byte[12];
		bb.get(name);
		listFolder.name = new String(name).trim();
		listFolder.offset = bb.getInt();
		listFolder.length = bb.getInt();
		return listFolder;
	}

}
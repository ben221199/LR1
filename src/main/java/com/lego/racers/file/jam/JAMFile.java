package com.lego.racers.file.jam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class JAMFile extends JAMNodeFile{

	private byte[] magic;
	private JAMNode root;

	public byte[] toBytes(){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			baos.write(this.magic);
			baos.write(this.root.toBytes());
		}catch(IOException e){
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public static JAMFile from(byte[] bytes){
		return JAMFile.from(ByteBuffer.wrap(bytes));
	}

	public static JAMFile from(ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		JAMFile file = new JAMFile();
		byte[] magic = new byte[4];
		bb.get(magic);
		file.magic = magic;
		file.root = JAMNode.from(bb);
		return file;
	}

	public JAMNode getRootNode(){
		return this.root;
	}

	@Override
	public String toString() {
		return "JAMFile{" +
				"magic=" + Arrays.toString(magic) +
				", root=" + root +
				'}';
	}

}
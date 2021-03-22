package com.lego.racers.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryString extends BinaryToken{

	private String string;

	public BinaryString(String string){
		super(BinaryToken.TOKEN_STRING);
		this.string = string;
	}

	public String getString(){
		return this.string;
	}

	public byte[] toBytes(){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			baos.write(this.getToken());
			baos.write(this.string.getBytes());
			baos.write(0x00);
		}catch(IOException e){
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public static BinaryString from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		StringBuilder sb = new StringBuilder();
		byte b;
		while((b = bb.get())!=0x00){
			sb.append((char)b);
		}
		return new BinaryString(sb.toString());
	}

	@Override
	public String toString() {
		return "BinaryString{" +
				"string='" + string + '\'' +
				'}';
	}

}

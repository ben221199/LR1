package com.lego.racers.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class BinaryStruct extends BinaryToken{

	private byte id;
	private byte length;
	private byte[] tokens;

	public BinaryStruct(){
		super(BinaryToken.TOKEN_STRUCT);
	}

	public BinaryStruct(byte id,byte length,byte[] tokens){
		this();
		this.id = id;
		this.length = length;
		this.tokens = tokens;
	}

	public byte getId() {
		return this.id;
	}

	public byte[] getTokens() {
		return this.tokens;
	}

	@Override
	public byte[] toBytes(){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			baos.write(this.getToken());
			baos.write(this.id);
			baos.write(this.length);
			baos.write(this.tokens);
		}catch(IOException e){
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public static BinaryStruct from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		BinaryStruct struct = new BinaryStruct();
		struct.id = bb.get();
		struct.length = bb.get();
		struct.tokens = new byte[struct.length];
		for(int i=0;i<struct.tokens.length;i++){
			struct.tokens[i] = bb.get();
		}
		if(file!=null){
			file.addTemporaryStruct(struct);
		}
		return struct;
	}

	@Override
	public String toString() {
		return "BinaryStruct{" +
				"id=" + id +
				", length=" + length +
				", tokens=" + Arrays.toString(tokens) +
				'}';
	}

}
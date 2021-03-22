package com.lego.racers.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryLength extends BinaryToken{

	private BinaryLengthStart start;
	private BinaryIntegerSigned length;
	private BinaryLengthEnd end;

	public BinaryLength(BinaryLengthStart start,BinaryIntegerSigned length,BinaryLengthEnd end){
		super((byte) -1);
		this.start = start;
		this.length = length;
		this.end = end;
	}

	public BinaryIntegerSigned getLength(){
		return this.length;
	}

	public byte[] toBytes(){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			baos.write(this.start.toBytes());
			baos.write(this.length.toBytes());
			baos.write(this.end.toBytes());
		}catch(IOException e){
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public static BinaryLength from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		BinaryLengthStart start = new BinaryLengthStart();
		BinaryIntegerSigned length = (BinaryIntegerSigned) BinaryToken.from(file,bb);
		BinaryLengthEnd end = (BinaryLengthEnd) BinaryToken.from(file,bb);
		return new BinaryLength(start,length,end);
	}


	@Override
	public String toString() {
		return "BinaryLength{" +
				"start=" + start +
				", length=" + length +
				", end=" + end +
				'}';
	}

}
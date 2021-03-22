package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryIntegerSigned extends BinaryToken{

	private int integerSigned;

	public BinaryIntegerSigned(int integerSigned){
		super(BinaryToken.TOKEN_INTEGER_SIGNED);
		this.integerSigned = integerSigned;
	}

	public int getIntegerSigned(){
		return this.integerSigned;
	}

	public void setIntegerSigned(int integerSigned){
		this.integerSigned = integerSigned;
	}

	@Override
	public byte[] toBytes(){
		return ByteBuffer.allocate(5).order(ByteOrder.LITTLE_ENDIAN).put(this.getToken()).putInt(this.integerSigned).array();
	}

	public static BinaryIntegerSigned from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return new BinaryIntegerSigned(bb.getInt());
	}

	@Override
	public String toString() {
		return "BinaryIntegerSigned{" +
				"integerSigned=" + integerSigned +
				'}';
	}

}
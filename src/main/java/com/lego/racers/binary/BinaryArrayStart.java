package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryArrayStart extends BinaryToken{

	public BinaryArrayStart(){
		super(BinaryToken.TOKEN_LENGTH_START);
	}

	public static BinaryArrayStart from(BinaryFile file, ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return new BinaryArrayStart();
	}

	@Override
	public String toString() {
		return "BinaryArrayStart{" +
				'}';
	}

}

package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryArrayEnd extends BinaryToken{

	public BinaryArrayEnd(){
		super(BinaryToken.TOKEN_LENGTH_END);
	}

	public static BinaryArrayEnd from(BinaryFile file, ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return new BinaryArrayEnd();
	}

	@Override
	public String toString() {
		return "BinaryArrayEnd{" +
				'}';
	}

}

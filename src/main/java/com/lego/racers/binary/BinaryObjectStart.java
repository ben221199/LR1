package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryObjectStart extends BinaryToken{

	public BinaryObjectStart(){
		super(BinaryToken.TOKEN_OBJECT_START);
	}

	public static BinaryObjectStart from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return new BinaryObjectStart();
	}

	@Override
	public String toString() {
		return "BinaryObjectStart{" +
				'}';
	}

}

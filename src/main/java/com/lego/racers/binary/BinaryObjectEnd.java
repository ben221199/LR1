package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryObjectEnd extends BinaryToken{

	public BinaryObjectEnd(){
		super(BinaryToken.TOKEN_OBJECT_END);
	}

	public static BinaryObjectEnd from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return new BinaryObjectEnd();
	}

	@Override
	public String toString() {
		return "BinaryObjectEnd{" +
				'}';
	}

}

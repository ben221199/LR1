package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryLengthEnd extends BinaryToken{

	public BinaryLengthEnd(){
		super(BinaryToken.TOKEN_LENGTH_END);
	}

	public static BinaryLengthEnd from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return new BinaryLengthEnd();
	}

	@Override
	public String toString() {
		return "BinaryLengthEnd{" +
				'}';
	}

}

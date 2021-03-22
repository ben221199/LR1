package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryLengthStart extends BinaryToken{

	public BinaryLengthStart(){
		super(BinaryToken.TOKEN_LENGTH_START);
	}

	public static BinaryLengthStart from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return new BinaryLengthStart();
	}

	@Override
	public String toString() {
		return "BinaryLengthStart{" +
				'}';
	}

}

package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryStruct extends BinaryToken{

	private float _float;

	public BinaryStruct(float _float){
		super(BinaryToken.TOKEN_FLOAT);
		this._float = _float;
	}

	public float getFloat(){
		return this._float;
	}

	@Override
	public byte[] toBytes(){
		//TODO
		return new byte[0];
	}

	public static BinaryArray from(ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		//TODO
		return new BinaryArray(0);
	}

	@Override
	public String toString() {
		return "BinaryStruct{" +
				"_float=" + _float +
				'}';
	}

}
package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryFloat extends BinaryToken{

	private float _float;

	public BinaryFloat(float _float){
		super(BinaryToken.TOKEN_FLOAT);
		this._float = _float;
	}

	public float getFloat(){
		return this._float;
	}

	@Override
	public byte[] toBytes(){
		return ByteBuffer.allocate(5).order(ByteOrder.LITTLE_ENDIAN).put(this.getToken()).putFloat(this._float).array();
	}

	public static BinaryFloat from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return new BinaryFloat(bb.getFloat());
	}

	@Override
	public String toString() {
		return "BinaryFloat{" +
				"_float=" + _float +
				'}';
	}

}

package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryByteUnsigned extends BinaryToken{

	private byte _byte;

	public BinaryByteUnsigned(byte _byte){
		super(BinaryToken.TOKEN_BYTE_UNSIGNED);
		this._byte = _byte;
	}

	public byte getByteUnsigned(){
		return this._byte;
	}

	@Override
	public byte[] toBytes(){
		return ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).put(this.getToken()).put(this._byte).array();
	}

	public static BinaryByteUnsigned from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return new BinaryByteUnsigned(bb.get());
	}

	@Override
	public String toString() {
		return "BinaryByteUnsigned{" +
				"_byte=" + _byte +
				'}';
	}

}
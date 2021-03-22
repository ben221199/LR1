package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryByteSigned extends BinaryToken{

	private byte _byte;

	public BinaryByteSigned(byte _byte){
		super(BinaryToken.TOKEN_BYTE_SIGNED);
		this._byte = _byte;
	}

	public int getByteSigned(){
		return this._byte;
	}

	@Override
	public byte[] toBytes(){
		return ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).put(this.getToken()).put(this._byte).array();
	}

	public static BinaryByteSigned from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return new BinaryByteSigned(bb.get());
	}

	@Override
	public String toString() {
		return "BinaryByteSigned{" +
				"_byte=" + _byte +
				'}';
	}

}
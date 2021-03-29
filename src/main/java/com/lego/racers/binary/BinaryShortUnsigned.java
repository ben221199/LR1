package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryShortUnsigned extends BinaryToken{

	private short _short;

	public BinaryShortUnsigned(short _short){
		super(BinaryToken.TOKEN_SHORT_UNSIGNED);
		this._short = _short;
	}

	public short getShortUnsigned(){
		return this._short;
	}

	@Override
	public byte[] toBytes(){
		return ByteBuffer.allocate(3).order(ByteOrder.LITTLE_ENDIAN).put(this.getToken()).putShort(this._short).array();
	}

	public static BinaryShortUnsigned from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return new BinaryShortUnsigned(bb.getShort());
	}

	@Override
	public String toString() {
		return "BinaryShortUnsigned{" +
				"_short=" + _short +
				'}';
	}

}
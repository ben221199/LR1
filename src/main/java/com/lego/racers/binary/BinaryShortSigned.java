package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryShortSigned extends BinaryToken{

	private short _short;

	public BinaryShortSigned(short _short){
		super(BinaryToken.TOKEN_SHORT_SIGNED);
		this._short = _short;
	}

	public int getShortSigned(){
		return this._short;
	}

	@Override
	public byte[] toBytes(){
		return ByteBuffer.allocate(3).order(ByteOrder.LITTLE_ENDIAN).put(this.getToken()).putShort(this._short).array();
	}

	public static BinaryShortSigned from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return new BinaryShortSigned(bb.getShort());
	}

	@Override
	public String toString() {
		return "BinaryShortSigned{" +
				"_short=" + _short +
				'}';
	}

}
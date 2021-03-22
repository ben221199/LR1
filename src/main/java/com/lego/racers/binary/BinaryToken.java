package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryToken{

	public static final byte TOKEN_STRING = 0x02;
	public static final byte TOKEN_FLOAT = 0x03;
	public static final byte TOKEN_INTEGER_SIGNED = 0x04;
	public static final byte TOKEN_OBJECT_START = 0x05;
	public static final byte TOKEN_OBJECT_END = 0x06;
	public static final byte TOKEN_LENGTH_START = 0x07;
	public static final byte TOKEN_LENGTH_END = 0x08;

	public static final byte TOKEN_BYTE_SIGNED = 0x0B;
	public static final byte TOKEN_BYTE_UNSIGNED = 0x0C;
	public static final byte TOKEN_SHORT_SIGNED = 0x0D;
	public static final byte TOKEN_SHORT_UNSIGNED = 0x0E;

	public static final byte TOKEN_ARRAY = 0x14;
	public static final byte TOKEN_STRUCT = 0x16;

	private byte token;

	public BinaryToken(byte token){
		this.token = token;
	}

	public byte getToken(){
		return this.token;
	}

	public byte[] toBytes(){
		return new byte[]{this.token};
	}

	public static BinaryToken from(ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		byte token = bb.get();
		bb.position(bb.position()-1);
		switch(token){
			case 0x02:return BinaryString.from(bb);
			case 0x03:return BinaryFloat.from(bb);
			case 0x04:return BinaryIntegerSigned.from(bb);
			case 0x05:return BinaryObjectStart.from(bb);
			case 0x06:return BinaryObjectEnd.from(bb);
			case 0x07:return BinaryLengthStart.from(bb);
			case 0x08:return BinaryLengthEnd.from(bb);
			//
			case 0x0B:return BinaryByteSigned.from(bb);
			case 0x0C:return BinaryByteUnsigned.from(bb);
			case 0x0D:return BinaryShortSigned.from(bb);
			case 0x0E:return BinaryShortUnsigned.from(bb);
			//
			case 0x14:return BinaryArray.from(bb);
			case 0x16:return BinaryStruct.from(bb);
		}
		bb.position(bb.position()+1);
		return new BinaryToken(token);
	}

	@Override
	public String toString() {
		return "BinaryToken{" +
				"token=" + token +
				'}';
	}

}
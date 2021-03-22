package com.yocto.io;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BetterOutputStream extends BufferedOutputStream{

	private OutputStream out;

	public BetterOutputStream(OutputStream out){
		super(out);
		this.out = out;
	}

	public final synchronized void writeBoolean(boolean value) throws IOException{
		this.writeByte((byte) (value?0x01:0x00));
	}

	public final synchronized void writeByte(byte value) throws IOException{
		this.write((byte) (0xFF & (value >> 0)));
	}

	public final synchronized void writeDoubleBE(double value) throws IOException{
		this.writeLongBE(Double.doubleToLongBits(value));
	}

	public final synchronized void writeFloatBE(float value) throws IOException{
		this.writeIntegerBE(Float.floatToIntBits(value));
	}

	public final synchronized void writeLongBE(long value) throws IOException{
		this.write((byte) (0xFF & (value >> 56)));
		this.write((byte) (0xFF & (value >> 48)));
		this.write((byte) (0xFF & (value >> 40)));
		this.write((byte) (0xFF & (value >> 32)));
		this.write((byte) (0xFF & (value >> 24)));
		this.write((byte) (0xFF & (value >> 16)));
		this.write((byte) (0xFF & (value >> 8)));
		this.write((byte) (0xFF & (value >> 0)));
	}

	public final synchronized void writeIntegerBE(int value) throws IOException{
		this.write((byte) (0xFF & (value >> 24)));
		this.write((byte) (0xFF & (value >> 16)));
		this.write((byte) (0xFF & (value >> 8)));
		this.write((byte) (0xFF & (value >> 0)));
	}

	public final synchronized void writeIntegerLE(int value) throws IOException{
		this.write((byte) (0xFF & (value >> 0)));
		this.write((byte) (0xFF & (value >> 8)));
		this.write((byte) (0xFF & (value >> 16)));
		this.write((byte) (0xFF & (value >> 24)));
	}

	public final synchronized void writeShortBE(short value) throws IOException{
		this.write((byte) (0xFF & (value >> 8)));
		this.write((byte) (0xFF & (value >> 0)));
	}

	public final synchronized void writeShortLE(short value) throws IOException{
		this.write((byte) (0xFF & (value >> 0)));
		this.write((byte) (0xFF & (value >> 8)));
	}

}
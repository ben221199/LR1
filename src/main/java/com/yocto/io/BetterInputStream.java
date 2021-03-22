package com.yocto.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BetterInputStream extends BufferedInputStream{

	private InputStream in;

	private byte[] buffer;

	public BetterInputStream(InputStream in){
		super(in);//TMP, should be non Buffered BetterInput
		this.in = new BufferedInputStream(in);
	}

	public final synchronized byte readByte() throws IOException{
		return (byte) this.read();
	}

	public final synchronized byte[] readByteArray(int length) throws IOException{
		byte[] buffer = new byte[length];
		this.read(buffer);
		return buffer;
	}

//	@Override
//	public int available() throws IOException{
//		return this.in.available();
//	}
//
//	@Override
//	public synchronized void mark(int readlimit){
//		this.in.mark(readlimit);
//	}
//
//	@Override
//	public boolean markSupported(){
//		return true;
//	}
//
//	@Override
//	public int read() throws IOException{
//		return this.in.read();
//	}
//
//	@Override
//	public int read(byte[] b) throws IOException{
//		return this.in.read(b);
//	}
//
//	@Override
//	public synchronized void reset() throws IOException{
//		this.in.reset();
//	}

	public final synchronized boolean readBoolean() throws IOException{
		return this.readByte()!=0x00;
	}

	public final synchronized double readDoubleBE() throws IOException{
		return Double.longBitsToDouble(this.readLongBE());
	}

	public final synchronized float readFloatBE() throws IOException{
		return Float.intBitsToFloat(this.readIntegerBE());
	}

	public final synchronized long readLongBE() throws IOException{
		long A = this.read() & 0xFF;
		long B = this.read() & 0xFF;
		long C = this.read() & 0xFF;
		long D = this.read() & 0xFF;
		long E = this.read() & 0xFF;
		long F = this.read() & 0xFF;
		long G = this.read() & 0xFF;
		long H = this.read() & 0xFF;
		return ((A << 56) | (B << 48) | (C << 40) | (D << 32) | (E << 24) | (F << 16) | (G << 8) | (H << 0));
	}

	public final synchronized int readIntegerBE() throws IOException{
		int A = this.read() & 0xFF;
		int B = this.read() & 0xFF;
		int C = this.read() & 0xFF;
		int D = this.read() & 0xFF;
		return ((A << 24) | (B << 16) | (C << 8) | (D << 0));
	}

	public final synchronized int readIntegerLE() throws IOException{
		int A = this.read() & 0xFF;
		int B = this.read() & 0xFF;
		int C = this.read() & 0xFF;
		int D = this.read() & 0xFF;
		return ((D << 24) | (C << 16) | (B << 8) | (A << 0));
	}

	public final synchronized short readShortBE() throws IOException{
		int A = this.read() & 0xFF;
		int B = this.read() & 0xFF;
		return (short) ((A << 8) | (B << 0));
	}

	public final synchronized short readShortLE() throws IOException{
		int A = this.read() & 0xFF;
		int B = this.read() & 0xFF;
		return (short) ((B << 8) | (A << 0));
	}

}
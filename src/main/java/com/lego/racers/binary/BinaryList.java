package com.lego.racers.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class BinaryList extends BinaryContainer{

	private byte type;

	public BinaryList(byte type){
		super(BinaryToken.TOKEN_ARRAY);
		this.type = type;
	}

	public byte getType(){
		return this.type;
	}

	public BinaryStruct getStructByToken(byte token){
		for(BinaryToken t : this.tokens){
			if(t instanceof BinaryStruct){
				if(((BinaryStruct) t).getId()==token){
					return (BinaryStruct) t;
				}
			}
			if(t instanceof BinaryStructInstance){
				return ((BinaryStructInstance) t).getStructByToken(token);
			}
			if(t instanceof BinaryList){
				return ((BinaryList) t).getStructByToken(token);
			}
			if(t instanceof BinaryObject){
				return ((BinaryObject) t).getStructByToken(token);
			}
		}
		return null;
	}

	@Override
	public byte[] toBytes(){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			baos.write(this.getToken());
			baos.write(ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort((short) this.tokens.size()).array());
			baos.write(this.type);
			for(BinaryToken t : this.tokens){
				byte[] out = t.toBytes();
				if(t.getClass().equals(BinaryToken.class)){
					baos.write(out);
				}else{
					baos.write(Arrays.copyOfRange(out,1,out.length));
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public static BinaryList from(BinaryFile file, ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		short length = bb.getShort();
		BinaryList arr = new BinaryList(bb.get());
		for(int i=0;i<length;i++){
			arr.tokens.add(BinaryToken.from(file,bb,arr.type));
		}
		return arr;
	}

	@Override
	public String toString() {
		return "BinaryList{" +
				"type=" + type +
				", tokens=" + tokens +
				'}';
	}

}
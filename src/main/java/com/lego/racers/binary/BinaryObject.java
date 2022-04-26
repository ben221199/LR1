package com.lego.racers.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryObject extends BinaryContainer{

	private BinaryObjectStart start;
	private BinaryObjectEnd end;

	public BinaryObject(){
		super((byte) -1);
		this.start = new BinaryObjectStart();
		this.end = new BinaryObjectEnd();
	}

	public BinaryObject(BinaryObjectStart start,BinaryObjectEnd end){
		this();
		this.start = start;
		this.end = end;
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



	public byte[] toBytes(){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			baos.write(this.start.toBytes());
			for(BinaryToken token : this.tokens){
				baos.write(token.toBytes());
			}
			baos.write(this.end.toBytes());
		}catch(IOException e){
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public static BinaryObject from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);

		BinaryObject object = new BinaryObject();
		object.start = new BinaryObjectStart();
		while(bb.hasRemaining()){
			BinaryToken token = BinaryToken.from(file,bb);
			if(token instanceof BinaryObjectStart){
				object.tokens.add(BinaryObject.from(file,bb));
				continue;
			}
			if(token instanceof BinaryArrayStart){
				object.tokens.add(BinaryArray.from(file,bb));
				continue;
			}
			if(token instanceof BinaryObjectEnd){
				object.end = (BinaryObjectEnd) token;
				break;
			}
			object.tokens.add(token);
		}
		return object;
	}

	@Override
	public String toString() {
		return "BinaryObject{" +
				"tokens=" + tokens +
				'}';
	}

}
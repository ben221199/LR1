package com.lego.racers.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class BinaryFile{

	private List<BinaryToken> tokens = new ArrayList<>();
	private List<BinaryStruct> structs = new ArrayList<>();

	public void addTemporaryStruct(BinaryStruct struct){
		this.structs.add(struct);
	}

	public BinaryStruct getStructByToken(byte token){
		for(BinaryStruct t : this.structs){
			if(t.getId()==token){
				return t;
			}
		}
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

	public List<BinaryToken> getTokens(){
		return this.tokens;
	}

	public byte[] toBytes(){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			for(BinaryToken token : this.tokens){
				baos.write(token.toBytes());
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public static BinaryFile from(byte[] bytes){
		return BinaryFile.from(ByteBuffer.wrap(bytes));
	}

	public static BinaryFile from(ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		BinaryFile file = new BinaryFile();
		file.tokens.addAll(BinaryObject.from(file,bb).getTokens());
		file.structs.clear();
		return file;
	}

	@Override
	public String toString() {
		return "BinaryFile{" +
				"tokens=" + tokens +
				", structs=" + structs +
				'}';
	}

}
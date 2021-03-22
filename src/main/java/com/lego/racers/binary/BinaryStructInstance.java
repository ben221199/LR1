package com.lego.racers.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryStructInstance extends BinaryToken{

	private List<BinaryToken> tokens = new ArrayList<>();

	public BinaryStructInstance(){
		super(BinaryToken.TOKEN_STRUCT);
	}

	public BinaryStructInstance(byte id){
		super(id);
	}

	public List<BinaryToken> getTokens(){
		return this.tokens;
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
			if(t instanceof BinaryArray){
				return ((BinaryArray) t).getStructByToken(token);
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
			for(BinaryToken t : this.tokens){
				if(!t.getClass().equals(BinaryToken.class)){
					byte[] out = t.toBytes();
					baos.write(Arrays.copyOfRange(out,1,out.length));
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public static BinaryStructInstance from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		byte token = bb.get();
		BinaryStruct struct = file.getStructByToken(token);
		BinaryStructInstance instance = new BinaryStructInstance(token);
		for(byte b : struct.getTokens()){
			instance.tokens.add(BinaryToken.from(file,bb,b));
		}
		return instance;
	}

	@Override
	public String toString() {
		return "BinaryStructInstance{" +
				"tokens=" + tokens +
				'}';
	}

}
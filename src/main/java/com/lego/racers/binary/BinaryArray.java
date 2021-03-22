package com.lego.racers.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class BinaryArray extends BinaryToken{

	private List<BinaryToken> tokens = new ArrayList<>();

	public BinaryArray(float _float){
		super(BinaryToken.TOKEN_FLOAT);
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
		//TODO
		return new byte[0];
	}

	public static BinaryArray from(BinaryFile file,ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		//TODO
		return new BinaryArray(0);
	}

	@Override
	public String toString() {
		return "BinaryArray{" +
				"tokens=" + tokens +
				'}';
	}

}
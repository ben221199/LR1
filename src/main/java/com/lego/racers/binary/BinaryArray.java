package com.lego.racers.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class BinaryArray extends BinaryToken{

	private BinaryArrayStart start;
	private List<BinaryToken> tokens = new ArrayList<>();
	private BinaryArrayEnd end;

	public BinaryArray(){
		super((byte) -1);
		this.start = new BinaryArrayStart();
		this.end = new BinaryArrayEnd();
	}

	public BinaryArray(BinaryArrayStart start,BinaryArrayEnd end){
		this();
		this.start = start;
		this.end = end;
	}

	public List<BinaryToken> getTokens(){
		return this.tokens;
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

	public static BinaryArray from(BinaryFile file, ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);

		BinaryArray length = new BinaryArray();
		length.start = new BinaryArrayStart();
		while(bb.hasRemaining()){
			BinaryToken token = BinaryToken.from(file,bb);
			if(token instanceof BinaryObjectStart){
				length.tokens.add(BinaryObject.from(file,bb));
				continue;
			}
			if(token instanceof BinaryArrayStart){
				length.tokens.add(BinaryArray.from(file,bb));
				continue;
			}
			if(token instanceof BinaryArrayEnd){
				length.end = (BinaryArrayEnd) token;
				break;
			}
			length.tokens.add(token);
		}
		return length;
	}


	@Override
	public String toString() {
		return "BinaryArray{" +
				"start=" + start +
				", tokens=" + tokens +
				", end=" + end +
				'}';
	}

}
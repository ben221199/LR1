package com.lego.racers.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class BinaryObject extends BinaryToken{

	private BinaryObjectStart start;
	private List<BinaryToken> tokens = new ArrayList<>();
	private BinaryObjectEnd end;

	public BinaryObject(){
		super(BinaryToken.TOKEN_OBJECT_START);
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

	public static BinaryObject from(ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		BinaryObject object = new BinaryObject();
		object.start = (BinaryObjectStart) BinaryToken.from(bb);
		while(bb.hasRemaining()){
			BinaryToken token = BinaryToken.from(bb);
			if(token instanceof BinaryObjectStart){
				bb.position(bb.position()-1);
				object.tokens.add(BinaryObject.from(bb));
				continue;
			}
			if(token instanceof BinaryLengthStart){
				bb.position(bb.position()-1);
				object.tokens.add(BinaryLength.from(bb));
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
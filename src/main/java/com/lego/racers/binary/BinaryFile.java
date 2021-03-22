package com.lego.racers.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class BinaryFile{

	private List<BinaryToken> tokens = new ArrayList<>();

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
		while(bb.hasRemaining()){
			BinaryToken token = BinaryToken.from(bb);
			if(token instanceof BinaryObjectStart){
				bb.position(bb.position()-1);
				file.tokens.add(BinaryObject.from(bb));
				continue;
			}
			if(token instanceof BinaryLengthStart){
				bb.position(bb.position()-1);
				file.tokens.add(BinaryLength.from(bb));
				continue;
			}
			file.tokens.add(token);
		}
		return file;
	}

	@Override
	public String toString() {
		return "BinaryFile{" +
				"tokens=" + tokens +
				'}';
	}

}
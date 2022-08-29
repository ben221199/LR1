package com.lego.racers.file.srf;

import com.lego.racers.file.jam.JAMNodeFile;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SRFFile extends JAMNodeFile{

	private List<String> strings = new ArrayList<>();

	public List<String> getStrings(){
		return this.strings;
	}

	public byte[] toBytes(){
		short charCount = 0;
		String[] stringArr = this.strings.toArray(new String[0]);
		for(int i=0;i<stringArr.length;i++){
			stringArr[i] += "\0";
		}
		short stringCount = (short) stringArr.length;
		for(String str : stringArr){
			charCount += str.getBytes(StandardCharsets.UTF_16LE).length;
		}
		ByteBuffer bb = ByteBuffer.allocate(2+2+stringCount*2+charCount*2).order(ByteOrder.LITTLE_ENDIAN);
		bb.putShort(stringCount);
		bb.putShort(charCount);
		short index = 0;
		for(String str : stringArr){
			bb.putShort(index);
			index += str.getBytes(StandardCharsets.UTF_16LE).length/2;
		}
		for(String str : stringArr){
			bb.put(str.getBytes(StandardCharsets.UTF_16LE));
		}
		return bb.array();
	}

	public static SRFFile from(byte[] bytes){
		return SRFFile.from(ByteBuffer.wrap(bytes));
	}

	public static SRFFile from(ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		short stringCount = bb.getShort();
		short charCount = bb.getShort();
		short[] indices = new short[stringCount];
		for(int i=0;i<indices.length;i++){
			indices[i] = bb.getShort();
		}
		byte[] stringData = new byte[charCount*2];
		bb.get(stringData);
		ByteBuffer stringBuffer = ByteBuffer.wrap(stringData).order(ByteOrder.LITTLE_ENDIAN);
		SRFFile srf = new SRFFile();
		for(short index : indices){
			stringBuffer.position(index*2);
			StringBuilder sb = new StringBuilder();
			char c;
			while((c = stringBuffer.getChar())!=0){
				sb.append(c);
			}
			srf.strings.add(sb.toString());
		}
		return srf;
	}

	@Override
	public String toString() {
		return "SRFFile{" +
				"strings=" + strings +
				'}';
	}

}
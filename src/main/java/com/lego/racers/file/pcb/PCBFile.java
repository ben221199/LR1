package com.lego.racers.file.pcb;

import com.lego.racers.binary.BinaryFile;
import com.lego.racers.file.jam.JAMNodeFile;

public class PCBFile extends JAMNodeFile{

	public byte[] toBytes(){
		//TODO
		return new byte[0];
	}

	public static PCBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		PCBFile adb = new PCBFile();
//		for(int i=0;i<bin.getTokens().size();i++){
//			BinaryToken token = bin.getTokens().get(i);
//			if(token.getToken()==GDBFile.BLOCK_MATERIALS){
//				GDBFile.initMaterials(gdb,(BinaryObject) bin.getTokens().get(i+2));
//			}
//			if(token.getToken()==GDBFile.BLOCK_VERTICES_NORMAL){
//				int j = 2;
//				while(bin.getTokens().get(i+j) instanceof BinaryStruct){
//					j++;
//				}
//				GDBFile.initVerticesNormal(gdb,(BinaryObject) bin.getTokens().get(i+j));
//			}
//			if(token.getToken()==GDBFile.BLOCK_VERTICES_COLORED){
//				int j = 2;
//				while(bin.getTokens().get(i+j) instanceof BinaryStruct){
//					j++;
//				}
//				GDBFile.initVerticesColored(gdb,(BinaryObject) bin.getTokens().get(i+j));
//			}
//			if(token.getToken()==GDBFile.BLOCK_INDICES){
//				int j = 2;
//				while(bin.getTokens().get(i+j) instanceof BinaryStruct){
//					j++;
//				}
//				GDBFile.initIndices(gdb,(BinaryObject) bin.getTokens().get(i+j));
//			}
//			if(token.getToken()==GDBFile.BLOCK_INDICES_META){
//				int j = 2;
//				while(bin.getTokens().get(i+j) instanceof BinaryStruct){
//					j++;
//				}
//				GDBFile.initIndicesMetas(gdb,(BinaryObject) bin.getTokens().get(i+j));
//			}
//			if(token.getToken()==GDBFile.PROPERTY_SCALE){
//				BinaryFloat f = (BinaryFloat) bin.getTokens().get(i+1);
//				gdb.scale = f.getFloat();
//			}
//		}
		return adb;
	}

}

package com.lego.racers.file.msb;

import com.lego.racers.binary.BinaryFile;

public class MSBFile{

	public static MSBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		MSBFile adb = new MSBFile();
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

	@Override
	public String toString() {
		return "MSBFile{" +
				'}';
	}

}

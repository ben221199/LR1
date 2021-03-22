package com.lego.racers.jam;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class JAMList{

	private List<JAMListFile> files = new ArrayList<>();
	private List<JAMListFolder> folders = new ArrayList<>();

	public int calculateSize(){
		return 8+this.files.size()*20+this.folders.size()*16;
	}

	public List<JAMListFile> getFiles(){
		return this.files;
	}

	public List<JAMListFolder> getFolders(){
		return this.folders;
	}

	public byte[] toBytes(){
		ByteBuffer bb = ByteBuffer.allocate(this.calculateSize()).order(ByteOrder.LITTLE_ENDIAN);
		bb.putInt(this.files.size());
		for(JAMListFile listFile : this.files){
			bb.put(listFile.toBytes());
		}
		bb.putInt(this.folders.size());
		for(JAMListFolder listFolder : this.folders){
			bb.put(listFolder.toBytes());
		}
		return bb.array();
	}

	@Override
	public String toString(){
		return "JAMList{" +
				"files=" + files +
				", folders=" + folders +
				'}';
	}

	public static JAMList from(ByteBuffer bb){
		bb.order(ByteOrder.LITTLE_ENDIAN);
		JAMList list = new JAMList();
		int fileCount = bb.getInt();
		for(int i=0;i<fileCount;i++){
			list.files.add(JAMListFile.from(bb));
		}
		int folderCount = bb.getInt();
		for(int i=0;i<folderCount;i++){
			list.folders.add(JAMListFolder.from(bb));
		}
		return list;
	}

}
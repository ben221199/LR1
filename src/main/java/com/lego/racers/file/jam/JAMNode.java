package com.lego.racers.file.jam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JAMNode{

	private Map<String,byte[]> files = new LinkedHashMap<>();
	private Map<String,JAMNode> folders = new LinkedHashMap<>();

	public int calculateSize(){
		return this.calculateListSize()+this.calculateFileSize()+this.calculateFolderSize();
	}

	public int calculateFileSize(){
		int size = 0;
		for(byte[] bytes : this.files.values()){
			size += bytes.length;
		}
		return size;
	}

	public int calculateFolderSize(){
		int size = 0;
		for(JAMNode node : this.folders.values()){
			size += node.calculateSize();
		}
		return size;
	}

	public int calculateHeaderSize(){
		return this.calculateOnlyFolderSize()+(0x100-(this.calculateOnlyFolderSize())%0x100);
	}

	public int calculateOnlyFolderSize(){
		int size = 0;
		size += this.calculateListSize();
		for(JAMNode node : this.folders.values()){
			size += node.calculateOnlyFolderSize();
		}
		return size;
	}

	public byte[] toBytes(){
		return this.toBytes(4,this.calculateHeaderSize());
	}

	public byte[] toBytes(int offset,int headerSize){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			List<byte[]> files = new ArrayList<>();
			baos.write(this.toBytes(offset,files,headerSize));
			byte[] exclamation = new byte[0x100-(offset+baos.size())%0x100];
			Arrays.fill(exclamation,(byte) '!');
			baos.write(exclamation);
			for(byte[] bytes : files){
				baos.write(bytes);
				baos.write(new byte[bytes.length%2]);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public byte[] toBytes(int offset,List<byte[]> files,int headerSize){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			offset += this.calculateListSize();
			JAMList list = this.toList(offset,files,headerSize);
			baos.write(list.toBytes());
			for(JAMNode node : this.folders.values()){
				baos.write(node.toBytes(offset,files,headerSize));
				offset += node.calculateOnlyFolderSize();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public int calculateListSize(){
		return 8+this.files.size()*20+this.folders.size()*16;
	}

	private int getOffset(List<byte[]> files,int headerSize){
		int offset = headerSize;
		for(byte[] file : files){
			offset += file.length+file.length%2;
		}
		return offset;
	}

	private JAMList toList(int folderOffset,List<byte[]> files,int headerSize){
		JAMList list = new JAMList();
		for(Map.Entry<String,byte[]> entry : this.files.entrySet()){
			int offset = getOffset(files,headerSize);
			files.add(entry.getValue());
			JAMListFile listFile = new JAMListFile(entry.getKey(),offset,entry.getValue().length);
			list.getFiles().add(listFile);
		}
		for(Map.Entry<String,JAMNode> entry : this.folders.entrySet()){
			JAMListFolder listFolder = new JAMListFolder(entry.getKey(),folderOffset);
			folderOffset += entry.getValue().calculateOnlyFolderSize();
			list.getFolders().add(listFolder);
		}
		return list;
	}

	public static JAMNode from(ByteBuffer bb){
		JAMNode node = new JAMNode();
		JAMList list = JAMList.from(bb);
		for(JAMListFile listFile : list.getFiles()){
			bb.position(listFile.getOffset());
			byte[] content = new byte[listFile.getLength()];
			bb.get(content);
			node.files.put(listFile.getName(),content);
		}
		for(JAMListFolder listFolder : list.getFolders()){
			bb.position(listFolder.getOffset());
			node.folders.put(listFolder.getName(),JAMNode.from(bb));
		}
		return node;
	}

	public JAMNode getFolder(String name){
		return this.folders.get(name);
	}

	public JAMNode setFolder(String name,JAMNode node){
		return this.folders.put(name,node);
	}

	public JAMNode removeFolder(String name){
		return this.folders.remove(name);
	}

	public Map<String,JAMNode> getFolders(){
		return this.folders;
	}

	public byte[] getFile(String name){
		return this.files.get(name);
	}

	public byte[] setFile(String name,byte[] content){
		return this.files.put(name,content);
	}

	public JAMNodeFile getNodeFile(String name){
		return JAMNodeFile.fromBytes(name,this.getFile(name));
	}

	public JAMNodeFile setNodeFile(String name,JAMNodeFile content){
		this.setFile(name,content.toBytes());
		return content;
	}

	public byte[] removeFile(String name){
		return this.files.remove(name);
	}

	public Map<String,byte[]> getFiles(){
		return this.files;
	}

	public Map<String,JAMNodeFile> getNodeFiles(){
		Map<String,JAMNodeFile> map = new LinkedHashMap<>();
		for(Map.Entry<String,byte[]> me : this.getFiles().entrySet()){
			map.put(me.getKey(),JAMNodeFile.fromBytes(me.getKey(),me.getValue()));
		}
		return map;
	}

	@Override
	public String toString() {
		return "JAMNode{" +
				"files=" + files +
				", folders=" + folders +
				'}';
	}

}
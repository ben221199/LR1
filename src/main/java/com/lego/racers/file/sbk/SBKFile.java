package com.lego.racers.file.sbk;

import com.lego.racers.file.jam.JAMNodeFile;

import java.util.ArrayList;
import java.util.List;

public class SBKFile extends JAMNodeFile{

	private List<String> lines = new ArrayList<>();

	public List<String> getLines(){
		return this.lines;
	}

	public byte[] toBytes(){
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n");
		sb.append(this.lines.size());
		sb.append("\r\n");
		for(String line : this.lines){
			sb.append(line).append("\r\n");
		}
		return sb.toString().getBytes();
	}

	public static SBKFile from(byte[] bytes){
		return SBKFile.from(new String(bytes));
	}

	public static SBKFile from(String str){
		SBKFile sbk = new SBKFile();
		String[] parts = str.split("\\r\\n");
		int i = 1;
		int amount = Integer.parseInt(parts[i++]);
		for(int j=i;j<amount+i;j++){
			sbk.lines.add(parts[j]);
		}
		return sbk;
	}

	@Override
	public String toString() {
		return "SBKFile{" +
				"lines=" + lines +
				'}';
	}

}
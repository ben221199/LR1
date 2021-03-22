package com.lego.racers.jam;

import com.yocto.io.BetterInputStream;
import com.yocto.io.BetterOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class Main{

	public static void main(String... args) throws IOException {
		BetterInputStream dis = new BetterInputStream(new FileInputStream("C:\\Users\\Ben\\Downloads\\LEGON64\\LEGON64.JAM"));
		BetterOutputStream dos = new BetterOutputStream(new FileOutputStream("C:\\Users\\Ben\\Downloads\\LEGON64\\LEGON64_OUT.JAM"));

//		BetterInputStream dis = new BetterInputStream(new FileInputStream("C:\\Users\\Ben\\Downloads\\JAMExtractor-1.0.2-Win64\\LEGO.JAM"));
//		BetterOutputStream dos = new BetterOutputStream(new FileOutputStream("C:\\Users\\Ben\\Downloads\\JAMExtractor-1.0.2-Win64\\LEGO_OUT.JAM"));
//		BetterInputStream dis2 = new BetterInputStream(new FileInputStream("C:\\Users\\Ben\\Downloads\\JAMExtractor-1.0.2-Win64\\LEGO_OUT.JAM"));

		JAMFile file = JAMFile.from(dis.readByteArray(dis.available()));
		printDepth(file.getRootNode(),0);

		dos.write(file.toBytes());
		dos.flush();

//		JAMFile file2 = JAMFile.from(dis2.readByteArray(dis2.available()));
//		printDepth(file2.getRootNode(),0);
	}

	public static void printDepth(JAMNode node,int depth){
		String prefix = "";
		for(int i=0;i<depth;i++){
			prefix += "---";
		}
		for(Map.Entry<String,byte[]> name : node.getFiles().entrySet()){
			System.out.println(prefix+""+name.getKey()+" :: "+name.getValue().length+" "+new String(name.getValue()).contains("\\<KLL"));
		}
		for(Map.Entry<String,JAMNode> name : node.getFolders().entrySet()){
			System.out.println(prefix+""+name.getKey()+" <> "+name.getValue().calculateSize());
			printDepth(name.getValue(),depth+1);
		}
	}

}
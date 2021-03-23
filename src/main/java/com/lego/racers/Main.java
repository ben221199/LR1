package com.lego.racers;

import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryObjectEnd;
import com.lego.racers.binary.BinaryObjectStart;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryStructInstance;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.jam.JAMFile;
import com.lego.racers.file.jam.JAMNode;
import com.yocto.io.BetterInputStream;
import com.yocto.io.BetterOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class Main{

	public static void main(String... args) throws IOException{
//		System.setOut(new PrintStream(new FileOutputStream("C:\\Users\\Ben\\Downloads\\JAMExtractor-1.0.2-Win64\\LOG.TXT")));

//		BetterInputStream dis = new BetterInputStream(new FileInputStream("C:\\Users\\Ben\\Downloads\\LEGON64\\LEGON64.JAM"));
//		BetterOutputStream dos = new BetterOutputStream(new FileOutputStream("C:\\Users\\Ben\\Downloads\\LEGON64\\LEGON64_OUT.JAM"));

		BetterInputStream dis = new BetterInputStream(new FileInputStream("C:\\Users\\Ben\\Downloads\\JAMExtractor-1.0.2-Win64\\LEGO.JAM"));
		BetterOutputStream dos = new BetterOutputStream(new FileOutputStream("C:\\Users\\Ben\\Downloads\\JAMExtractor-1.0.2-Win64\\LEGO_OUT.JAM"));
//		BetterInputStream dis2 = new BetterInputStream(new FileInputStream("C:\\Users\\Ben\\Downloads\\JAMExtractor-1.0.2-Win64\\LEGO_OUT.JAM"));


		JAMFile file = JAMFile.from(dis.readByteArray(dis.available()));
		printDepth(file.getRootNode(),0);

		JAMNode MENUDATA = file.getRootNode().getFolder("MENUDATA");
		fix_MENUDATA_SINGRACE0IDB(MENUDATA);
		fix_MENUDATA_LEGORACE0RCB(MENUDATA);
		fix_MENUDATA_LEGORACE0CRB(MENUDATA);
		MENUDATA.setFile("KAAS.BMP",MENUDATA.getFile("ADVENTUR.BMP"));
		dos.write(file.toBytes());
		dos.flush();



//		byte[] legoRaceContent = ;
//		printDepth(file.getRootNode(),0);

//		BetterInputStream dis = new BetterInputStream(new FileInputStream("C:\\Users\\Ben\\Downloads\\JAMExtractor-1.0.2-Win64\\LEGO\\MENUDATA\\LEGORACE.RCB"));
//		BetterOutputStream dos = new BetterOutputStream(new FileOutputStream("C:\\Users\\Ben\\Downloads\\JAMExtractor-1.0.2-Win64\\LEGO\\MENUDATA\\LEGORACE_OUT.RCB"));
//		BinaryFile file = BinaryFile.from(dis.readByteArray(dis.available()));
//		System.err.println(file);
//		dos.write(file.toBytes());
//		dos.flush();

//		dos.write(file.toBytes());
//		dos.flush();

//		JAMFile file2 = JAMFile.from(dis2.readByteArray(dis2.available()));
	}

	private static void fix_MENUDATA_SINGRACE0IDB(JAMNode MENUDATA) throws IOException{
		BetterOutputStream dos2 = new BetterOutputStream(new FileOutputStream("C:\\Users\\Ben\\Downloads\\JAMExtractor-1.0.2-Win64\\SINGRACE.IDB"));


//		System.err.println("FIX");
		BinaryFile binFile = BinaryFile.from(MENUDATA.getFile("SINGRACE.IDB"));
//		System.err.println("LOADED "+binFile);

		BinaryArray length = (BinaryArray) binFile.getTokens().get(1);
		BinaryIntegerSigned binaryInteger = (BinaryIntegerSigned) length.getTokens().get(0);
		binaryInteger.setIntegerSigned(binaryInteger.getIntegerSigned()+1);

		BinaryObject object = (BinaryObject) binFile.getTokens().get(2);
		object.getTokens().add(new BinaryToken((byte) 0x27));
		object.getTokens().add(new BinaryString("kaas"));
		BinaryObject track = new BinaryObject(new BinaryObjectStart(),new BinaryObjectEnd());
		object.getTokens().add(track);
		track.getTokens().add(new BinaryToken((byte) 0x29));
		BinaryStructInstance instance = new BinaryStructInstance((byte) 0x17);
		instance.getTokens().add(new BinaryToken((byte) 0x2B));
		instance.getTokens().add(new BinaryIntegerSigned(10));
		instance.getTokens().add(new BinaryIntegerSigned(10));
		instance.getTokens().add(new BinaryIntegerSigned(114));
		track.getTokens().add(instance);

		MENUDATA.setFile("SINGRACE.IDB",binFile.toBytes());

		dos2.write(binFile.toBytes());
		dos2.flush();
	}

	private static void fix_MENUDATA_LEGORACE0RCB(JAMNode MENUDATA){
		BinaryFile binFile = BinaryFile.from(MENUDATA.getFile("LEGORACE.RCB"));

		BinaryArray length = (BinaryArray) binFile.getTokens().get(1);
		BinaryIntegerSigned binaryInteger = (BinaryIntegerSigned) length.getTokens().get(0);
		binaryInteger.setIntegerSigned(binaryInteger.getIntegerSigned()+1);
		BinaryObject object = (BinaryObject) binFile.getTokens().get(2);
		object.getTokens().add(new BinaryToken((byte) 0x27));
		object.getTokens().add(new BinaryString("kaas"));
		BinaryObject track = new BinaryObject(new BinaryObjectStart(),new BinaryObjectEnd());
		track.getTokens().add(new BinaryToken((byte) 0x2B));
		track.getTokens().add(new BinaryIntegerSigned(16));
		track.getTokens().add(new BinaryToken((byte) 0x29));
		track.getTokens().add(new BinaryString("racec0r4"));
		track.getTokens().add(new BinaryToken((byte) 0x2A));
		track.getTokens().add(new BinaryString("c4"));
		track.getTokens().add(new BinaryToken((byte) 0x28));
		track.getTokens().add(new BinaryIntegerSigned(4));
		track.getTokens().add(new BinaryToken((byte) 0x2D));
		track.getTokens().add(new BinaryString("pirate1"));
		track.getTokens().add(new BinaryToken((byte) 0x2E));
		track.getTokens().add(new BinaryString("GB"));

		object.getTokens().add(track);
		MENUDATA.setFile("LEGORACE.RCB",binFile.toBytes());
	}

	private static void fix_MENUDATA_LEGORACE0CRB(JAMNode MENUDATA) throws IOException {
		BetterOutputStream dos2 = new BetterOutputStream(new FileOutputStream("C:\\Users\\Ben\\Downloads\\JAMExtractor-1.0.2-Win64\\LEGORACE.CRB"));

		BinaryFile binFile = BinaryFile.from(MENUDATA.getFile("LEGORACE.CRB"));
//		System.err.println(binFile);

		BinaryArray length = (BinaryArray) binFile.getTokens().get(1);
		BinaryIntegerSigned binaryInteger = (BinaryIntegerSigned) length.getTokens().get(0);
		binaryInteger.setIntegerSigned(binaryInteger.getIntegerSigned()+1);
		BinaryObject object = (BinaryObject) binFile.getTokens().get(2);

		object.getTokens().add(new BinaryToken((byte) 0x27));
		object.getTokens().add(new BinaryString("c7"));
		BinaryObject track = new BinaryObject(new BinaryObjectStart(),new BinaryObjectEnd());
		track.getTokens().add(new BinaryToken((byte) 0x29));
		track.getTokens().add(new BinaryIntegerSigned(7));
		track.getTokens().add(new BinaryToken((byte) 0x28));

		BinaryArray l = new BinaryArray();
		l.getTokens().add(new BinaryIntegerSigned(6));
		track.getTokens().add(l);
		//
		BinaryObject obj = new BinaryObject(new BinaryObjectStart(),new BinaryObjectEnd());
		BinaryList arr = new BinaryList(BinaryToken.TOKEN_STRING);
		arr.getTokens().add(new BinaryString("KK"));
		arr.getTokens().add(new BinaryString("CR"));
		arr.getTokens().add(new BinaryString("GB"));
		arr.getTokens().add(new BinaryString("RH"));
		arr.getTokens().add(new BinaryString("AD"));
		arr.getTokens().add(new BinaryString("PH"));
		obj.getTokens().add(arr);
		track.getTokens().add(obj);
		//
		track.getTokens().add(new BinaryToken((byte) 0x2A));
		track.getTokens().add(new BinaryIntegerSigned(7));
		track.getTokens().add(new BinaryToken((byte) 0x2B));
		track.getTokens().add(new BinaryString("c6"));

//		BinaryToken{token=39},
//		BinaryString{string='c0'},
//		BinaryObject{tokens=[
//			BinaryToken{token=41},
//			BinaryIntegerSigned{integerSigned=0},
//			BinaryToken{token=40},
//			BinaryLength{start=BinaryLengthStart{}, length=BinaryIntegerSigned{integerSigned=6}, end=BinaryLengthEnd{}},
//			BinaryObject{tokens=[
//				BinaryArray{tokens=[
//					BinaryString{string='KK'},
//					BinaryString{string='CR'},
//					BinaryString{string='GB'},
//					BinaryString{string='RH'},
//					BinaryString{string='AD'},
//					BinaryString{string='PH'}
//				]}
//			]},
//			BinaryToken{token=42},
//			BinaryIntegerSigned{integerSigned=0},
//			BinaryToken{token=43},
//			BinaryString{string='c1'}
//		]},
		object.getTokens().add(track);

		dos2.write(binFile.toBytes());
		dos2.flush();

		MENUDATA.setFile("LEGORACE.CRB",binFile.toBytes());
	}

	public static void printDepth(JAMNode node, int depth){
		String prefix = "";
		for(int i=0;i<depth;i++){
			prefix += "---";
		}
		for(Map.Entry<String,byte[]> name : node.getFiles().entrySet()){
			if(name.getKey().contains(".PCM") || name.getKey().contains(".BMP") || name.getKey().contains(".SRF") || name.getKey().contains(".SBK") || name.getKey().contains(".TGA") || !name.getKey().contains(".") || name.getKey().contains(".LRS")){
				continue;
			}
			if(!name.getKey().contains(".MSB")){
				continue;
			}
			System.out.print(prefix+""+name.getKey()+" :: ");//+name.getValue().length+
			String debug = BinaryFile.from(name.getValue()).toString();////new String(name.getValue()).contains("c4");
			System.out.println(debug);
		}
		for(Map.Entry<String,JAMNode> name : node.getFolders().entrySet()){
			System.out.println(prefix+""+name.getKey()+" <> "+name.getValue().calculateSize());
			printDepth(name.getValue(),depth+1);
		}
	}

}
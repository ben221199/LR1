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
import com.lego.racers.file.cpb.CPBFile;
import com.lego.racers.file.emb.EMBFile;
import com.lego.racers.file.fdb.FDBFile;
import com.lego.racers.file.jam.JAMFile;
import com.lego.racers.file.jam.JAMNode;
import com.lego.racers.file.pwb.PWBFile;
import com.lego.racers.file.sbk.SBKFile;
import com.lego.racers.file.spb.SPBFile;
import com.lego.racers.file.srf.SRFFile;
import com.lego.racers.file.tdb.TDBFile;
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
		JAMNode GAMEDATA = file.getRootNode().getFolder("GAMEDATA");
//		fix_MENUDATA_SINGRACE0IDB(MENUDATA);
//		fix_MENUDATA_LEGORACE0RCB(MENUDATA);
//		fix_MENUDATA_LEGORACE0CRB(MENUDATA);
		fix_RR(MENUDATA,GAMEDATA);
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

	private static void fix_RR(JAMNode MENUDATA,JAMNode GAMEDATA) throws IOException{
		BinaryFile binFile = BinaryFile.from(MENUDATA.getFile("LEGORACE.RCB"));

		BinaryArray lengthArray = (BinaryArray) binFile.getTokens().get(1);
		BinaryIntegerSigned length = (BinaryIntegerSigned) lengthArray.getTokens().get(0);
		length.setIntegerSigned(length.getIntegerSigned()+1);

		BinaryObject tracks = (BinaryObject) binFile.getTokens().get(2);
		int i = 0;
		while(true){
			BinaryToken token = tracks.getTokens().get(i);
			if(token instanceof BinaryString){
				if(((BinaryString) token).getString().equalsIgnoreCase("rr2")){
					break;
				}
			}
			i++;
		}
		BinaryObject track = (BinaryObject) tracks.getTokens().get(i+1);

		BinaryIntegerSigned nameIndex = (BinaryIntegerSigned) track.getTokens().get(1);
		nameIndex.setIntegerSigned(nameIndex.getIntegerSigned()-5);

		BinaryString folderName = (BinaryString) track.getTokens().get(3);
		folderName.setString("racec0r1");

		track.getTokens().add(new BinaryToken((byte) 0x2A));// Circuit ID
		track.getTokens().add(new BinaryString("c6"));
		track.getTokens().add(new BinaryToken((byte) 0x28));// Position in circuit
		track.getTokens().add(new BinaryIntegerSigned(1));
		track.getTokens().add(new BinaryToken((byte) 0x2D));// Theme string
		track.getTokens().add(new BinaryString("space2"));
		track.getTokens().add(new BinaryToken((byte) 0x2E));// Mascot character
		track.getTokens().add(new BinaryString("RR"));

		//////////////

		BinaryObject track2 = new BinaryObject();
		tracks.getTokens().add(new BinaryToken((byte) 0x27));
		tracks.getTokens().add(new BinaryString("rr3"));
		tracks.getTokens().add(track2);

		track2.getTokens().add(new BinaryToken((byte) 0x2B));// Name Index (/MENUDATA/<lang>/CIRCUIT.SRF)
		track2.getTokens().add(new BinaryIntegerSigned(12));
		track2.getTokens().add(new BinaryToken((byte) 0x29));// Folder (/GAMEDATA/<this>/)
		track2.getTokens().add(new BinaryString("racec6r8"));

		track2.getTokens().add(new BinaryToken((byte) 0x2A));// Circuit ID
		track2.getTokens().add(new BinaryString("c6"));
		track2.getTokens().add(new BinaryToken((byte) 0x28));// Position in circuit
		track2.getTokens().add(new BinaryIntegerSigned(2));
		track2.getTokens().add(new BinaryToken((byte) 0x2D));// Theme string
		track2.getTokens().add(new BinaryString("advntr1"));
		track2.getTokens().add(new BinaryToken((byte) 0x2E));// Mascot character
		track2.getTokens().add(new BinaryString("RH"));

		BinaryFile RAB = new BinaryFile();

		BinaryObject RAB_INFO = new BinaryObject();

		RAB.getTokens().add(new BinaryToken((byte) 0x35));
		RAB.getTokens().add(new BinaryString("Yocto Plantage"));
		RAB.getTokens().add(RAB_INFO);

		RAB_INFO.getTokens().add(new BinaryToken((byte) 0x2D));
		RAB_INFO.getTokens().add(new BinaryString("legofnts.fdf"));

		JAMNode RACEC6R8 = new JAMNode();
//		RACEC6R8.setFile("RACEC6R8.RAB",RAB.toBytes());
		RACEC6R8.setFile("RACEC6R8.RAB",GAMEDATA.getFolder("RACEC0R0").getFile("RACEC0R0.RAB"));
		RACEC6R8.setFile("LOADSCRN.LSB",GAMEDATA.getFolder("RACEC0R0").getFile("LOADSCRN.LSB"));
		RACEC6R8.setFile("RKR.BMP",GAMEDATA.getFolder("RACEC0R0").getFile("RKR.BMP"));
		RACEC6R8.setFile("LOADSCRN.IDB",GAMEDATA.getFolder("RACEC0R0").getFile("LOADSCRN.IDB"));
		RACEC6R8.setFile("TICK.BMP",GAMEDATA.getFolder("RACEC0R0").getFile("TICK.BMP"));

		RACEC6R8.setFile("RRTRK.WDB",GAMEDATA.getFolder("RACEC0R0").getFile("RRTRK.WDB"));

		GAMEDATA.setFolder("RACEC6R8",RACEC6R8);

		MENUDATA.setFile("LEGORACE.RCB",binFile.toBytes());
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
		object.getTokens().add(new BinaryString("kaas2"));

		BinaryObject track = new BinaryObject(new BinaryObjectStart(),new BinaryObjectEnd());
		// Name Index (/MENUDATA/<lang>/CIRCUIT.SRF)
		track.getTokens().add(new BinaryToken((byte) 0x2B));
		track.getTokens().add(new BinaryIntegerSigned(3));
		// Folder (/GAMEDATA/<this>/)
		track.getTokens().add(new BinaryToken((byte) 0x29));
		track.getTokens().add(new BinaryString("racec0r4"));
		// Circuit ID
		track.getTokens().add(new BinaryToken((byte) 0x2A));
		track.getTokens().add(new BinaryString("c7"));
		// Position in circuit
		track.getTokens().add(new BinaryToken((byte) 0x28));
		track.getTokens().add(new BinaryIntegerSigned(0));
		// Theme string
		track.getTokens().add(new BinaryToken((byte) 0x2D));
		track.getTokens().add(new BinaryString("myTheme"));
		// Mascot character
		track.getTokens().add(new BinaryToken((byte) 0x2E));
		track.getTokens().add(new BinaryString("YOC"));

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
			if(name.getKey().endsWith(".BMP")){
//				System.out.println(prefix+name.getKey()+" => [NON-LEGO-BINARY] BITMAP (IMAGE)");
				continue;
			}
			if(name.getKey().endsWith(".CPB")){
//				System.out.println(prefix+name.getKey()+" => "+ CPBFile.from(name.getValue()));
				continue;
			}
			if(name.getKey().endsWith(".EMB")){
				System.out.println(prefix+name.getKey()+" => "+ EMBFile.from(name.getValue()));
				continue;
			}
			if(name.getKey().endsWith(".FDB")){
//				System.out.println(prefix+name.getKey()+" => "+ FDBFile.from(name.getValue()));
				continue;
			}
			if(name.getKey().endsWith(".LRS")){
//				System.out.println(prefix+name.getKey()+" => [NON-LEGO-BINARY] LEGO RACERS SAVE (???)");
				continue;
			}
			if(name.getKey().endsWith(".PCM")){
//				System.out.println(prefix+name.getKey()+" => [NON-LEGO-BINARY] PULSE-CODE MODULATION (AUDIO)");
				continue;
			}
			if(name.getKey().endsWith(".PWB")){
//				System.out.println(prefix+name.getKey()+" => "+PWBFile.from(name.getValue()));
				continue;
			}
			if(name.getKey().endsWith(".RRB")){
//				System.out.println(prefix+name.getKey()+" => "+BinaryFile.from(name.getValue()));//PWBFile.from(name.getValue())
				continue;
			}
			if(name.getKey().endsWith(".SBK") || "LEGOMSC".equals(name.getKey())){
//				System.out.println(prefix+name.getKey()+" => [NON] => "+ SBKFile.from(name.getValue()));
				continue;
			}
			if(name.getKey().endsWith(".SPB")){
//				System.out.println(prefix+name.getKey()+" => "+ SPBFile.from(name.getValue()));
				continue;
			}
			if(name.getKey().endsWith(".SRF")){
//				System.out.println(prefix+name.getKey()+" => [NON] => "+ SRFFile.from(name.getValue()));
				continue;
			}
			if(name.getKey().endsWith(".TDB")){
//				System.out.println(prefix+name.getKey()+" => "+ TDBFile.from(name.getValue()));
				continue;
			}
			if(name.getKey().endsWith(".TGA")){
//				System.out.println(prefix+name.getKey()+" => [NON-LEGO-BINARY] TRUEVISION GRAPHICS ADAPTER (IMAGE)");
				continue;
			}
			String data = "";//BinaryFile.from(name.getValue()).toString();
			System.out.println(prefix+name.getKey()+" <<>> "+data);
		}
		for(Map.Entry<String,JAMNode> name : node.getFolders().entrySet()){
			System.out.println(prefix+""+name.getKey()+" <> "+name.getValue().calculateSize());
			printDepth(name.getValue(),depth+1);
		}
	}

}
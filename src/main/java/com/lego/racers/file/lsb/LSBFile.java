package com.lego.racers.file.lsb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.jam.JAMNodeFile;
import com.lego.racers.file.lsb.object.Icon;
import com.lego.racers.file.lsb.object.LoadingScreen;

public class LSBFile extends JAMNodeFile{

	public static final byte PROPERTY_LOADING_SCREEN = 0x27;
	public static final byte PROPERTY_MAP_IMAGE = 0x28;
	public static final byte PROPERTY_ICONS = 0x29;
	public static final byte PROPERTY_TITLE = 0x2A;

	private LoadingScreen loadingScreen;

	public LoadingScreen getLoadingScreen(){
		return this.loadingScreen;
	}

	public void setLoadingScreen(LoadingScreen loadingScreen){
		this.loadingScreen = loadingScreen;
	}

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		if(this.loadingScreen!=null){
			bin.getTokens().add(new BinaryToken(LSBFile.PROPERTY_LOADING_SCREEN));
			BinaryObject obj = new BinaryObject();

			obj.getTokens().add(new BinaryToken(LSBFile.PROPERTY_MAP_IMAGE));
			obj.getTokens().add(new BinaryString(this.loadingScreen.getMapImage()));

			obj.getTokens().add(new BinaryToken(LSBFile.PROPERTY_ICONS));
			BinaryArray arr = new BinaryArray();
			arr.getTokens().add(new BinaryIntegerSigned(this.loadingScreen.getIcons().length));
			obj.getTokens().add(arr);
			BinaryObject obj2 = new BinaryObject();
			BinaryList list = new BinaryList(BinaryToken.TOKEN_FLOAT);
			for(Icon icon : this.loadingScreen.getIcons()){
				list.getTokens().add(new BinaryFloat(icon.getXFraction()));
				list.getTokens().add(new BinaryFloat(icon.getYFraction()));
			}
			obj2.getTokens().add(list);
			obj.getTokens().add(obj2);

			obj.getTokens().add(new BinaryToken(LSBFile.PROPERTY_TITLE));
			obj.getTokens().add(new BinaryIntegerSigned(this.loadingScreen.getTitleIndex()));

			bin.getTokens().add(obj);
		}
		return bin.toBytes();
	}

	public static LSBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		LSBFile lsb = new LSBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==LSBFile.PROPERTY_LOADING_SCREEN){
				LSBFile.initLoadingScreen(lsb,(BinaryObject) bin.getTokens().get(i+1));
			}
		}
		return lsb;
	}

	private static void initLoadingScreen(LSBFile file,BinaryObject obj){
		LoadingScreen loadingScreen = new LoadingScreen();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==LSBFile.PROPERTY_MAP_IMAGE){
				loadingScreen.setMapImage(((BinaryString) obj.getTokens().get(i+1)).getString());
			}
			if(token.getToken()==LSBFile.PROPERTY_ICONS){
				LSBFile.initIcons(loadingScreen,((BinaryArray) obj.getTokens().get(i+1)),((BinaryObject) obj.getTokens().get(i+2)));
			}
			if(token.getToken()==LSBFile.PROPERTY_TITLE){
				loadingScreen.setTitleIndex(((BinaryIntegerSigned) obj.getTokens().get(i+1)).getIntegerSigned());
			}
		}
		file.loadingScreen = loadingScreen;
	}

	private static void initIcons(LoadingScreen loadingScreen,BinaryArray arr,BinaryObject obj){
		int size = ((BinaryIntegerSigned) arr.getTokens().get(0)).getIntegerSigned();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		Icon[] icons = new Icon[size];
		int j = 0;
		for(int i=0;i<icons.length;i++){
			Icon icon = new Icon();
			float x = ((BinaryFloat) list.getTokens().get(j)).getFloat();
			icon.setXFraction(x);
			float y = ((BinaryFloat) list.getTokens().get(j+1)).getFloat();
			icon.setYFraction(y);
			j += 2;
			icons[i] = icon;
		}
		loadingScreen.setIcons(icons);
	}

	@Override
	public String toString() {
		return "LSBFile{" +
				"loadingScreen=" + loadingScreen +
				'}';
	}

}
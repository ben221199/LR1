package com.lego.racers.file.idb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.BinaryStructInstance;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.idb.object.Color;
import com.lego.racers.file.idb.object.Image;
import com.lego.racers.file.idb.object.Images;

import java.util.Map;

public class IDBFile{

	public static final byte BLOCK_IMAGE = 0x27;

	public static final byte PROPERTY_IMAGES = 0x27;
	public static final byte PROPERTY_41 = 0x29;
	public static final byte PROPERTY_TRANSPARENT_COLOR = 0x2B;

	private Images images;

	public Images getImages(){
		return this.images;
	}

	public void setImages(Images images){
		this.images = images;
	}

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		boolean struct23 = false;
		if(this.images!=null){
			bin.getTokens().add(new BinaryToken(IDBFile.PROPERTY_IMAGES));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.images.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			int i = 0;
			for(Map.Entry<String,Image> entry : this.images.entrySet()){
				obj.getTokens().add(new BinaryToken(IDBFile.BLOCK_IMAGE));
				obj.getTokens().add(new BinaryString(entry.getKey()));
				BinaryObject imageObj = new BinaryObject();
				if(entry.getValue().getTransparentColor()!=null && i==0 && !struct23){
					imageObj.getTokens().add(new BinaryStruct((byte) 0x17,(byte)4,new byte[]{IDBFile.PROPERTY_TRANSPARENT_COLOR,4,4,4}));
					struct23 = true;
				}
				if(entry.getValue().getTransparentColor()!=null){
					BinaryStructInstance structInstance = new BinaryStructInstance((byte) 0x17);
					structInstance.getTokens().add(new BinaryToken(IDBFile.PROPERTY_TRANSPARENT_COLOR));
					structInstance.getTokens().add(new BinaryIntegerSigned(entry.getValue().getTransparentColor().getRed()));
					structInstance.getTokens().add(new BinaryIntegerSigned(entry.getValue().getTransparentColor().getGreen()));
					structInstance.getTokens().add(new BinaryIntegerSigned(entry.getValue().getTransparentColor().getBlue()));
					imageObj.getTokens().add(structInstance);
				}
				if(entry.getValue().getP41()){
					imageObj.getTokens().add(new BinaryToken(IDBFile.PROPERTY_41));
				}
				obj.getTokens().add(imageObj);
				i++;
			}
			bin.getTokens().add(obj);
		}
		return bin.toBytes();
	}

	public static IDBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		IDBFile idb = new IDBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==IDBFile.PROPERTY_IMAGES){
				IDBFile.initImages(idb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return idb;
	}

	private static void initImages(IDBFile file,BinaryObject obj){
		Images images = new Images();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==IDBFile.BLOCK_IMAGE){
				IDBFile.initImage(images,(BinaryString) obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file.images = images;
	}

	private static void initImage(Images images,BinaryString str,BinaryObject obj){
		Image image = new Image();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token instanceof BinaryStructInstance){
				BinaryStructInstance structInstance = (BinaryStructInstance) token;
				if(structInstance.getTokens().get(0).getToken()==IDBFile.PROPERTY_TRANSPARENT_COLOR){
					Color c = new Color();
					c.setRed(((BinaryIntegerSigned) structInstance.getTokens().get(1)).getIntegerSigned());
					c.setGreen(((BinaryIntegerSigned) structInstance.getTokens().get(2)).getIntegerSigned());
					c.setBlue(((BinaryIntegerSigned) structInstance.getTokens().get(3)).getIntegerSigned());
					image.setTransparentColor(c);
				}
			}
			if(token.getToken()==IDBFile.PROPERTY_41){
				image.setP41(true);
			}
		}
		images.put(str.getString(),image);
	}

	@Override
	public String toString() {
		return "IDBFile{" +
				"images=" + images +
				'}';
	}

}
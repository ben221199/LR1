package com.lego.racers.file.tdb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.BinaryStructInstance;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.tdb.object.Texture;
import com.lego.racers.file.tdb.object.Textures;
import com.lego.racers.file.tdb.object.TransparentColor;

import java.util.Map;

public class TDBFile{

	public static final byte BLOCK_TEXTURES = 0x27;

	public static final byte PROPERTY_TEXTURE = 0x27;
	public static final byte PROPERTY_40 = 0x28;
	public static final byte PROPERTY_BITMAP = 0x2A;
	public static final byte PROPERTY_TRANSPARENT_COLOR = 0x2C;
	public static final byte PROPERTY_45 = 0x2D;

	private Textures textures;

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		boolean struct23 = false;
		if(this.textures!=null){
			bin.getTokens().add(new BinaryToken(TDBFile.BLOCK_TEXTURES));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.textures.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			int i = 0;
			for(Map.Entry<String,Texture> entry : this.textures.entrySet()){
				obj.getTokens().add(new BinaryToken(TDBFile.PROPERTY_TEXTURE));
				obj.getTokens().add(new BinaryString(entry.getKey()));
				BinaryObject textureObj = new BinaryObject();
				if(entry.getValue().getIsBitmap()){
					textureObj.getTokens().add(new BinaryToken(TDBFile.PROPERTY_BITMAP));
				}
				if(i==0 && !struct23){
					textureObj.getTokens().add(new BinaryStruct((byte) 0x17,(byte)4,new byte[]{0x28,4,4,4}));
					struct23 = true;
				}
				if(entry.getValue().get40()){
					textureObj.getTokens().add(new BinaryToken(TDBFile.PROPERTY_40));
				}
				if(entry.getValue().get45()){
					textureObj.getTokens().add(new BinaryToken(TDBFile.PROPERTY_40));
				}
				if(entry.getValue().getTransparentColor()!=null){
					BinaryStructInstance transparentColor = new BinaryStructInstance((byte) 0x17);
					transparentColor.getTokens().add(new BinaryToken(TDBFile.PROPERTY_TRANSPARENT_COLOR));
					transparentColor.getTokens().add(new BinaryIntegerSigned(entry.getValue().getTransparentColor().getRed()));
					transparentColor.getTokens().add(new BinaryIntegerSigned(entry.getValue().getTransparentColor().getGreen()));
					transparentColor.getTokens().add(new BinaryIntegerSigned(entry.getValue().getTransparentColor().getBlue()));
					textureObj.getTokens().add(transparentColor);
				}
				obj.getTokens().add(textureObj);
				i++;
			}
			bin.getTokens().add(obj);
		}
		return bin.toBytes();
	}

	public static TDBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		TDBFile tdb = new TDBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==TDBFile.BLOCK_TEXTURES){
				TDBFile.initTextures(tdb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return tdb;
	}

	private static void initTextures(TDBFile file,BinaryObject obj){
		Textures textures = new Textures();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==TDBFile.PROPERTY_TEXTURE){
				TDBFile.initTexture(textures,(BinaryString) obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file.textures = textures;
	}

	private static void initTexture(Textures textures,BinaryString str,BinaryObject obj){
		Texture texture = new Texture();

		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==TDBFile.PROPERTY_BITMAP){
				texture.setIsBitmap(true);
			}
			if(token.getToken()==TDBFile.PROPERTY_40){
				texture.set40(true);
			}
			if(token.getToken()==TDBFile.PROPERTY_45){
				texture.set45(true);
			}
			if(token instanceof BinaryStructInstance){
				if(((BinaryStructInstance) token).getId()==0x17){
					initTransparentColor(texture,(BinaryStructInstance) token);
				}
			}
		}

		textures.put(str.getString(),texture);
	}

	private static void initTransparentColor(Texture texture,BinaryStructInstance structInstance){
		if(structInstance.getTokens().size()>0 && structInstance.getTokens().get(0).getToken()==TDBFile.PROPERTY_TRANSPARENT_COLOR){
			TransparentColor transparentColor = new TransparentColor();
			transparentColor.setRed(((BinaryIntegerSigned)structInstance.getTokens().get(1)).getIntegerSigned());
			transparentColor.setGreen(((BinaryIntegerSigned)structInstance.getTokens().get(2)).getIntegerSigned());
			transparentColor.setBlue(((BinaryIntegerSigned)structInstance.getTokens().get(3)).getIntegerSigned());
			texture.setTransparentColor(transparentColor);
		}
	}

	@Override
	public String toString() {
		return "TDBFile{" +
				"textures=" + textures +
				'}';
	}

}
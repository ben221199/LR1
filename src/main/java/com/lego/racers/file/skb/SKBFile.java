package com.lego.racers.file.skb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.BinaryStructInstance;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.skb.object.Color;
import com.lego.racers.file.skb.object.Sky;
import com.lego.racers.file.skb.object.SkyGradient;
import com.lego.racers.file.skb.object.SkyGradients;

import java.util.Map;

public class SKBFile{

	public static final byte PROPERTY_SKY = 0x2C;			//Block array only
	public static final byte PROPERTY_SKYGRADIENT = 0x27;	//Block array too
	public static final byte PROPERTY_40 = 0x28;
	public static final byte PROPERTY_COLOR_START = 0x29;
	public static final byte PROPERTY_COLOR_MIDDLE = 0x2A;
	public static final byte PROPERTY_COLOR_END = 0x2B;
	public static final byte PROPERTY_DEFAULT = 0x2D;
	public static final byte PROPERTY_HORIZON = 0x2E;

	private Sky sky;
	private String defaultGradient;
	private Float horizon;

	public Sky getSky() {
		return this.sky;
	}

	public void setSky(Sky sky) {
		this.sky = sky;
	}

	public String getDefaultGradient() {
		return this.defaultGradient;
	}

	public void setDefaultGradient(String defaultGradient) {
		this.defaultGradient = defaultGradient;
	}

	public Float getHorizon() {
		return this.horizon;
	}

	public void setHorizon(Float horizon) {
		this.horizon = horizon;
	}

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		boolean struct23 = false;
		boolean struct24 = false;
		boolean struct25 = false;
		if(this.sky!=null){
			bin.getTokens().add(new BinaryToken(SKBFile.PROPERTY_SKY));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.sky.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			for(Map.Entry<String, SkyGradients> entry : this.sky.entrySet()){
				obj.getTokens().add(new BinaryToken(SKBFile.PROPERTY_SKYGRADIENT));
				BinaryArray gradientLength = new BinaryArray();
				gradientLength.getTokens().add(new BinaryIntegerSigned(entry.getValue().size()));
				obj.getTokens().add(gradientLength);
				obj.getTokens().add(new BinaryString(entry.getKey()));
				BinaryObject skyGradientsObj = new BinaryObject();
				for(int i=0;i<entry.getValue().size();i++){
					SkyGradient skyGradient = entry.getValue().get(i);
					BinaryObject skyGradientObj = new BinaryObject();
					if(skyGradient.get40()!=null){
						skyGradientObj.getTokens().add(new BinaryToken(SKBFile.PROPERTY_40));
						skyGradientObj.getTokens().add(new BinaryIntegerSigned(skyGradient.get40()));
					}
					if(!struct23){
						BinaryStruct struct = new BinaryStruct((byte)0x17,(byte)4,new byte[]{SKBFile.PROPERTY_COLOR_START,BinaryToken.TOKEN_INTEGER_SIGNED,BinaryToken.TOKEN_INTEGER_SIGNED,BinaryToken.TOKEN_INTEGER_SIGNED});
						skyGradientObj.getTokens().add(struct);
						struct23 = true;
					}
					if(!struct24){
						BinaryStruct struct = new BinaryStruct((byte)0x18,(byte)4,new byte[]{SKBFile.PROPERTY_COLOR_MIDDLE,BinaryToken.TOKEN_INTEGER_SIGNED,BinaryToken.TOKEN_INTEGER_SIGNED,BinaryToken.TOKEN_INTEGER_SIGNED});
						skyGradientObj.getTokens().add(struct);
						struct24 = true;
					}
					if(!struct25){
						BinaryStruct struct = new BinaryStruct((byte)0x19,(byte)4,new byte[]{SKBFile.PROPERTY_COLOR_END,BinaryToken.TOKEN_INTEGER_SIGNED,BinaryToken.TOKEN_INTEGER_SIGNED,BinaryToken.TOKEN_INTEGER_SIGNED});
						skyGradientObj.getTokens().add(struct);
						struct25 = true;
					}
					if(skyGradient.getColorStart()!=null){
						BinaryStructInstance structInstance = new BinaryStructInstance((byte) 0x17);
						structInstance.getTokens().add(new BinaryToken(SKBFile.PROPERTY_COLOR_START));
						structInstance.getTokens().add(new BinaryIntegerSigned(skyGradient.getColorStart().getRed()));
						structInstance.getTokens().add(new BinaryIntegerSigned(skyGradient.getColorStart().getGreen()));
						structInstance.getTokens().add(new BinaryIntegerSigned(skyGradient.getColorStart().getBlue()));
						skyGradientObj.getTokens().add(structInstance);
					}
					if(skyGradient.getColorMiddle()!=null){
						BinaryStructInstance structInstance = new BinaryStructInstance((byte) 0x18);
						structInstance.getTokens().add(new BinaryToken(SKBFile.PROPERTY_COLOR_MIDDLE));
						structInstance.getTokens().add(new BinaryIntegerSigned(skyGradient.getColorMiddle().getRed()));
						structInstance.getTokens().add(new BinaryIntegerSigned(skyGradient.getColorMiddle().getGreen()));
						structInstance.getTokens().add(new BinaryIntegerSigned(skyGradient.getColorMiddle().getBlue()));
						skyGradientObj.getTokens().add(structInstance);
					}
					if(skyGradient.getColorEnd()!=null){
						BinaryStructInstance structInstance = new BinaryStructInstance((byte) 0x19);
						structInstance.getTokens().add(new BinaryToken(SKBFile.PROPERTY_COLOR_END));
						structInstance.getTokens().add(new BinaryIntegerSigned(skyGradient.getColorEnd().getRed()));
						structInstance.getTokens().add(new BinaryIntegerSigned(skyGradient.getColorEnd().getGreen()));
						structInstance.getTokens().add(new BinaryIntegerSigned(skyGradient.getColorEnd().getBlue()));
						skyGradientObj.getTokens().add(structInstance);
					}
					skyGradientsObj.getTokens().add(new BinaryToken(SKBFile.PROPERTY_SKYGRADIENT));
					skyGradientsObj.getTokens().add(skyGradientObj);
				}
				obj.getTokens().add(skyGradientsObj);
			}
			bin.getTokens().add(obj);
		}
		if(this.defaultGradient!=null){
			bin.getTokens().add(new BinaryToken(SKBFile.PROPERTY_DEFAULT));
			bin.getTokens().add(new BinaryString(this.defaultGradient));
		}
		if(this.horizon!=null){
			bin.getTokens().add(new BinaryToken(SKBFile.PROPERTY_HORIZON));
			bin.getTokens().add(new BinaryFloat(this.horizon));
		}
		return bin.toBytes();
	}

	public static SKBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		SKBFile skb = new SKBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==SKBFile.PROPERTY_SKY){
				SKBFile.initSky(skb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==SKBFile.PROPERTY_DEFAULT){
				BinaryString str = (BinaryString) bin.getTokens().get(i+1);
				skb.defaultGradient = str.getString();
			}
			if(token.getToken()==SKBFile.PROPERTY_HORIZON){
				BinaryFloat f = (BinaryFloat) bin.getTokens().get(i+1);
				skb.horizon = f.getFloat();
			}
		}
		return skb;
	}

	private static void initSky(SKBFile file,BinaryObject obj){
		Sky sky = new Sky();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==SKBFile.PROPERTY_SKYGRADIENT){
				SKBFile.initSkyGradients(sky,(BinaryString) obj.getTokens().get(i+2),(BinaryObject) obj.getTokens().get(i+3));
			}
		}
		file.sky = sky;
	}

	private static void initSkyGradients(Sky sky,BinaryString str,BinaryObject obj){
		SkyGradients skyGradients = new SkyGradients();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==SKBFile.PROPERTY_SKYGRADIENT){
				int j = 1;
				while(obj.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				SKBFile.initSkyGradient(skyGradients,(BinaryObject) obj.getTokens().get(i+j));
			}
		}
		sky.put(str.getString(),skyGradients);
	}

	private static void initSkyGradient(SkyGradients skyGradients,BinaryObject obj){
		SkyGradient skyGradient = new SkyGradient();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==SKBFile.PROPERTY_40){
				int j = 1;
				while(obj.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				skyGradient.set40(((BinaryIntegerSigned) obj.getTokens().get(i+j)).getIntegerSigned());
			}
			if(token instanceof BinaryStructInstance){
				BinaryStructInstance structInstance = (BinaryStructInstance) token;
				BinaryToken subToken = structInstance.getTokens().get(0);
				Color color = new Color();
				color.setRed(((BinaryIntegerSigned) structInstance.getTokens().get(1)).getIntegerSigned());
				color.setGreen(((BinaryIntegerSigned) structInstance.getTokens().get(2)).getIntegerSigned());
				color.setBlue(((BinaryIntegerSigned) structInstance.getTokens().get(3)).getIntegerSigned());
				if(subToken.getToken()==SKBFile.PROPERTY_COLOR_START){
					skyGradient.setColorStart(color);
				}
				if(subToken.getToken()==SKBFile.PROPERTY_COLOR_MIDDLE){
					skyGradient.setColorMiddle(color);
				}
				if(subToken.getToken()==SKBFile.PROPERTY_COLOR_END){
					skyGradient.setColorEnd(color);
				}
			}
		}

		skyGradients.add(skyGradient);
	}

	@Override
	public String toString() {
		return "SKBFile{" +
				"sky=" + sky +
				", defaultGradient='" + defaultGradient + '\'' +
				", horizon=" + horizon +
				'}';
	}

}

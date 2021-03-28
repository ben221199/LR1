package com.lego.racers.file.fdb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.fdb.object.Font;
import com.lego.racers.file.fdb.object.Fonts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FDBFile{

	public static final byte BLOCK_FONTS = 0x27;

	public static final byte PROPERTY_FONT = 0x27;
	public static final byte PROPERTY_40 = 0x28;
	public static final byte PROPERTY_TRANSPARENT_COLOR = 0x2A;
	public static final byte PROPERTY_ORDER = 0x2B;
	public static final byte PROPERTY_SPACING = 0x2C;

	private Fonts fonts;

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		if(this.fonts!=null){
			bin.getTokens().add(new BinaryToken(FDBFile.BLOCK_FONTS));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.fonts.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			for(Map.Entry<String, Font> entry : this.fonts.entrySet()){
				obj.getTokens().add(new BinaryToken(FDBFile.PROPERTY_FONT));
				obj.getTokens().add(new BinaryString(entry.getKey()));
				BinaryObject fontObj = new BinaryObject();
				if(entry.getValue().get40()){
					fontObj.getTokens().add(new BinaryToken(FDBFile.PROPERTY_40));
				}
				//TODO Improve TransparentColor???
				fontObj.getTokens().add(new BinaryToken(FDBFile.PROPERTY_TRANSPARENT_COLOR));
				fontObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().getTransparentColorRed()));
				fontObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().getTransparentColorGreen()));
				fontObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().getTransparentColorBlue()));

				fontObj.getTokens().add(new BinaryToken(FDBFile.PROPERTY_SPACING));
				fontObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().getSpacing()));

				if(entry.getValue().getOrder()!=null){
					fontObj.getTokens().add(new BinaryToken(FDBFile.PROPERTY_ORDER));
					BinaryArray arr = new BinaryArray();
					for(Object orderItem : entry.getValue().getOrder()){
						if(orderItem!=null && orderItem.getClass().equals(int.class)){
							arr.getTokens().add(new BinaryIntegerSigned((Integer) orderItem));
						}
						if(orderItem!=null && orderItem.getClass().equals(char.class)){
							arr.getTokens().add(new BinaryString(Character.toString((Character) orderItem)));
						}
						if(orderItem!=null && orderItem.getClass().equals(int[].class)){
							BinaryList list = new BinaryList(BinaryToken.TOKEN_INTEGER_SIGNED);
							for(int val : (int[]) orderItem){
								list.getTokens().add(new BinaryIntegerSigned(val));
							}
							arr.getTokens().add(list);
						}
						if(orderItem!=null && orderItem.getClass().equals(char[].class)){
							BinaryList list = new BinaryList(BinaryToken.TOKEN_STRING);
							for(char val : (char[]) orderItem){
								list.getTokens().add(new BinaryString(Character.toString(val)));
							}
							arr.getTokens().add(list);
						}
					}
					fontObj.getTokens().add(arr);
				}
				obj.getTokens().add(fontObj);
			}
			bin.getTokens().add(obj);
		}
		return bin.toBytes();
	}

	public static FDBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		FDBFile fdb = new FDBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==FDBFile.BLOCK_FONTS){
				FDBFile.initFonts(fdb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return fdb;
	}

	private static void initFonts(FDBFile file,BinaryObject obj){
		Fonts fonts = new Fonts();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==FDBFile.PROPERTY_FONT){
				FDBFile.initFont(fonts,(BinaryString) obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file.fonts = fonts;
	}

	private static void initFont(Fonts fonts,BinaryString str,BinaryObject obj){
		Font font = new Font();

		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==FDBFile.PROPERTY_40){
				font.set40(true);
			}
			if(token.getToken()==FDBFile.PROPERTY_TRANSPARENT_COLOR){
				BinaryIntegerSigned red = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				BinaryIntegerSigned green = (BinaryIntegerSigned) obj.getTokens().get(i+2);
				BinaryIntegerSigned blue = (BinaryIntegerSigned) obj.getTokens().get(i+3);
				font.setTransparentColorRed(red.getIntegerSigned());
				font.setTransparentColorGreen(green.getIntegerSigned());
				font.setTransparentColorBlue(blue.getIntegerSigned());
			}
			if(token.getToken()==FDBFile.PROPERTY_SPACING){
				BinaryIntegerSigned spacing = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				font.setSpacing(spacing.getIntegerSigned());
			}
			if(token.getToken()==FDBFile.PROPERTY_ORDER){
				BinaryArray order = (BinaryArray) obj.getTokens().get(i+1);
				FDBFile.initOrder(font,order);
			}
		}

		fonts.put(str.getString(),font);
	}

	private static void initOrder(Font font,BinaryArray arr){
		List<Object> order = new ArrayList<>();
		for(BinaryToken orderItem : arr.getTokens()){
			if(orderItem instanceof BinaryList){
				BinaryList list = (BinaryList) orderItem;
				if(list.getType()==BinaryToken.TOKEN_STRING){
					char[] chars = new char[list.getTokens().size()];
					for(int i=0;i<chars.length;i++){
						BinaryString str = (BinaryString) list.getTokens().get(i);
						chars[i] = str.getString().charAt(0);
					}
					order.add(chars);
				}
				if(list.getType()==BinaryToken.TOKEN_INTEGER_SIGNED){
					int[] integers = new int[list.getTokens().size()];
					for(int i=0;i<integers.length;i++){
						BinaryIntegerSigned integer = (BinaryIntegerSigned) list.getTokens().get(i);
						integers[i] = integer.getIntegerSigned();
					}
					order.add(integers);
				}
			}
			if(orderItem instanceof BinaryString){
				order.add(((BinaryString) orderItem).getString().charAt(0));
			}
			if(orderItem instanceof BinaryIntegerSigned){
				order.add(((BinaryIntegerSigned) orderItem).getIntegerSigned());
			}
		}
		font.setOrder(order);
	}

	@Override
	public String toString() {
		return "FDBFile{" +
				"fonts=" + fonts +
				'}';
	}

}
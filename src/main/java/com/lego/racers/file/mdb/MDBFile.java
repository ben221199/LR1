package com.lego.racers.file.mdb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.BinaryStructInstance;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.jam.JAMNodeFile;
import com.lego.racers.file.mdb.object.AmbientColor;
import com.lego.racers.file.mdb.object.DiffuseColor;
import com.lego.racers.file.mdb.object.Material;
import com.lego.racers.file.mdb.object.Materials;

import java.util.Map;

public class MDBFile extends JAMNodeFile{

	public static final byte BLOCK_MATERIALS = 0x27;

	public static final byte PROPERTY_MATERIAL = 0x27;
	public static final byte PROPERTY_COLOR_AMBIENT = 0x28;
	public static final byte PROPERTY_COLOR_DIFFUSE = 0x29;
	public static final byte PROPERTY_43 = 0x2B;
	public static final byte PROPERTY_TEXTURE = 0x2C;
	public static final byte PROPERTY_45 = 0x2D;
	public static final byte PROPERTY_46 = 0x2E;
	public static final byte PROPERTY_56 = 0x38;
	public static final byte PROPERTY_63 = 0x3F;
	public static final byte PROPERTY_65 = 0x41;
	public static final byte PROPERTY_69 = 0x45;
	public static final byte PROPERTY_ALPHA = 0x46;
	public static final byte PROPERTY_74 = 0x4A;

	private Materials materials;

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		boolean struct23 = false;
		boolean struct24 = false;
		if(this.materials!=null){
			bin.getTokens().add(new BinaryToken(MDBFile.BLOCK_MATERIALS));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.materials.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			int i = 0;
			for(Map.Entry<String, Material> entry : this.materials.entrySet()){
				obj.getTokens().add(new BinaryToken(MDBFile.PROPERTY_MATERIAL));
				obj.getTokens().add(new BinaryString(entry.getKey()));
				if(i==0 && !struct24){
					obj.getTokens().add(new BinaryStruct((byte) 0x18,(byte)5,new byte[]{0x29,4,4,4,4}));
					struct24 = true;
				}
				BinaryObject materialObj = new BinaryObject();
				if(i==0 && !struct23){
					materialObj.getTokens().add(new BinaryStruct((byte) 0x17,(byte)5,new byte[]{0x28,4,4,4,4}));
					struct23 = true;
				}
				if(entry.getValue().getAmbientColor()!=null){
					BinaryStructInstance structInstance = new BinaryStructInstance((byte) 0x17);
					structInstance.getTokens().add(new BinaryToken(MDBFile.PROPERTY_COLOR_AMBIENT));
					structInstance.getTokens().add(new BinaryIntegerSigned(entry.getValue().getAmbientColor().getRed()));
					structInstance.getTokens().add(new BinaryIntegerSigned(entry.getValue().getAmbientColor().getGreen()));
					structInstance.getTokens().add(new BinaryIntegerSigned(entry.getValue().getAmbientColor().getBlue()));
					structInstance.getTokens().add(new BinaryIntegerSigned(entry.getValue().getAmbientColor().getAlpha()));
					materialObj.getTokens().add(structInstance);
				}
				if(entry.getValue().getDiffuseColor()!=null){
					BinaryStructInstance structInstance = new BinaryStructInstance((byte) 0x18);
					structInstance.getTokens().add(new BinaryToken(MDBFile.PROPERTY_COLOR_DIFFUSE));
					structInstance.getTokens().add(new BinaryIntegerSigned(entry.getValue().getDiffuseColor().getRed()));
					structInstance.getTokens().add(new BinaryIntegerSigned(entry.getValue().getDiffuseColor().getGreen()));
					structInstance.getTokens().add(new BinaryIntegerSigned(entry.getValue().getDiffuseColor().getBlue()));
					structInstance.getTokens().add(new BinaryIntegerSigned(entry.getValue().getDiffuseColor().getAlpha()));
					materialObj.getTokens().add(structInstance);
				}
				if(entry.getValue().get43()!=null && entry.getValue().get43()){
					materialObj.getTokens().add(new BinaryToken(MDBFile.PROPERTY_43));
				}
				if(entry.getValue().getTexture()!=null){
					materialObj.getTokens().add(new BinaryToken(MDBFile.PROPERTY_TEXTURE));
					materialObj.getTokens().add(new BinaryString(entry.getValue().getTexture()));
				}
				if(entry.getValue().get45()!=null && entry.getValue().get45()){
					materialObj.getTokens().add(new BinaryToken(MDBFile.PROPERTY_45));
				}
				if(entry.getValue().get46()!=null && entry.getValue().get46()){
					materialObj.getTokens().add(new BinaryToken(MDBFile.PROPERTY_46));
				}
				if(entry.getValue().get56()!=null && entry.getValue().get56()){
					materialObj.getTokens().add(new BinaryToken(MDBFile.PROPERTY_56));
				}
				if(entry.getValue().get63()!=null && entry.getValue().get63()){
					materialObj.getTokens().add(new BinaryToken(MDBFile.PROPERTY_63));
				}
				if(entry.getValue().get65()!=null && entry.getValue().get65()){
					materialObj.getTokens().add(new BinaryToken(MDBFile.PROPERTY_65));
				}
				if(entry.getValue().get69()!=null && entry.getValue().get69()){
					materialObj.getTokens().add(new BinaryToken(MDBFile.PROPERTY_69));
				}
				if(entry.getValue().getAlpha()!=null){
					materialObj.getTokens().add(new BinaryToken(MDBFile.PROPERTY_ALPHA));
					materialObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().getAlpha()));
				}
				if(entry.getValue().get74()!=null && entry.getValue().get74()){
					materialObj.getTokens().add(new BinaryToken(MDBFile.PROPERTY_74));
				}
				obj.getTokens().add(materialObj);
				i++;
			}
			bin.getTokens().add(obj);
		}
		return bin.toBytes();
	}

	public static MDBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		MDBFile mdb = new MDBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()== MDBFile.BLOCK_MATERIALS){
				MDBFile.initMaterials(mdb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return mdb;
	}

	private static void initMaterials(MDBFile file, BinaryObject obj){
		Materials materials = new Materials();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()== MDBFile.PROPERTY_MATERIAL){
				int j = 2;
				while(obj.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				MDBFile.initMaterial(materials,(BinaryString) obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+j));
			}
		}
		file.materials = materials;
	}

	private static void initMaterial(Materials materials,BinaryString str,BinaryObject obj){
		Material material = new Material();

		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token instanceof BinaryStructInstance){
				for(int j=0;j<((BinaryStructInstance) token).getTokens().size();j++){
					BinaryToken subToken = ((BinaryStructInstance) token).getTokens().get(j);
					if(subToken.getToken()==MDBFile.PROPERTY_COLOR_AMBIENT){
						MDBFile.initAmbientColor(material,(BinaryStructInstance) token);
					}
					if(subToken.getToken()==MDBFile.PROPERTY_COLOR_DIFFUSE){
						MDBFile.initDiffuseColor(material,(BinaryStructInstance) token);
					}
				}
			}
			if(token.getToken()==MDBFile.PROPERTY_43){
				material.set43(true);
			}
			if(token.getToken()==MDBFile.PROPERTY_TEXTURE){
				BinaryString string = (BinaryString) obj.getTokens().get(i+1);
				material.setTexture(string.getString());
			}
			if(token.getToken()==MDBFile.PROPERTY_45){
				material.set45(true);
			}
			if(token.getToken()==MDBFile.PROPERTY_46){
				material.set46(true);
			}
			if(token.getToken()==MDBFile.PROPERTY_56){
				material.set56(true);
			}
			if(token.getToken()==MDBFile.PROPERTY_63){
				material.set63(true);
			}
			if(token.getToken()==MDBFile.PROPERTY_65){
				material.set65(true);
			}
			if(token.getToken()==MDBFile.PROPERTY_69){
				material.set69(true);
			}
			if(token.getToken()==MDBFile.PROPERTY_ALPHA){
				int j = 1;
				while(obj.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				BinaryIntegerSigned integer = (BinaryIntegerSigned) obj.getTokens().get(i+j);
				material.setAlpha(integer.getIntegerSigned());
			}
			if(token.getToken()==MDBFile.PROPERTY_74){
				material.set74(true);
			}
		}

		materials.put(str.getString(),material);
	}

	private static void initDiffuseColor(Material material,BinaryStructInstance structInstance){
		DiffuseColor diffuseColor = new DiffuseColor();
		diffuseColor.setRed(((BinaryIntegerSigned) structInstance.getTokens().get(1)).getIntegerSigned());
		diffuseColor.setGreen(((BinaryIntegerSigned) structInstance.getTokens().get(2)).getIntegerSigned());
		diffuseColor.setBlue(((BinaryIntegerSigned) structInstance.getTokens().get(3)).getIntegerSigned());
		diffuseColor.setAlpha(((BinaryIntegerSigned) structInstance.getTokens().get(4)).getIntegerSigned());
		material.setDiffuseColor(diffuseColor);
	}

	private static void initAmbientColor(Material material,BinaryStructInstance structInstance){
		AmbientColor ambientColor = new AmbientColor();
		ambientColor.setRed(((BinaryIntegerSigned) structInstance.getTokens().get(1)).getIntegerSigned());
		ambientColor.setGreen(((BinaryIntegerSigned) structInstance.getTokens().get(2)).getIntegerSigned());
		ambientColor.setBlue(((BinaryIntegerSigned) structInstance.getTokens().get(3)).getIntegerSigned());
		ambientColor.setAlpha(((BinaryIntegerSigned) structInstance.getTokens().get(4)).getIntegerSigned());
		material.setAmbientColor(ambientColor);
	}

	@Override
	public String toString() {
		return "MDBFile{" +
				"materials=" + materials +
				'}';
	}

}
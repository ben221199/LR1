package com.lego.racers.file.sdb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryProperty;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.binary.object.Position;
import com.lego.racers.file.sdb.object.Rotation;
import com.lego.racers.file.sdb.object.Skeleton;
import com.lego.racers.file.sdb.object.Skeletons;

import java.util.List;
import java.util.Map;

public class SDBFile{

	public static final byte PROPERTY_SKELETON = 0x27;
	public static final byte PROPERTY_OFFSET = 0x28;
	public static final byte PROPERTY_ROTATION = 0x29;
	public static final byte PROPERTY_PARENT = 0x2A;

	private Skeletons skeletons;

	public Skeletons getSkeletons(){
		return this.skeletons;
	}

	public void setSkeletons(Skeletons skeletons){
		this.skeletons = skeletons;
	}

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		if(this.skeletons!=null){
			BinaryProperty triggersProp = new BinaryProperty();
			triggersProp.setKey(new BinaryToken(SDBFile.PROPERTY_SKELETON));
			BinaryArray arr = new BinaryArray();
			arr.getTokens().add(new BinaryIntegerSigned(this.skeletons.size()));
			BinaryObject obj = new BinaryObject();
			this.toBytesSkeletons(this.skeletons,obj);
			triggersProp.setValues(List.of(arr,obj));
			bin.addProperty(triggersProp);
		}
		return bin.toBytes();
	}

	private void toBytesSkeletons(Skeletons skeletons,BinaryObject obj){
		for(Map.Entry<String,Skeleton> me : skeletons.entrySet()){
			BinaryProperty skeletonProp = new BinaryProperty();
			skeletonProp.setKey(new BinaryToken(SDBFile.PROPERTY_SKELETON));
			BinaryString str = new BinaryString(me.getKey());
			BinaryObject subObj = new BinaryObject();
			this.toBytesSkeleton(me.getValue(),subObj);
			skeletonProp.setValues(List.of(str,subObj));
			obj.addProperty(skeletonProp);
		}
	}

	private void toBytesSkeleton(Skeleton skeleton,BinaryObject obj){
		if(skeleton.getOffset()!=null){
			BinaryProperty offsetProp = new BinaryProperty();
			offsetProp.setKey(new BinaryToken(SDBFile.PROPERTY_OFFSET));
			BinaryFloat f1 = new BinaryFloat(skeleton.getOffset().getX());
			BinaryFloat f2 = new BinaryFloat(skeleton.getOffset().getY());
			BinaryFloat f3 = new BinaryFloat(skeleton.getOffset().getZ());
			offsetProp.setValues(List.of(f1,f2,f3));
			obj.addProperty(offsetProp);
		}
		if(skeleton.getRotation()!=null){
			BinaryProperty rotationProp = new BinaryProperty();
			rotationProp.setKey(new BinaryToken(SDBFile.PROPERTY_ROTATION));
			BinaryFloat f1 = new BinaryFloat(skeleton.getRotation().getV1());
			BinaryFloat f2 = new BinaryFloat(skeleton.getRotation().getV2());
			BinaryFloat f3 = new BinaryFloat(skeleton.getRotation().getV3());
			BinaryFloat f4 = new BinaryFloat(skeleton.getRotation().getV4());
			rotationProp.setValues(List.of(f1,f2,f3,f4));
			obj.addProperty(rotationProp);
		}
		if(skeleton.getParent()!=null){
			BinaryProperty parentProp = new BinaryProperty();
			parentProp.setKey(new BinaryToken(SDBFile.PROPERTY_PARENT));
			BinaryString str = new BinaryString(skeleton.getParent());
			parentProp.setValues(List.of(str));
			obj.addProperty(parentProp);
		}
	}

	public static SDBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		SDBFile sdb = new SDBFile();
		for(BinaryProperty prop : bin.getProperties()){
			if(prop.getKey().getToken()==SDBFile.PROPERTY_SKELETON){
				SDBFile.fromSkeletons(sdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		return sdb;
	}

	private static void fromSkeletons(SDBFile file,BinaryObject obj){
		Skeletons skeletons = new Skeletons();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==SDBFile.PROPERTY_SKELETON){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				SDBFile.fromSkeleton(skeletons,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.skeletons = skeletons;
	}

	private static void fromSkeleton(Skeletons skeletons,BinaryString str,BinaryObject obj){
		Skeleton skeleton = new Skeleton();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==SDBFile.PROPERTY_OFFSET){
				Position position = new Position();
				position.setX(((BinaryFloat) prop.getValues().get(0)).getFloat());
				position.setY(((BinaryFloat) prop.getValues().get(1)).getFloat());
				position.setZ(((BinaryFloat) prop.getValues().get(2)).getFloat());
				skeleton.setOffset(position);
				continue;
			}
			if(prop.getKey().getToken()==SDBFile.PROPERTY_ROTATION){
				Rotation rotation = new Rotation();
				rotation.setV1(((BinaryFloat) prop.getValues().get(0)).getFloat());
				rotation.setV2(((BinaryFloat) prop.getValues().get(1)).getFloat());
				rotation.setV3(((BinaryFloat) prop.getValues().get(2)).getFloat());
				rotation.setV4(((BinaryFloat) prop.getValues().get(3)).getFloat());
				skeleton.setRotation(rotation);
				continue;
			}
			if(prop.getKey().getToken()==SDBFile.PROPERTY_PARENT){
				BinaryString s = (BinaryString) prop.getValues().get(0);
				skeleton.setParent(s.getString());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		skeletons.put(str.getString(),skeleton);
	}

	@Override
	public String toString() {
		return "SDBFile{" +
				"skeletons=" + skeletons +
				'}';
	}

}

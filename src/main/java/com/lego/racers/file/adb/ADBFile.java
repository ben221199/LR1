package com.lego.racers.file.adb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryContainer;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryProperty;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.BinaryStructInstance;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.binary.object.Position;
import com.lego.racers.file.adb.object.Data;
import com.lego.racers.file.adb.object.Offsets;
import com.lego.racers.file.adb.object.Pointer;
import com.lego.racers.file.adb.object.Pointers;
import com.lego.racers.file.adb.object.Rotation;
import com.lego.racers.file.adb.object.Rotations;
import com.lego.racers.file.adb.object.Sequence;
import com.lego.racers.file.adb.object.Sequences;
import com.lego.racers.file.jam.JAMNodeFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ADBFile extends JAMNodeFile{

	public static final byte PROPERTY_DATA = 0x27;
	public static final byte PROPERTY_OFFSET = 0x28;
	public static final byte PROPERTY_ROTATION = 0x29;
	public static final byte PROPERTY_TIME = 0x2A;
	public static final byte PROPERTY_POINTER = 0x2B;
	public static final byte PROPERTY_SEQUENCE = 0x2C;
	public static final byte PROPERTY_DURATION = 0x2D;
	public static final byte PROPERTY_DURATION2 = 0x2E;
	public static final byte PROPERTY_SPEED = 0x2F;
	public static final byte PROPERTY_OFFSET_INITIAL = 0x30;
	public static final byte PROPERTY_ROTATION_INITIAL = 0x31;

	private Data data;
	private Pointers pointers;
	private Sequences sequences;

	public Data getData(){
		return this.data;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Pointers getPointers(){
		return this.pointers;
	}

	public void setPointers(Pointers pointers){
		this.pointers = pointers;
	}

	public Sequences getSequences(){
		return this.sequences;
	}

	public void setSequences(Sequences sequences){
		this.sequences = sequences;
	}

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		if(this.data!=null){
			BinaryProperty dataProp = new BinaryProperty();
			dataProp.setKey(new BinaryToken(ADBFile.PROPERTY_DATA));
			BinaryObject obj = new BinaryObject();
			this.toBytesData(this.data,obj);
			dataProp.setValues(List.of(obj));
			bin.addProperty(dataProp);
		}
		if(this.pointers!=null){
			BinaryProperty pointersProp = new BinaryProperty();
			pointersProp.setKey(new BinaryToken(ADBFile.PROPERTY_POINTER));
			BinaryArray arr = new BinaryArray();
			arr.getTokens().add(new BinaryIntegerSigned(this.pointers.size()));
			BinaryObject obj = new BinaryObject();
			this.toBytesPointers(this.pointers,obj);
			pointersProp.setValues(List.of(arr,obj));
			bin.addProperty(pointersProp);
		}
		if(this.sequences!=null){
			BinaryProperty sequencesProp = new BinaryProperty();
			sequencesProp.setKey(new BinaryToken(ADBFile.PROPERTY_SEQUENCE));
			BinaryArray arr = new BinaryArray();
			arr.getTokens().add(new BinaryIntegerSigned(this.sequences.size()));
			BinaryObject obj = new BinaryObject();
			this.toBytesSequences(this.sequences,obj);
			sequencesProp.setValues(List.of(arr,obj));
			bin.addProperty(sequencesProp);
		}
		return bin.toBytes();
	}

	private void toBytesData(Data data,BinaryObject obj){
		if(data.getOffsets()!=null){
			BinaryProperty offsetsProp = new BinaryProperty();
			offsetsProp.setKey(new BinaryToken(ADBFile.PROPERTY_OFFSET));
			BinaryArray arr = new BinaryArray();
			arr.getTokens().add(new BinaryIntegerSigned(this.data.getOffsets().size()));
			BinaryObject subObj = new BinaryObject();
			this.toBytesOffsets(data.getOffsets(),subObj);
			offsetsProp.setValues(List.of(arr,subObj));
			obj.addProperty(offsetsProp);
		}
		if(data.getRotations()!=null){
			BinaryProperty rotationProp = new BinaryProperty();
			rotationProp.setKey(new BinaryToken(ADBFile.PROPERTY_ROTATION));
			BinaryArray arr = new BinaryArray();
			arr.getTokens().add(new BinaryIntegerSigned(this.data.getRotations().size()));
			BinaryObject subObj = new BinaryObject();
			this.toBytesRotations(data.getRotations(),subObj);
			rotationProp.setValues(List.of(arr,subObj));
			obj.addProperty(rotationProp);
		}
		if(data.getTimes()!=null){
			BinaryProperty timesProp = new BinaryProperty();
			timesProp.setKey(new BinaryToken(ADBFile.PROPERTY_TIME));
			BinaryArray arr = new BinaryArray();
			arr.getTokens().add(new BinaryIntegerSigned(this.data.getTimes().length));
			BinaryObject subObj = new BinaryObject();
			this.toBytesTimes(data.getTimes(),subObj);
			timesProp.setValues(List.of(arr,subObj));
			obj.addProperty(timesProp);
		}
	}

	private void toBytesOffsets(Offsets offsets,BinaryObject obj){
		for(Position offset : offsets){
			obj.getTokens().add(new BinaryFloat(offset.getX()));
			obj.getTokens().add(new BinaryFloat(offset.getY()));
			obj.getTokens().add(new BinaryFloat(offset.getZ()));
		}
	}

	private void toBytesRotations(Rotations rotations,BinaryObject obj){
		BinaryList list = new BinaryList(BinaryToken.TOKEN_FLOAT);
		for(Rotation rotation : rotations){
			list.getTokens().add(new BinaryFloat(rotation.getV1()));
			list.getTokens().add(new BinaryFloat(rotation.getV2()));
			list.getTokens().add(new BinaryFloat(rotation.getV3()));
			list.getTokens().add(new BinaryFloat(rotation.getV4()));
		}
		obj.getTokens().add(list);
	}

	private void toBytesTimes(int[] times,BinaryObject obj){
		for(int time : times){
			obj.getTokens().add(new BinaryIntegerSigned(time));
		}
	}

	private void toBytesPointers(Pointers pointers,BinaryObject obj){
		BinaryList list = new BinaryList(BinaryToken.TOKEN_INTEGER_SIGNED);
		for(Pointer pointer : pointers){
			list.getTokens().add(new BinaryIntegerSigned(pointer.getV1()));
			list.getTokens().add(new BinaryIntegerSigned(pointer.getV2()));
			list.getTokens().add(new BinaryIntegerSigned(pointer.getV3()));
			list.getTokens().add(new BinaryIntegerSigned(pointer.getV4()));
			list.getTokens().add(new BinaryIntegerSigned(pointer.getV5()));
			list.getTokens().add(new BinaryIntegerSigned(pointer.getV6()));
		}
		obj.getTokens().add(list);
	}

	private void toBytesSequences(Sequences sequences,BinaryObject obj){
		for(Map.Entry<String,Sequence> me : sequences.entrySet()){
			BinaryProperty sequenceProp = new BinaryProperty();
			sequenceProp.setKey(new BinaryToken(ADBFile.PROPERTY_SEQUENCE));
			BinaryString str = new BinaryString(me.getKey());
			BinaryObject subObj = new BinaryObject();
			this.toBytesSequence(me.getValue(),subObj);
			sequenceProp.setValues(List.of(str,subObj));
			obj.addProperty(sequenceProp);
		}
	}

	private void toBytesSequence(Sequence sequence,BinaryObject obj){
		if(sequence.getPointer()!=null){
			BinaryProperty pointerProp = new BinaryProperty();
			pointerProp.setKey(new BinaryToken(ADBFile.PROPERTY_POINTER));
			BinaryIntegerSigned i = new BinaryIntegerSigned(sequence.getPointer());
			pointerProp.setValues(List.of(i));
			obj.addProperty(pointerProp);
		}
		if(sequence.getDuration()!=null){
			BinaryProperty durationProp = new BinaryProperty();
			durationProp.setKey(new BinaryToken(ADBFile.PROPERTY_DURATION));
			BinaryIntegerSigned i = new BinaryIntegerSigned(sequence.getDuration());
			durationProp.setValues(List.of(i));
			obj.addProperty(durationProp);
		}
		if(sequence.getDuration2()!=null){
			BinaryProperty duration2Prop = new BinaryProperty();
			duration2Prop.setKey(new BinaryToken(ADBFile.PROPERTY_DURATION2));
			BinaryIntegerSigned i = new BinaryIntegerSigned(sequence.getDuration2());
			duration2Prop.setValues(List.of(i));
			obj.addProperty(duration2Prop);
		}
		if(sequence.getSpeed()!=null){
			BinaryProperty speedProp = new BinaryProperty();
			speedProp.setKey(new BinaryToken(ADBFile.PROPERTY_SPEED));
			BinaryIntegerSigned i = new BinaryIntegerSigned(sequence.getSpeed());
			speedProp.setValues(List.of(i));
			obj.addProperty(speedProp);
		}
		if(sequence.getInitialOffset()!=null){
			BinaryProperty initialOffsetProp = new BinaryProperty();
			initialOffsetProp.setKey(new BinaryToken(ADBFile.PROPERTY_OFFSET_INITIAL));
			BinaryFloat f1 = new BinaryFloat(sequence.getInitialOffset().getX());
			BinaryFloat f2 = new BinaryFloat(sequence.getInitialOffset().getY());
			BinaryFloat f3 = new BinaryFloat(sequence.getInitialOffset().getZ());
			initialOffsetProp.setValues(List.of(f1,f2,f3));
			obj.addProperty(initialOffsetProp);
		}
		if(sequence.getInitialRotation()!=null){
			BinaryProperty initialRotationProp = new BinaryProperty();
			initialRotationProp.setKey(new BinaryToken(ADBFile.PROPERTY_ROTATION_INITIAL));
			BinaryFloat f1 = new BinaryFloat(sequence.getInitialRotation().getV1());
			BinaryFloat f2 = new BinaryFloat(sequence.getInitialRotation().getV2());
			BinaryFloat f3 = new BinaryFloat(sequence.getInitialRotation().getV3());
			BinaryFloat f4 = new BinaryFloat(sequence.getInitialRotation().getV4());
			initialRotationProp.setValues(List.of(f1,f2,f3,f4));
			obj.addProperty(initialRotationProp);
		}
	}

	public static ADBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		ADBFile adb = new ADBFile();
		for(BinaryProperty prop : bin.getProperties()){
			if(prop.getKey().getToken()==ADBFile.PROPERTY_DATA){
				ADBFile.fromData(adb,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			if(prop.getKey().getToken()==ADBFile.PROPERTY_POINTER){
				ADBFile.fromPointers(adb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==ADBFile.PROPERTY_SEQUENCE){
				ADBFile.fromSequences(adb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		return adb;
	}

	private static void fromData(ADBFile file,BinaryObject obj){
		Data data = new Data();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==ADBFile.PROPERTY_OFFSET){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				ADBFile.fromOffsets(data,(BinaryObject) prop.getValues().get(i));
				continue;
			}
			if(prop.getKey().getToken()==ADBFile.PROPERTY_ROTATION){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				ADBFile.fromRotations(data,(BinaryObject) prop.getValues().get(i));
				continue;
			}
			if(prop.getKey().getToken()==ADBFile.PROPERTY_TIME){
				ADBFile.fromTimes(data,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.data = data;
	}

	private static void fromOffsets(Data data,BinaryObject obj){
		Offsets offsets = new Offsets();
		List<BinaryToken> tokens = new ArrayList<>();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) token;
				for(BinaryToken token2 : list.getTokens()){
					if(token2 instanceof BinaryStructInstance){
						BinaryStructInstance bsi = (BinaryStructInstance) token2;
						tokens.addAll(bsi.getTokens());
					}
				}
				if(list.getType()==BinaryToken.TOKEN_FLOAT){
					tokens.addAll(list.getTokens());
				}
			}
			if(token instanceof BinaryStructInstance){
				BinaryStructInstance bsi = (BinaryStructInstance) token;
				tokens.addAll(bsi.getTokens());
			}
			if(token instanceof BinaryFloat){
				tokens.add(token);
			}
		}
		for(int i=0;i<tokens.size();i+=3){
			Position offset = new Position();
			offset.setX(((BinaryFloat) tokens.get(i)).getFloat());
			offset.setY(((BinaryFloat) tokens.get(i+1)).getFloat());
			offset.setZ(((BinaryFloat) tokens.get(i+2)).getFloat());
			offsets.add(offset);
		}
		data.setOffsets(offsets);
	}

	private static void fromRotations(Data data,BinaryObject obj){
		Rotations rotations = new Rotations();
		List<BinaryToken> tokens = new ArrayList<>();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) token;
				for(BinaryToken token2 : list.getTokens()){
					if(token2 instanceof BinaryStructInstance){
						BinaryStructInstance bsi = (BinaryStructInstance) token2;
						tokens.addAll(bsi.getTokens());
					}
				}
				if(list.getType()==BinaryToken.TOKEN_FLOAT){
					tokens.addAll(list.getTokens());
				}
			}
			if(token instanceof BinaryStructInstance){
				BinaryStructInstance bsi = (BinaryStructInstance) token;
				tokens.addAll(bsi.getTokens());
			}
		}
		for(int i=0;i<tokens.size();i+=4){
			Rotation rotation = new Rotation();
			rotation.setV1(((BinaryFloat) tokens.get(i)).getFloat());
			rotation.setV2(((BinaryFloat) tokens.get(i+1)).getFloat());
			rotation.setV3(((BinaryFloat) tokens.get(i+2)).getFloat());
			rotation.setV4(((BinaryFloat) tokens.get(i+3)).getFloat());
			rotations.add(rotation);
		}
		data.setRotations(rotations);
	}

	private static void fromTimes(Data data,BinaryObject obj){
		BinaryContainer container = obj;
		if(obj.getTokens().size()>0 && obj.getTokens().get(0) instanceof BinaryList){
			container = (BinaryContainer) obj.getTokens().get(0);
		}
		int[] times = new int[container.getTokens().size()];
		for(int i=0;i<times.length;i+=1){
			times[i] = ((BinaryIntegerSigned) container.getTokens().get(i)).getIntegerSigned();
		}
		data.setTimes(times);
	}

	private static void fromPointers(ADBFile file,BinaryObject obj){
		Pointers pointers = new Pointers();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(int i=0;i<list.getTokens().size();i+=6){
			Pointer pointer = new Pointer();
			pointer.setV1(((BinaryIntegerSigned) list.getTokens().get(i)).getIntegerSigned());
			pointer.setV2(((BinaryIntegerSigned) list.getTokens().get(i+1)).getIntegerSigned());
			pointer.setV3(((BinaryIntegerSigned) list.getTokens().get(i+2)).getIntegerSigned());
			pointer.setV4(((BinaryIntegerSigned) list.getTokens().get(i+3)).getIntegerSigned());
			pointer.setV5(((BinaryIntegerSigned) list.getTokens().get(i+4)).getIntegerSigned());
			pointer.setV6(((BinaryIntegerSigned) list.getTokens().get(i+5)).getIntegerSigned());
			pointers.add(pointer);
		}
		file.pointers = pointers;
	}

	private static void fromSequences(ADBFile file,BinaryObject obj){
		Sequences sequences = new Sequences();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==ADBFile.PROPERTY_SEQUENCE){
				ADBFile.fromSequence(sequences,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(1));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.sequences = sequences;
	}

	private static void fromSequence(Sequences sequences,BinaryString str,BinaryObject obj){
		Sequence sequence = new Sequence();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==ADBFile.PROPERTY_POINTER){
				BinaryIntegerSigned i = (BinaryIntegerSigned) prop.getValues().get(0);
				sequence.setPointer(i.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==ADBFile.PROPERTY_DURATION){
				BinaryIntegerSigned i = (BinaryIntegerSigned) prop.getValues().get(0);
				sequence.setDuration(i.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==ADBFile.PROPERTY_DURATION2){
				BinaryIntegerSigned i = (BinaryIntegerSigned) prop.getValues().get(0);
				sequence.setDuration2(i.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==ADBFile.PROPERTY_SPEED){
				BinaryIntegerSigned i = (BinaryIntegerSigned) prop.getValues().get(0);
				sequence.setSpeed(i.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==ADBFile.PROPERTY_OFFSET_INITIAL){
				Position offset = new Position();
				offset.setX(((BinaryFloat) prop.getValues().get(0)).getFloat());
				offset.setY(((BinaryFloat) prop.getValues().get(1)).getFloat());
				offset.setZ(((BinaryFloat) prop.getValues().get(2)).getFloat());
				sequence.setInitialOffset(offset);
				continue;
			}
			if(prop.getKey().getToken()==ADBFile.PROPERTY_ROTATION_INITIAL){
				Rotation rotation = new Rotation();
				List<BinaryToken> tokens = prop.getValues();
				if(tokens.get(0) instanceof BinaryList){
					BinaryList list = (BinaryList) prop.getValues().get(0);
					tokens = list.getTokens();
				}
				rotation.setV1(((BinaryFloat) tokens.get(0)).getFloat());
				rotation.setV2(((BinaryFloat) tokens.get(1)).getFloat());
				rotation.setV3(((BinaryFloat) tokens.get(2)).getFloat());
				rotation.setV4(((BinaryFloat) tokens.get(3)).getFloat());
				sequence.setInitialRotation(rotation);
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		sequences.put(str.getString(),sequence);
	}

	@Override
	public String toString() {
		return "ADBFile{" +
				"data=" + data +
				", pointers=" + pointers +
				", sequences=" + sequences +
				'}';
	}

}

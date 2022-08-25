package com.lego.racers.file.trb;

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
import com.lego.racers.file.trb.object.Trigger;
import com.lego.racers.file.trb.object.Triggers;

import java.util.List;

public class TRBFile{

	public static final byte PROPERTY_TRIGGER = 0x27;
	public static final byte PROPERTY_x29 = 0x29;
	public static final byte PROPERTY_x2A = 0x2A;
	public static final byte PROPERTY_x2B = 0x2B;
	public static final byte PROPERTY_x2D = 0x2D;
	public static final byte PROPERTY_x2E = 0x2E;
	public static final byte PROPERTY_x2F = 0x2F;

	private Triggers triggers;

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		if(this.triggers!=null){
			BinaryProperty triggersProp = new BinaryProperty();
			triggersProp.setKey(new BinaryToken(TRBFile.PROPERTY_TRIGGER));
			BinaryArray arr = new BinaryArray();
			arr.getTokens().add(new BinaryIntegerSigned(this.triggers.size()));
			BinaryObject obj = new BinaryObject();
			this.toBytesTriggers(this.triggers,obj);
			triggersProp.setValues(List.of(arr,obj));
			bin.addProperty(triggersProp);
		}
		return bin.toBytes();
	}

	private void toBytesTriggers(Triggers triggers,BinaryObject obj){
		for(Trigger trigger : triggers){
			BinaryProperty triggerProp = new BinaryProperty();
			triggerProp.setKey(new BinaryToken(TRBFile.PROPERTY_TRIGGER));
			BinaryObject subObj = new BinaryObject();
			this.toBytesTrigger(trigger,subObj);
			triggerProp.setValues(List.of(subObj));
			obj.addProperty(triggerProp);
		}
	}

	private void toBytesTrigger(Trigger trigger,BinaryObject obj){
		if(trigger.getX29()!=null){
			BinaryProperty seatProp = new BinaryProperty();
			seatProp.setKey(new BinaryToken(TRBFile.PROPERTY_x29));
			BinaryFloat f1 = new BinaryFloat(trigger.getX29().getX());
			BinaryFloat f2 = new BinaryFloat(trigger.getX29().getY());
			BinaryFloat f3 = new BinaryFloat(trigger.getX29().getZ());
			seatProp.setValues(List.of(f1,f2,f3));
			obj.addProperty(seatProp);
		}
		if(trigger.getX2A()!=null){
			BinaryProperty x2AProp = new BinaryProperty();
			x2AProp.setKey(new BinaryToken(TRBFile.PROPERTY_x2A));
			BinaryFloat f1 = new BinaryFloat(trigger.getX2A());
			x2AProp.setValues(List.of(f1));
			obj.addProperty(x2AProp);
		}
		if(trigger.getX2B()!=null){
			BinaryProperty x2BProp = new BinaryProperty();
			x2BProp.setKey(new BinaryToken(TRBFile.PROPERTY_x2B));
			BinaryIntegerSigned f1 = new BinaryIntegerSigned(trigger.getX2B());
			x2BProp.setValues(List.of(f1));
			obj.addProperty(x2BProp);
		}
		if(trigger.getX2D()!=null){
			BinaryProperty x2DProp = new BinaryProperty();
			x2DProp.setKey(new BinaryToken(TRBFile.PROPERTY_x2D));
			BinaryString f1 = new BinaryString(trigger.getX2D());
			x2DProp.setValues(List.of(f1));
			obj.addProperty(x2DProp);
		}
		if(trigger.getX2E()!=null){
			BinaryProperty x2EProp = new BinaryProperty();
			x2EProp.setKey(new BinaryToken(TRBFile.PROPERTY_x2E));
			BinaryIntegerSigned f1 = new BinaryIntegerSigned(trigger.getX2E());
			x2EProp.setValues(List.of(f1));
			obj.addProperty(x2EProp);
		}
		if(trigger.getX2F()){
			BinaryProperty x2FProp = new BinaryProperty();
			x2FProp.setKey(new BinaryToken(TRBFile.PROPERTY_x2F));
			obj.addProperty(x2FProp);
		}
	}

	public static TRBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		TRBFile trb = new TRBFile();
		for(BinaryProperty prop : bin.getProperties()){
			if(prop.getKey().getToken()==TRBFile.PROPERTY_TRIGGER){
				TRBFile.fromTriggers(trb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		return trb;
	}

	private static void fromTriggers(TRBFile file,BinaryObject obj){
		Triggers triggers = new Triggers();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==TRBFile.PROPERTY_TRIGGER){
				int i = 0;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				TRBFile.fromTrigger(triggers,(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.triggers = triggers;
	}

	private static void fromTrigger(Triggers triggers,BinaryObject obj){
		Trigger trigger = new Trigger();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==TRBFile.PROPERTY_x29){
				Position position = new Position();
				position.setX(((BinaryFloat)prop.getValues().get(0)).getFloat());
				position.setY(((BinaryFloat)prop.getValues().get(1)).getFloat());
				position.setZ(((BinaryFloat)prop.getValues().get(2)).getFloat());
				trigger.setX29(position);
				continue;
			}
			if(prop.getKey().getToken()==TRBFile.PROPERTY_x2A){
				BinaryFloat f = (BinaryFloat) prop.getValues().get(0);
				trigger.setX2A(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==TRBFile.PROPERTY_x2B){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				trigger.setX2B(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==TRBFile.PROPERTY_x2D){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				trigger.setX2D(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==TRBFile.PROPERTY_x2E){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				trigger.setX2E(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==TRBFile.PROPERTY_x2F){
				trigger.setX2F(true);
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		triggers.add(trigger);
	}

	@Override
	public String toString() {
		return "TRBFile{" +
				"triggers=" + triggers +
				'}';
	}

}

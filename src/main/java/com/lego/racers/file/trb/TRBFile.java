package com.lego.racers.file.trb;

import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryProperty;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.object.Position;
import com.lego.racers.file.trb.object.Trigger;
import com.lego.racers.file.trb.object.Triggers;

public class TRBFile{

	public static final byte PROPERTY_TRIGGERS = 0x27;

	public static final byte PROPERTY_TRIGGER = 0x27;
	public static final byte PROPERTY_x29 = 0x29;
	public static final byte PROPERTY_x2A = 0x2A;
	public static final byte PROPERTY_x2B = 0x2B;
	public static final byte PROPERTY_x2D = 0x2D;
	public static final byte PROPERTY_x2E = 0x2E;
	public static final byte PROPERTY_x2F = 0x2F;

	private Triggers triggers;

	public static TRBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		TRBFile trb = new TRBFile();
		for(BinaryProperty prop : bin.getProperties()){
			if(prop.getKey().getToken()==TRBFile.PROPERTY_TRIGGERS){
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

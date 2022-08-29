package com.lego.racers.file.tgb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.BinaryStructInstance;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.binary.object.Position;
import com.lego.racers.file.jam.JAMNodeFile;
import com.lego.racers.file.tgb.object.Trigger;
import com.lego.racers.file.tgb.object.Triggers;

public class TGBFile extends JAMNodeFile{

	public static final byte BLOCK_TRIGGER = 0x27;

	public static final byte PROPERTY_TRIGGERS = 0x27;
	public static final byte PROPERTY_POSITION = 0x28;
	public static final byte PROPERTY_41 = 0x29;

	private Triggers triggers;

	public Triggers getTriggers(){
		return this.triggers;
	}

	public void setTriggers(Triggers triggers){
		this.triggers = triggers;
	}

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		boolean struct23 = false;
		if(this.triggers!=null){
			bin.getTokens().add(new BinaryToken(TGBFile.PROPERTY_TRIGGERS));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.triggers.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			int i = 0;
			for(Trigger entry : this.triggers){
				obj.getTokens().add(new BinaryToken(TGBFile.BLOCK_TRIGGER));
				if(i==0 && !struct23){
					obj.getTokens().add(new BinaryStruct((byte) 0x17,(byte)4,new byte[]{TGBFile.PROPERTY_POSITION,3,3,3}));
					struct23 = true;
				}
				BinaryObject triggerObj = new BinaryObject();
				if(entry.getPosition()!=null){
					BinaryStructInstance structInstance = new BinaryStructInstance((byte) 0x17);
					structInstance.getTokens().add(new BinaryToken(TGBFile.PROPERTY_POSITION));
					structInstance.getTokens().add(new BinaryFloat(entry.getPosition().getX()));
					structInstance.getTokens().add(new BinaryFloat(entry.getPosition().getY()));
					structInstance.getTokens().add(new BinaryFloat(entry.getPosition().getZ()));
					triggerObj.getTokens().add(structInstance);
				}
				if(entry.getP41()!=null){
					triggerObj.getTokens().add(new BinaryToken(TGBFile.PROPERTY_41));
					triggerObj.getTokens().add(new BinaryIntegerSigned(entry.getP41()));
				}
				obj.getTokens().add(triggerObj);
				i++;
			}
			bin.getTokens().add(obj);
		}
		return bin.toBytes();
	}

	public static TGBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		TGBFile tgb = new TGBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==TGBFile.PROPERTY_TRIGGERS){
				TGBFile.initTriggers(tgb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return tgb;
	}

	private static void initTriggers(TGBFile file,BinaryObject obj){
		Triggers triggers = new Triggers();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==TGBFile.BLOCK_TRIGGER){
				int j = 1;
				while(obj.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				TGBFile.initTrigger(triggers,(BinaryObject) obj.getTokens().get(i+j));
			}
		}
		file.triggers = triggers;
	}

	private static void initTrigger(Triggers triggers,BinaryObject obj){
		Trigger trigger = new Trigger();

		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token instanceof BinaryStructInstance){
				BinaryStructInstance structInstance = (BinaryStructInstance) token;
				if(structInstance.getTokens().get(0).getToken()==TGBFile.PROPERTY_POSITION){
					Position position = new Position();
					position.setX(((BinaryFloat)structInstance.getTokens().get(1)).getFloat());
					position.setY(((BinaryFloat)structInstance.getTokens().get(2)).getFloat());
					position.setZ(((BinaryFloat)structInstance.getTokens().get(3)).getFloat());
					trigger.setPosition(position);
				}
			}
			if(token.getToken()==TGBFile.PROPERTY_41){
				BinaryIntegerSigned integerSigned = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				trigger.setP41(integerSigned.getIntegerSigned());
			}
		}

		triggers.add(trigger);
	}

	@Override
	public String toString() {
		return "TGBFile{" +
				"triggers=" + triggers +
				'}';
	}

}
package com.lego.racers.file.emb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.emb.object.Emitter;
import com.lego.racers.file.emb.object.Emitters;
import com.lego.racers.file.emb.object.Gravity;
import com.lego.racers.file.emb.object.TMPEmitterObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EMBFile{

	public static final byte BLOCK_EMITTERS = 0x27;

	public static final byte PROPERTY_EMITTER = 0x27;
	public static final byte PROPERTY_40 = 0x28;
	public static final byte PROPERTY_41 = 0x29;
	public static final byte PROPERTY_GRAVITY = 0x2A;
	public static final byte PROPERTY_43 = 0x2B;
	public static final byte PROPERTY_START_WIDTH = 0x2C;
	public static final byte PROPERTY_START_HEIGHT = 0x2D;
	public static final byte PROPERTY_46 = 0x2E;
	public static final byte PROPERTY_47 = 0x2F;
	public static final byte PROPERTY_48 = 0x30;
	public static final byte PROPERTY_END_WIDTH = 0x31;
	public static final byte PROPERTY_END_HEIGHT = 0x32;
	public static final byte PROPERTY_DURATION = 0x33;
	public static final byte PROPERTY_MATERIAL = 0x34;
	public static final byte PROPERTY_53 = 0x35;

	private Emitters emitters;

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		if(this.emitters!=null){
			bin.getTokens().add(new BinaryToken(EMBFile.BLOCK_EMITTERS));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.emitters.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			for(Map.Entry<String, Emitter> entry : this.emitters.entrySet()){
				obj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_EMITTER));
				obj.getTokens().add(new BinaryString(entry.getKey()));
				BinaryObject emitterObj = new BinaryObject();
				if(entry.getValue().get40()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_40));
					emitterObj.getTokens().add(new BinaryFloat(entry.getValue().get40()));
				}
				if(entry.getValue().get41()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_41));
					emitterObj.getTokens().add(new BinaryFloat(entry.getValue().get41()));
				}
				if(entry.getValue().getGravity()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_GRAVITY));
					emitterObj.getTokens().add(new BinaryFloat(entry.getValue().getGravity().getX()));
					emitterObj.getTokens().add(new BinaryFloat(entry.getValue().getGravity().getY()));
					emitterObj.getTokens().add(new BinaryFloat(entry.getValue().getGravity().getZ()));
				}
				if(entry.getValue().get43()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_43));
					BinaryArray tmpArr = new BinaryArray();
					tmpArr.getTokens().add(new BinaryIntegerSigned(entry.getValue().get43().size()));
					emitterObj.getTokens().add(tmpArr);
					BinaryObject tmpObj = new BinaryObject();
					BinaryList list = new BinaryList(BinaryToken.TOKEN_FLOAT);
					for(TMPEmitterObject tmp : entry.getValue().get43()){
						list.getTokens().add(new BinaryFloat(tmp.getX()));
						list.getTokens().add(new BinaryFloat(tmp.getY()));
						list.getTokens().add(new BinaryFloat(tmp.getZ()));
					}
					tmpObj.getTokens().add(list);
					emitterObj.getTokens().add(tmpObj);
				}
				if(entry.getValue().getStartWidth()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_START_WIDTH));
					emitterObj.getTokens().add(new BinaryFloat(entry.getValue().getStartWidth()));
				}
				if(entry.getValue().getStartHeight()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_START_HEIGHT));
					emitterObj.getTokens().add(new BinaryFloat(entry.getValue().getStartHeight()));
				}
				if(entry.getValue().get46()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_46));
					emitterObj.getTokens().add(new BinaryFloat(entry.getValue().getGravity().getX()));
					emitterObj.getTokens().add(new BinaryFloat(entry.getValue().getGravity().getY()));
					emitterObj.getTokens().add(new BinaryFloat(entry.getValue().getGravity().getZ()));
				}
				if(entry.getValue().get47()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_47));
					emitterObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get47()));
				}
				if(entry.getValue().get48()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_48));
					emitterObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get48()));
				}
				if(entry.getValue().getEndWidth()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_END_WIDTH));
					emitterObj.getTokens().add(new BinaryFloat(entry.getValue().getEndWidth()));
				}
				if(entry.getValue().getEndWidth()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_END_HEIGHT));
					emitterObj.getTokens().add(new BinaryFloat(entry.getValue().getEndHeight()));
				}
				if(entry.getValue().getDuration()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_DURATION));
					emitterObj.getTokens().add(new BinaryFloat(entry.getValue().getDuration()));
				}
				if(entry.getValue().getMaterial()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_MATERIAL));
					emitterObj.getTokens().add(new BinaryString(entry.getValue().getMaterial()));
				}
				if(entry.getValue().get53()!=null){
					emitterObj.getTokens().add(new BinaryToken(EMBFile.PROPERTY_53));
					emitterObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get53()));
				}
				obj.getTokens().add(emitterObj);
			}
			bin.getTokens().add(obj);
		}
		return bin.toBytes();
	}

	public static EMBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		EMBFile emb = new EMBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()== EMBFile.BLOCK_EMITTERS){
				EMBFile.initEmitters(emb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return emb;
	}

	private static void initEmitters(EMBFile file,BinaryObject obj){
		Emitters emitters = new Emitters();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()== EMBFile.PROPERTY_EMITTER){
				EMBFile.initEmitter(emitters,(BinaryString) obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file.emitters = emitters;
	}

	private static void initEmitter(Emitters emitters,BinaryString str,BinaryObject obj){
		Emitter emitter = new Emitter();

		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==EMBFile.PROPERTY_40){
				BinaryFloat f = (BinaryFloat) obj.getTokens().get(i+1);
				emitter.set40(f.getFloat());
			}
			if(token.getToken()==EMBFile.PROPERTY_41){
				BinaryFloat f = (BinaryFloat) obj.getTokens().get(i+1);
				emitter.set41(f.getFloat());
			}
			if(token.getToken()==EMBFile.PROPERTY_GRAVITY){
				Gravity gravity = new Gravity();
				BinaryFloat x = (BinaryFloat) obj.getTokens().get(i+1);
				BinaryFloat y = (BinaryFloat) obj.getTokens().get(i+2);
				BinaryFloat z = (BinaryFloat) obj.getTokens().get(i+3);
				gravity.setX(x.getFloat());
				gravity.setY(y.getFloat());
				gravity.setZ(z.getFloat());
				emitter.setGravity(gravity);
			}
			if(token.getToken()==EMBFile.PROPERTY_43){
				EMBFile.initTMPEmitterObject(emitter,(BinaryObject) obj.getTokens().get(i+2));
			}
			if(token.getToken()==EMBFile.PROPERTY_START_WIDTH){
				BinaryFloat f = (BinaryFloat) obj.getTokens().get(i+1);
				emitter.setStartWidth(f.getFloat());
			}
			if(token.getToken()==EMBFile.PROPERTY_START_HEIGHT){
				BinaryFloat f = (BinaryFloat) obj.getTokens().get(i+1);
				emitter.setStartHeight(f.getFloat());
			}
			if(token.getToken()==EMBFile.PROPERTY_46){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				emitter.set46(f.getIntegerSigned());
			}
			if(token.getToken()==EMBFile.PROPERTY_47){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				emitter.set47(f.getIntegerSigned());
			}
			if(token.getToken()==EMBFile.PROPERTY_48){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				emitter.set48(f.getIntegerSigned());
			}
			if(token.getToken()==EMBFile.PROPERTY_END_WIDTH){
				BinaryFloat f = (BinaryFloat) obj.getTokens().get(i+1);
				emitter.setEndWidth(f.getFloat());
			}
			if(token.getToken()==EMBFile.PROPERTY_END_HEIGHT){
				BinaryFloat f = (BinaryFloat) obj.getTokens().get(i+1);
				emitter.setEndHeight(f.getFloat());
			}
			if(token.getToken()==EMBFile.PROPERTY_DURATION){
				BinaryFloat f = (BinaryFloat) obj.getTokens().get(i+1);
				emitter.setDuration(f.getFloat());
			}
			if(token.getToken()==EMBFile.PROPERTY_MATERIAL){
				BinaryString string = (BinaryString) obj.getTokens().get(i+1);
				emitter.setMaterial(string.getString());
			}
			if(token.getToken()==EMBFile.PROPERTY_53){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				emitter.set53(f.getIntegerSigned());
			}
		}

		emitters.put(str.getString(),emitter);
	}

	private static void initTMPEmitterObject(Emitter emitter,BinaryObject obj){
		List<TMPEmitterObject> p43 = new ArrayList<>();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) token;
				for(int i=0;i<list.getTokens().size();i+=3){
					BinaryFloat x = (BinaryFloat) list.getTokens().get(i);
					BinaryFloat y = (BinaryFloat) list.getTokens().get(i+1);
					BinaryFloat z = (BinaryFloat) list.getTokens().get(i+2);
					TMPEmitterObject tmp = new TMPEmitterObject();
					tmp.setX(x.getFloat());
					tmp.setY(y.getFloat());
					tmp.setZ(z.getFloat());
					p43.add(tmp);
				}
			}
		}
		emitter.set43(p43);
	}


	@Override
	public String toString() {
		return "EMBFile{" +
				"emitters=" + emitters +
				'}';
	}

}
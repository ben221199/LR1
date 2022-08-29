package com.lego.racers.file.spb;

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
import com.lego.racers.file.spb.object.Orientation;
import com.lego.racers.file.spb.object.Startposition;
import com.lego.racers.file.spb.object.Startpositions;

import java.util.Map;

public class SPBFile extends JAMNodeFile{

	public static final byte BLOCK_STARTPOSITIONS = 0x27;

	public static final byte PROPERTY_STARTPOSITION = 0x27;
	public static final byte PROPERTY_POSITION = 0x28;
	public static final byte PROPERTY_ORIENTATION = 0x29;

	private Startpositions startPositions;

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		boolean struct23 = false;
		boolean struct24 = false;
		if(this.startPositions!=null){
			bin.getTokens().add(new BinaryToken(SPBFile.BLOCK_STARTPOSITIONS));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.startPositions.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			int i = 0;
			for(Map.Entry<Integer, Startposition> entry : this.startPositions.entrySet()){
				obj.getTokens().add(new BinaryToken(SPBFile.PROPERTY_STARTPOSITION));
				obj.getTokens().add(new BinaryIntegerSigned(entry.getKey()));
				if(i==0 && !struct23){
					obj.getTokens().add(new BinaryStruct((byte) 0x17,(byte)4,new byte[]{0x28,3,3,3}));
					struct23 = true;
				}
				BinaryObject startPositionObj = new BinaryObject();
				if(i==0 && !struct24){
					startPositionObj.getTokens().add(new BinaryStruct((byte) 0x18,(byte)7,new byte[]{0x29,3,3,3,3,3,3}));
					struct24 = true;
				}
				Startposition startPosition = entry.getValue();
				if(startPosition.getPosition()!=null){
					BinaryStructInstance position = new BinaryStructInstance((byte) 0x17);
					position.getTokens().add(new BinaryFloat(startPosition.getPosition().getX()));
					position.getTokens().add(new BinaryFloat(startPosition.getPosition().getY()));
					position.getTokens().add(new BinaryFloat(startPosition.getPosition().getZ()));
					startPositionObj.getTokens().add(position);
				}
				if(startPosition.getOrientation()!=null){
					BinaryStructInstance orientation = new BinaryStructInstance((byte) 0x18);
					orientation.getTokens().add(new BinaryFloat(startPosition.getOrientation().getRotationX()));
					orientation.getTokens().add(new BinaryFloat(startPosition.getOrientation().getRotationY()));
					orientation.getTokens().add(new BinaryFloat(startPosition.getOrientation().getRotationZ()));
					orientation.getTokens().add(new BinaryFloat(startPosition.getOrientation().getFlipX()));
					orientation.getTokens().add(new BinaryFloat(startPosition.getOrientation().getFlipY()));
					orientation.getTokens().add(new BinaryFloat(startPosition.getOrientation().getFlipZ()));
					startPositionObj.getTokens().add(orientation);
				}
				obj.getTokens().add(startPositionObj);
				i++;
			}
			bin.getTokens().add(obj);
		}
		return bin.toBytes();
	}

	public static SPBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		SPBFile spb = new SPBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()== SPBFile.BLOCK_STARTPOSITIONS){
				SPBFile.initStartPositions(spb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return spb;
	}

	private static void initStartPositions(SPBFile file,BinaryObject obj){
		Startpositions startPositions = new Startpositions();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==SPBFile.PROPERTY_STARTPOSITION){
				int j = 2;
				while(obj.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				BinaryIntegerSigned index = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				SPBFile.initStartPosition(startPositions,index.getIntegerSigned(),(BinaryObject) obj.getTokens().get(i+j));
			}
		}
		file.startPositions = startPositions;
	}

	private static void initStartPosition(Startpositions startPositions, int index, BinaryObject obj){
		Startposition startPosition = new Startposition();

		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryStructInstance){
				if(((BinaryStructInstance) token).getId()==0x17){
					initPosition(startPosition,(BinaryStructInstance) token);
				}
				if(((BinaryStructInstance) token).getId()==0x18){
					initOrientation(startPosition,(BinaryStructInstance) token);
				}
			}
		}

		startPositions.put(index,startPosition);
	}

	private static void initPosition(Startposition startPosition, BinaryStructInstance structInstance){
		if(structInstance.getTokens().size()>0 && structInstance.getTokens().get(0).getToken()==SPBFile.PROPERTY_POSITION){
			Position position = new Position();
			position.setX(((BinaryFloat)structInstance.getTokens().get(1)).getFloat());
			position.setY(((BinaryFloat)structInstance.getTokens().get(2)).getFloat());
			position.setZ(((BinaryFloat)structInstance.getTokens().get(3)).getFloat());
			startPosition.setPosition(position);
		}
	}

	private static void initOrientation(Startposition startPosition, BinaryStructInstance structInstance){
		if(structInstance.getTokens().size()>0 && structInstance.getTokens().get(0).getToken()==SPBFile.PROPERTY_ORIENTATION){
			Orientation orientation = new Orientation();
			orientation.setRotationX(((BinaryFloat)structInstance.getTokens().get(1)).getFloat());
			orientation.setRotationY(((BinaryFloat)structInstance.getTokens().get(2)).getFloat());
			orientation.setRotationZ(((BinaryFloat)structInstance.getTokens().get(3)).getFloat());
			orientation.setFlipX(((BinaryFloat)structInstance.getTokens().get(4)).getFloat());
			orientation.setFlipY(((BinaryFloat)structInstance.getTokens().get(5)).getFloat());
			orientation.setFlipZ(((BinaryFloat)structInstance.getTokens().get(6)).getFloat());
			startPosition.setOrientation(orientation);
		}
	}

	@Override
	public String toString() {
		return "SPBFile{" +
				"startPositions=" + startPositions +
				'}';
	}

}
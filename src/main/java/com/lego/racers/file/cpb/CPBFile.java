package com.lego.racers.file.cpb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.cpb.object.Checkpoint;
import com.lego.racers.file.cpb.object.Checkpoints;
import com.lego.racers.file.cpb.object.Direction;
import com.lego.racers.file.cpb.object.Timing;

public class CPBFile{

	public static final byte BLOCK_CHECKPOINTS = 0x27;

	public static final byte PROPERTY_CHECKPOINT = 0x27;
	public static final byte PROPERTY_DIRECTION = 0x28;
	public static final byte PROPERTY_TIMING = 0x29;
	public static final byte PROPERTY_LOCATION = 0x2A;

	private Checkpoints checkpoints;

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		if(this.checkpoints!=null){
			bin.getTokens().add(new BinaryToken(CPBFile.BLOCK_CHECKPOINTS));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.checkpoints.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			for(Checkpoint checkpoint : this.checkpoints) {
				obj.getTokens().add(new BinaryToken(CPBFile.PROPERTY_CHECKPOINT));
				BinaryObject checkpointObj = new BinaryObject();
				if (checkpoint.getDirection() != null) {
					checkpointObj.getTokens().add(new BinaryToken(CPBFile.PROPERTY_DIRECTION));
					BinaryArray arr = new BinaryArray();
					arr.getTokens().add(new BinaryFloat(checkpoint.getDirection().getVarA()));
					arr.getTokens().add(new BinaryFloat(checkpoint.getDirection().getVarB()));
					arr.getTokens().add(new BinaryFloat(checkpoint.getDirection().getVarC()));
					arr.getTokens().add(new BinaryFloat(checkpoint.getDirection().getVarD()));
					checkpointObj.getTokens().add(arr);
				}
				checkpointObj.getTokens().add(new BinaryToken(CPBFile.PROPERTY_LOCATION));
				checkpointObj.getTokens().add(new BinaryFloat(checkpoint.getLocationX()));
				checkpointObj.getTokens().add(new BinaryFloat(checkpoint.getLocationY()));
				checkpointObj.getTokens().add(new BinaryFloat(checkpoint.getLocationZ()));
				if (checkpoint.getDirection() != null) {
					checkpointObj.getTokens().add(new BinaryToken(CPBFile.PROPERTY_TIMING));
					BinaryArray arr = new BinaryArray();
					arr.getTokens().add(new BinaryIntegerSigned(checkpoint.getTiming().getVarA()));
					arr.getTokens().add(new BinaryIntegerSigned(checkpoint.getTiming().getVarB()));
					arr.getTokens().add(new BinaryIntegerSigned(checkpoint.getTiming().getVarC()));
					arr.getTokens().add(new BinaryIntegerSigned(checkpoint.getTiming().getVarD()));
					checkpointObj.getTokens().add(arr);
				}
				obj.getTokens().add(checkpointObj);
			}
			bin.getTokens().add(obj);
		}
		return bin.toBytes();
	}

	public static CPBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		CPBFile cpb = new CPBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()== CPBFile.BLOCK_CHECKPOINTS){
				CPBFile.initCheckpoints(cpb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return cpb;
	}

	private static void initCheckpoints(CPBFile file,BinaryObject obj){
		Checkpoints checkpoints = new Checkpoints();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CPBFile.PROPERTY_CHECKPOINT){
				CPBFile.initCheckpoint(checkpoints,(BinaryObject) obj.getTokens().get(i+1));
			}
		}
		file.checkpoints = checkpoints;
	}
//
	private static void initCheckpoint(Checkpoints checkpoints,BinaryObject obj){
		Checkpoint checkpoint = new Checkpoint();

		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CPBFile.PROPERTY_DIRECTION){
				CPBFile.initDirection(checkpoint,(BinaryList) obj.getTokens().get(i+1));
			}
			if(token.getToken()==CPBFile.PROPERTY_LOCATION){
				CPBFile.initLocation(checkpoint,(BinaryFloat) obj.getTokens().get(i+1),(BinaryFloat) obj.getTokens().get(i+2),(BinaryFloat) obj.getTokens().get(i+3));
			}
			if(token.getToken()==CPBFile.PROPERTY_TIMING){
				CPBFile.initTiming(checkpoint,(BinaryList) obj.getTokens().get(i+1));
			}
		}

		checkpoints.add(checkpoint);
	}
//
	private static void initDirection(Checkpoint checkpoint,BinaryList list){
		Direction direction = new Direction();
		direction.setVarA(((BinaryFloat)list.getTokens().get(0)).getFloat());
		direction.setVarB(((BinaryFloat)list.getTokens().get(1)).getFloat());
		direction.setVarC(((BinaryFloat)list.getTokens().get(2)).getFloat());
		direction.setVarD(((BinaryFloat)list.getTokens().get(3)).getFloat());
		checkpoint.setDirection(direction);
	}

	private static void initLocation(Checkpoint checkpoint,BinaryFloat locationX,BinaryFloat locationY,BinaryFloat locationZ){
		checkpoint.setLocationX(locationX.getFloat());
		checkpoint.setLocationY(locationY.getFloat());
		checkpoint.setLocationZ(locationZ.getFloat());
	}

	private static void initTiming(Checkpoint checkpoint,BinaryList list){
		Timing timing = new Timing();
		timing.setVarA(((BinaryIntegerSigned)list.getTokens().get(0)).getIntegerSigned());
		timing.setVarB(((BinaryIntegerSigned)list.getTokens().get(1)).getIntegerSigned());
		timing.setVarC(((BinaryIntegerSigned)list.getTokens().get(2)).getIntegerSigned());
		timing.setVarD(((BinaryIntegerSigned)list.getTokens().get(3)).getIntegerSigned());
		checkpoint.setTiming(timing);
	}

	@Override
	public String toString() {
		return "CPBFile{" +
				"checkpoints=" + checkpoints +
				'}';
	}

}
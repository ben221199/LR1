package com.lego.racers.file.ghb;

import com.lego.racers.binary.BinaryByteSigned;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryShortSigned;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.BinaryStructInstance;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.binary.object.Position;
import com.lego.racers.file.ghb.object.Ghost;
import com.lego.racers.file.ghb.object.Node;
import com.lego.racers.file.ghb.object.Nodes;
import com.lego.racers.file.ghb.object.Orientation;

public class GHBFile{

	public static final byte PROPERTY_NODES = 0x27;
	public static final byte PROPERTY_START_POSITION = 0x28;
	public static final byte PROPERTY_START_ORIENTATION = 0x29;
	public static final byte PROPERTY_42 = 0x2A;
	public static final byte PROPERTY_43 = 0x2B;
	public static final byte PROPERTY_GHOST = 0x2C;

	private Ghost ghost;

	public Ghost getGhost(){
		return this.ghost;
	}

	public void setGhost(Ghost ghost){
		this.ghost = ghost;
	}

	public byte[] toBytes(){
		//TODO
		return new byte[0];
	}

	public static GHBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		GHBFile ghb = new GHBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==GHBFile.PROPERTY_GHOST){
				GHBFile.initGhost(ghb,(BinaryObject) bin.getTokens().get(i+1));
			}
		}
		return ghb;
	}

	private static void initGhost(GHBFile file,BinaryObject obj){
		Ghost ghost = new Ghost();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()== GHBFile.PROPERTY_NODES){
				int j = 2;
				while(obj.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				GHBFile.initNodes(ghost,(BinaryObject) obj.getTokens().get(i+j));
			}
			if(token.getToken()==GHBFile.PROPERTY_START_POSITION){
				Position position = new Position();
				position.setX(((BinaryFloat) obj.getTokens().get(i+1)).getFloat());
				position.setY(((BinaryFloat) obj.getTokens().get(i+2)).getFloat());
				position.setZ(((BinaryFloat) obj.getTokens().get(i+3)).getFloat());
				ghost.setStartPosition(position);
			}
			if(token.getToken()==GHBFile.PROPERTY_START_ORIENTATION){
				GHBFile.initStartOrientation(ghost,(BinaryList) obj.getTokens().get(i+1));
			}
			if(token.getToken()==GHBFile.PROPERTY_42){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				BinaryIntegerSigned ii2 = (BinaryIntegerSigned) obj.getTokens().get(i+2);
				BinaryIntegerSigned ii3 = (BinaryIntegerSigned) obj.getTokens().get(i+3);

				ghost.setP42_1(ii.getIntegerSigned());
				ghost.setP42_2(ii2.getIntegerSigned());
				ghost.setP42_3(ii3.getIntegerSigned());
			}
			if(token.getToken()==GHBFile.PROPERTY_43){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				ghost.setP43(ii.getIntegerSigned());
			}
		}
		file.ghost = ghost;
	}

	private static void initNodes(Ghost ghost,BinaryObject obj){
		Nodes nodes = new Nodes();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(int i=0;i<list.getTokens().size();i++){
			BinaryStructInstance structInstance = (BinaryStructInstance) list.getTokens().get(i);
			GHBFile.initNode(nodes,structInstance);
		}
		ghost.setNodes(nodes);
	}

	private static void initNode(Nodes nodes,BinaryStructInstance structInstance){
		Node node = new Node();

		BinaryShortSigned deltaX = (BinaryShortSigned) structInstance.getTokens().get(0);
		node.setDeltaX((short) deltaX.getShortSigned());
		BinaryShortSigned deltaY = (BinaryShortSigned) structInstance.getTokens().get(1);
		node.setDeltaY((short) deltaY.getShortSigned());
		BinaryShortSigned deltaZ = (BinaryShortSigned) structInstance.getTokens().get(2);
		node.setDeltaZ((short) deltaZ.getShortSigned());

		BinaryByteSigned v4 = (BinaryByteSigned) structInstance.getTokens().get(3);
		node.setV4((byte) v4.getByteSigned());
		BinaryByteSigned v5 = (BinaryByteSigned) structInstance.getTokens().get(4);
		node.setV5((byte) v5.getByteSigned());

		BinaryByteSigned v6 = (BinaryByteSigned) structInstance.getTokens().get(5);
		node.setV6((byte) v6.getByteSigned());
		BinaryByteSigned v7 = (BinaryByteSigned) structInstance.getTokens().get(6);
		node.setV7((byte) v7.getByteSigned());

		nodes.add(node);
	}

	private static void initStartOrientation(Ghost ghost,BinaryList list){
		Orientation orientation = new Orientation();
		orientation.setF1(((BinaryFloat)list.getTokens().get(0)).getFloat());
		orientation.setF2(((BinaryFloat)list.getTokens().get(1)).getFloat());
		orientation.setF3(((BinaryFloat)list.getTokens().get(2)).getFloat());
		orientation.setF4(((BinaryFloat)list.getTokens().get(3)).getFloat());
		ghost.setStartOrientation(orientation);
	}

	@Override
	public String toString() {
		return "GHBFile{" +
				"ghost=" + ghost +
				'}';
	}

}
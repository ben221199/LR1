package com.lego.racers.file.rrb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryByteSigned;
import com.lego.racers.binary.BinaryByteUnsigned;
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
import com.lego.racers.file.rrb.object.Node;
import com.lego.racers.file.rrb.object.Nodes;
import com.lego.racers.file.rrb.object.Orientation;
import com.lego.racers.file.spb.SPBFile;
import com.lego.racers.file.spb.object.Startposition;

import java.util.Map;

public class RRBFile{

	public static final byte PROPERTY_NODES = 0x27;
	public static final byte PROPERTY_START_ORIENTATION = 0x28;
	public static final byte PROPERTY_START_POSITION = 0x29;
	public static final byte PROPERTY_END_POSITION = 0x2A;
	public static final byte PROPERTY_END_ORIENTATION = 0x2B;
	public static final byte PROPERTY_TIME = 0x2C;
	public static final byte PROPERTY_45 = 0x2D;

	private Nodes nodes;

	private Orientation startOrientation;
	private Position startPosition;
	private Position endPosition;
	private Orientation endOrientation;

	private int time;
	private int p45;

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		boolean struct23 = false;
		if(this.nodes!=null){
			bin.getTokens().add(new BinaryToken(RRBFile.PROPERTY_NODES));
			BinaryArray arr = new BinaryArray();
			arr.getTokens().add(new BinaryIntegerSigned(this.nodes.size()));
			bin.getTokens().add(arr);
			if(!struct23){
				bin.getTokens().add(new BinaryStruct((byte) 0x17,(byte)10,new byte[]{13,13,11,11,11,11,11,11,11,12}));
				struct23 = true;
			}
			BinaryObject obj = new BinaryObject();
			BinaryList list = new BinaryList((byte) 0x17);
			for(Node n : this.nodes){
				BinaryStructInstance structInstance = new BinaryStructInstance((byte) 0x17);

				structInstance.getTokens().add(new BinaryShortSigned(n.getDeltaX()));
				structInstance.getTokens().add(new BinaryShortSigned(n.getDeltaY()));
				structInstance.getTokens().add(new BinaryByteSigned(n.getDeltaZ()));

				structInstance.getTokens().add(new BinaryByteSigned(n.getV4()));
				structInstance.getTokens().add(new BinaryByteSigned(n.getV5()));

				structInstance.getTokens().add(new BinaryByteSigned(n.getV6()));
				structInstance.getTokens().add(new BinaryByteSigned(n.getV7()));

				structInstance.getTokens().add(new BinaryByteSigned(n.getV8()));
				structInstance.getTokens().add(new BinaryByteSigned(n.getV9()));

				structInstance.getTokens().add(new BinaryByteUnsigned(n.getV10()));

				list.getTokens().add(structInstance);
			}
			obj.getTokens().add(list);
			bin.getTokens().add(obj);
		}
		if(this.startOrientation!=null){
			bin.getTokens().add(new BinaryToken(RRBFile.PROPERTY_START_ORIENTATION));
			BinaryList list = new BinaryList(BinaryToken.TOKEN_FLOAT);
			list.getTokens().add(new BinaryFloat(this.startOrientation.getF1()));
			list.getTokens().add(new BinaryFloat(this.startOrientation.getF2()));
			list.getTokens().add(new BinaryFloat(this.startOrientation.getF3()));
			list.getTokens().add(new BinaryFloat(this.startOrientation.getF4()));
			bin.getTokens().add(list);
		}
		if(this.startPosition!=null){
			bin.getTokens().add(new BinaryToken(RRBFile.PROPERTY_START_POSITION));
			bin.getTokens().add(new BinaryFloat(this.startPosition.getX()));
			bin.getTokens().add(new BinaryFloat(this.startPosition.getY()));
			bin.getTokens().add(new BinaryFloat(this.startPosition.getZ()));
		}
		if(this.endPosition!=null){
			bin.getTokens().add(new BinaryToken(RRBFile.PROPERTY_END_POSITION));
			bin.getTokens().add(new BinaryFloat(this.endPosition.getX()));
			bin.getTokens().add(new BinaryFloat(this.endPosition.getY()));
			bin.getTokens().add(new BinaryFloat(this.endPosition.getZ()));
		}
		if(this.endOrientation!=null){
			bin.getTokens().add(new BinaryToken(RRBFile.PROPERTY_END_ORIENTATION));
			BinaryList list = new BinaryList(BinaryToken.TOKEN_FLOAT);
			list.getTokens().add(new BinaryFloat(this.endOrientation.getF1()));
			list.getTokens().add(new BinaryFloat(this.endOrientation.getF2()));
			list.getTokens().add(new BinaryFloat(this.endOrientation.getF3()));
			list.getTokens().add(new BinaryFloat(this.endOrientation.getF4()));
			bin.getTokens().add(list);
		}

		bin.getTokens().add(new BinaryToken(RRBFile.PROPERTY_TIME));
		bin.getTokens().add(new BinaryIntegerSigned(this.time));

		bin.getTokens().add(new BinaryToken(RRBFile.PROPERTY_45));
		bin.getTokens().add(new BinaryIntegerSigned(this.p45));

		return bin.toBytes();
	}

	public static RRBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		RRBFile rrb = new RRBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==RRBFile.PROPERTY_NODES){
				int j = 2;
				while(bin.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				RRBFile.initNodes(rrb,(BinaryObject) bin.getTokens().get(i+j));
			}
			if(token.getToken()==RRBFile.PROPERTY_START_ORIENTATION){
				RRBFile.initStartOrientation(rrb,(BinaryList) bin.getTokens().get(i+1));
			}
			if(token.getToken()==RRBFile.PROPERTY_START_POSITION){
				Position position = new Position();
				position.setX(((BinaryFloat) bin.getTokens().get(i+1)).getFloat());
				position.setY(((BinaryFloat) bin.getTokens().get(i+2)).getFloat());
				position.setZ(((BinaryFloat) bin.getTokens().get(i+3)).getFloat());
				rrb.startPosition = position;
			}
			if(token.getToken()==RRBFile.PROPERTY_END_POSITION){
				Position position = new Position();
				position.setX(((BinaryFloat) bin.getTokens().get(i+1)).getFloat());
				position.setY(((BinaryFloat) bin.getTokens().get(i+2)).getFloat());
				position.setZ(((BinaryFloat) bin.getTokens().get(i+3)).getFloat());
				rrb.endPosition = position;
			}
			if(token.getToken()==RRBFile.PROPERTY_END_ORIENTATION){
				RRBFile.initEndOrientation(rrb,(BinaryList) bin.getTokens().get(i+1));
			}
			if(token.getToken()==RRBFile.PROPERTY_TIME){
				BinaryIntegerSigned time = (BinaryIntegerSigned) bin.getTokens().get(i+1);
				rrb.time = time.getIntegerSigned();
			}
			if(token.getToken()==RRBFile.PROPERTY_45){
				BinaryIntegerSigned p45 = (BinaryIntegerSigned) bin.getTokens().get(i+1);
				rrb.p45 = p45.getIntegerSigned();
			}
		}
		return rrb;
	}

	private static void initStartOrientation(RRBFile file,BinaryList list){
		Orientation orientation = new Orientation();
		orientation.setF1(((BinaryFloat)list.getTokens().get(0)).getFloat());
		orientation.setF2(((BinaryFloat)list.getTokens().get(1)).getFloat());
		orientation.setF3(((BinaryFloat)list.getTokens().get(2)).getFloat());
		orientation.setF4(((BinaryFloat)list.getTokens().get(3)).getFloat());
		file.startOrientation = orientation;
	}

	private static void initEndOrientation(RRBFile file,BinaryList list){
		Orientation orientation = new Orientation();
		orientation.setF1(((BinaryFloat)list.getTokens().get(0)).getFloat());
		orientation.setF2(((BinaryFloat)list.getTokens().get(1)).getFloat());
		orientation.setF3(((BinaryFloat)list.getTokens().get(2)).getFloat());
		orientation.setF4(((BinaryFloat)list.getTokens().get(3)).getFloat());
		file.endOrientation = orientation;
	}

	private static void initNodes(RRBFile file,BinaryObject obj){
		Nodes nodes = new Nodes();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(int i=0;i<list.getTokens().size();i++){
			BinaryStructInstance structInstance = (BinaryStructInstance) list.getTokens().get(i);
			RRBFile.initNode(nodes,structInstance);
		}
		file.nodes = nodes;
	}

	private static void initNode(Nodes nodes,BinaryStructInstance structInstance){
		Node node = new Node();

		BinaryShortSigned deltaX = (BinaryShortSigned) structInstance.getTokens().get(0);
		node.setDeltaX((short) deltaX.getShortSigned());
		BinaryShortSigned deltaY = (BinaryShortSigned) structInstance.getTokens().get(1);
		node.setDeltaY((short) deltaY.getShortSigned());
		BinaryByteSigned deltaZ = (BinaryByteSigned) structInstance.getTokens().get(2);
		node.setDeltaZ((byte) deltaZ.getByteSigned());

		BinaryByteSigned v4 = (BinaryByteSigned) structInstance.getTokens().get(3);
		node.setV4((byte) v4.getByteSigned());
		BinaryByteSigned v5 = (BinaryByteSigned) structInstance.getTokens().get(4);
		node.setV5((byte) v5.getByteSigned());

		BinaryByteSigned v6 = (BinaryByteSigned) structInstance.getTokens().get(5);
		node.setV6((byte) v6.getByteSigned());
		BinaryByteSigned v7 = (BinaryByteSigned) structInstance.getTokens().get(6);
		node.setV7((byte) v7.getByteSigned());

		BinaryByteSigned v8 = (BinaryByteSigned) structInstance.getTokens().get(7);
		node.setV8((byte) v8.getByteSigned());
		BinaryByteSigned v9 = (BinaryByteSigned) structInstance.getTokens().get(8);
		node.setV9((byte) v9.getByteSigned());

		BinaryByteUnsigned v10 = (BinaryByteUnsigned) structInstance.getTokens().get(9);
		node.setV10(v10.getByteUnsigned());

		nodes.add(node);
	}

	@Override
	public String toString() {
		return "RRBFile{" +
				"startOrientation=" + startOrientation +
				", startPosition=" + startPosition +
				", endPosition=" + endPosition +
				", endOrientation=" + endOrientation +
				", time=" + time +
				", p45=" + p45 +
				", nodes=" + nodes +
				'}';
	}

}
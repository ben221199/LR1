package com.lego.racers.file.bdb;

import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryShortSigned;
import com.lego.racers.binary.BinaryShortUnsigned;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.BinaryStructInstance;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.binary.object.Position;
import com.lego.racers.file.bdb.object.Region;
import com.lego.racers.file.bdb.object.Regions;
import com.lego.racers.file.bdb.object.Tree;
import com.lego.racers.file.bdb.object.TreeLeaf;
import com.lego.racers.file.bdb.object.TreeParent;
import com.lego.racers.file.bdb.object.VisibleRegions;
import com.lego.racers.file.jam.JAMNodeFile;

public class BDBFile extends JAMNodeFile{

	public static final byte BLOCK_TREE_PARENT = 0x28;
	public static final byte BLOCK_TREE_LEAF = 0x29;

	public static final byte PROPERTY_TREE = 0x27;
	public static final byte PROPERTY_REGIONS = 0x2A;
	public static final byte PROPERTY_VISIBLE_REGIONS = 0x2B;

	private Tree tree;
	private Regions regions;
	private VisibleRegions visibleRegions;

	public byte[] toBytes(){
		//TODO
		return new byte[0];
	}

	public static BDBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		BDBFile bdb = new BDBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==BDBFile.PROPERTY_TREE){
				BDBFile.initTree(bdb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==BDBFile.PROPERTY_REGIONS){
				BDBFile.initRegions(bdb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==BDBFile.PROPERTY_VISIBLE_REGIONS){
				BDBFile.initVisibleRegions(bdb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return bdb;
	}

	private static void initTree(BDBFile file,BinaryObject obj){
		Tree tree = new Tree();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==BDBFile.BLOCK_TREE_PARENT){
				TreeParent parent = new TreeParent();

				parent.setParent(((BinaryIntegerSigned) obj.getTokens().get(i+1)).getIntegerSigned());

				parent.setChild1(((BinaryIntegerSigned) obj.getTokens().get(i+2)).getIntegerSigned());
				parent.setChild2(((BinaryIntegerSigned) obj.getTokens().get(i+3)).getIntegerSigned());

				int j = 4;
				while(obj.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				BinaryList list = (BinaryList) obj.getTokens().get(i+j);

				Position position = new Position();
				position.setX(((BinaryFloat)list.getTokens().get(0)).getFloat());
				position.setY(((BinaryFloat)list.getTokens().get(1)).getFloat());
				position.setZ(((BinaryFloat)list.getTokens().get(2)).getFloat());
				parent.setSelectorPosition(position);
				parent.setSelectorValue(((BinaryFloat)list.getTokens().get(3)).getFloat());

				tree.add(parent);
			}
			if(token instanceof BinaryStructInstance){
				BinaryStructInstance structInstance = (BinaryStructInstance) token;
				BinaryToken token2 = structInstance.getTokens().get(0);
				if(token2.getToken()==BDBFile.BLOCK_TREE_PARENT){
					TreeParent parent = new TreeParent();

					parent.setParent(((BinaryShortUnsigned) structInstance.getTokens().get(1)).getShortUnsigned());

					parent.setChild1(((BinaryShortUnsigned) structInstance.getTokens().get(2)).getShortUnsigned());
					parent.setChild2(((BinaryShortUnsigned) structInstance.getTokens().get(3)).getShortUnsigned());

					Position position = new Position();
					position.setX(((BinaryFloat) structInstance.getTokens().get(4)).getFloat());
					position.setY(((BinaryFloat) structInstance.getTokens().get(5)).getFloat());
					position.setZ(((BinaryFloat) structInstance.getTokens().get(6)).getFloat());
					parent.setSelectorPosition(position);

					parent.setSelectorValue(((BinaryFloat) structInstance.getTokens().get(7)).getFloat());

					tree.add(parent);
				}
				if(token2.getToken()==BDBFile.BLOCK_TREE_LEAF){
					TreeLeaf leaf = new TreeLeaf();

					leaf.setParent(((BinaryShortUnsigned) structInstance.getTokens().get(1)).getShortUnsigned());

					leaf.setGraphOffset(((BinaryShortUnsigned) structInstance.getTokens().get(2)).getShortUnsigned());
					leaf.setGraphLength(((BinaryShortUnsigned) structInstance.getTokens().get(3)).getShortUnsigned());

					leaf.setCarInRegion((short) ((BinaryShortSigned) structInstance.getTokens().get(4)).getShortSigned());

					leaf.setVisibleRegionOffset((short) ((BinaryShortSigned) structInstance.getTokens().get(5)).getShortSigned());
					leaf.setVisibleRegionLength((short) ((BinaryShortSigned) structInstance.getTokens().get(6)).getShortSigned());

					tree.add(leaf);
				}
			}
		}
		file.tree = tree;
	}

	private static void initRegions(BDBFile file,BinaryObject obj){
		Regions regions = new Regions();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(int i=0;i<list.getTokens().size();i+=6){
			Region region = new Region();

			Position bottomRightFront = new Position();
			bottomRightFront.setX(((BinaryFloat) list.getTokens().get(i)).getFloat());
			bottomRightFront.setY(((BinaryFloat) list.getTokens().get(i+1)).getFloat());
			bottomRightFront.setZ(((BinaryFloat) list.getTokens().get(i+2)).getFloat());
			region.setBottomLeftFront(bottomRightFront);

			Position upperRightBack = new Position();
			upperRightBack.setX(((BinaryFloat) list.getTokens().get(i+3)).getFloat());
			upperRightBack.setY(((BinaryFloat) list.getTokens().get(i+4)).getFloat());
			upperRightBack.setZ(((BinaryFloat) list.getTokens().get(i+5)).getFloat());
			region.setBottomLeftFront(upperRightBack);

			regions.add(region);
		}
		file.regions = regions;
	}

	private static void initVisibleRegions(BDBFile file,BinaryObject obj){
		VisibleRegions visibleRegions = new VisibleRegions();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(int i=0;i<list.getTokens().size();i++){
			BinaryIntegerSigned ii = (BinaryIntegerSigned) list.getTokens().get(i);
			visibleRegions.add(ii.getIntegerSigned());
		}
		file.visibleRegions = visibleRegions;
	}

	@Override
	public String toString() {
		return "BDBFile{" +
				"tree=" + tree +
				", regions=" + regions +
				", visibleRegions=" + visibleRegions +
				'}';
	}

}
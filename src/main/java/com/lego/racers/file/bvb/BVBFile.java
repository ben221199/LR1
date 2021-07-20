package com.lego.racers.file.bvb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryShortUnsigned;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.BinaryStructInstance;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.binary.object.Position;
import com.lego.racers.file.bvb.object._Unknown142;
import com.lego.racers.file.bvb.object._Unknown45;
import com.lego.racers.file.bvb.object._Unknowns142;
import com.lego.racers.file.bvb.object._Unknowns39;
import com.lego.racers.file.bvb.object._Unknowns45;
import com.lego.racers.file.bvb.object.Positions;

public class BVBFile{

	public static final byte PROPERTY_39 = 0x27;
	public static final byte PROPERTY_45 = 0x2D;
	public static final byte PROPERTY_52 = 0x34;
	public static final byte PROPERTY_142 = (byte) 0x8E;

	private _Unknowns39 _unknowns39;
	private _Unknowns45 _unknowns45;
	private Positions positions;
	private _Unknowns142 _unknowns142;

	public static BVBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		BVBFile bvb = new BVBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==BVBFile.PROPERTY_39){
				BVBFile.init_Unknowns39(bvb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==BVBFile.PROPERTY_45){
				int j = 2;
				while(bin.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				BVBFile.init_Unknowns45(bvb,(BinaryObject) bin.getTokens().get(i+j));
			}
			if(token.getToken()==BVBFile.PROPERTY_52){
				BVBFile.initPositions(bvb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==BVBFile.PROPERTY_142){
				BinaryArray arr = (BinaryArray) bin.getTokens().get(i+1);
				BVBFile.init_Unknowns142(bvb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return bvb;
	}

	private static void init_Unknowns39(BVBFile file,BinaryObject obj){
		_Unknowns39 _unknowns39 = new _Unknowns39();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) obj.getTokens().get(0);
				for(int j=0;j<list.getTokens().size();j++){
					BinaryString str = (BinaryString) list.getTokens().get(j);
					_unknowns39.add(str.getString());
				}
			}
			if(token instanceof BinaryString){
				_unknowns39.add(((BinaryString) token).getString());
			}
		}
		file._unknowns39 = _unknowns39;
	}

	private static void init_Unknowns45(BVBFile file,BinaryObject obj){
		_Unknowns45 _unknowns45 = new _Unknowns45();
		BinaryToken token = obj.getTokens().get(0);
		if(token instanceof BinaryList){
			BinaryList list = (BinaryList) token;
			for(int i=0;i<list.getTokens().size();i++){
				BVBFile.init_Unknown45(_unknowns45,(BinaryStructInstance) list.getTokens().get(i));
			}
		}
		if(token instanceof BinaryStructInstance){
			BVBFile.init_Unknown45(_unknowns45,(BinaryStructInstance) token);
		}
		file._unknowns45 = _unknowns45;
	}

	private static void init_Unknown45(_Unknowns45 _unknowns45,BinaryStructInstance structInstance){
		_Unknown45 _unknown45 = new _Unknown45();

		_unknown45.setV1(((BinaryShortUnsigned) structInstance.getTokens().get(0)).getShortUnsigned());
		_unknown45.setV2(((BinaryShortUnsigned) structInstance.getTokens().get(1)).getShortUnsigned());
		_unknown45.setV3(((BinaryShortUnsigned) structInstance.getTokens().get(2)).getShortUnsigned());
		_unknown45.setV4(((BinaryShortUnsigned) structInstance.getTokens().get(3)).getShortUnsigned());

		_unknowns45.add(_unknown45);
	}

	private static void initPositions(BVBFile file,BinaryObject obj){
		Positions positions = new Positions();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(int i=0;i<list.getTokens().size();i+=3){
			Position position = new Position();
			position.setX(((BinaryFloat)list.getTokens().get(i)).getFloat());
			position.setY(((BinaryFloat)list.getTokens().get(i+1)).getFloat());
			position.setZ(((BinaryFloat)list.getTokens().get(i+2)).getFloat());
			positions.add(position);
		}
		file.positions = positions;
	}

	private static void init_Unknowns142(BVBFile file,BinaryObject obj){
		_Unknowns142 _unknowns142 = new _Unknowns142();

		BinaryObject newObj = new BinaryObject();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token instanceof BinaryList){
				newObj.getTokens().addAll(((BinaryList) token).getTokens());
			}
			if(token instanceof BinaryStructInstance){
				newObj.getTokens().addAll(((BinaryStructInstance) token).getTokens());
			}
			if(token instanceof BinaryIntegerSigned){
				newObj.getTokens().add(token);
			}
		}

		for(int i=0;i<newObj.getTokens().size();i+=7){
			_Unknown142 _unknown142 = new _Unknown142();

			BinaryToken v1 = newObj.getTokens().get(i);
			if(v1 instanceof BinaryIntegerSigned){
				_unknown142.setV1(((BinaryIntegerSigned) v1).getIntegerSigned());
			}
			if(v1 instanceof BinaryShortUnsigned){
				_unknown142.setV1(((BinaryShortUnsigned) v1).getShortUnsigned());
			}

			BinaryToken v2 = newObj.getTokens().get(i+1);
			if(v2 instanceof BinaryIntegerSigned){
				_unknown142.setV2(((BinaryIntegerSigned) v2).getIntegerSigned());
			}
			if(v2 instanceof BinaryShortUnsigned){
				_unknown142.setV2(((BinaryShortUnsigned) v2).getShortUnsigned());
			}

			BinaryToken v3 = newObj.getTokens().get(i+2);
			if(v3 instanceof BinaryIntegerSigned){
				_unknown142.setV3(((BinaryIntegerSigned) v3).getIntegerSigned());
			}
			if(v3 instanceof BinaryShortUnsigned){
				_unknown142.setV3(((BinaryShortUnsigned) v3).getShortUnsigned());
			}

			BinaryToken v4 = newObj.getTokens().get(i+3);
			if(v4 instanceof BinaryIntegerSigned){
				_unknown142.setV4(((BinaryIntegerSigned) v4).getIntegerSigned());
			}
			if(v4 instanceof BinaryShortUnsigned){
				_unknown142.setV4(((BinaryShortUnsigned) v4).getShortUnsigned());
			}

			BinaryToken v5 = newObj.getTokens().get(i);
			if(v5 instanceof BinaryIntegerSigned){
				_unknown142.setV5(((BinaryIntegerSigned) v5).getIntegerSigned());
			}
			if(v5 instanceof BinaryShortUnsigned){
				_unknown142.setV5(((BinaryShortUnsigned) v5).getShortUnsigned());
			}

			BinaryToken v6 = newObj.getTokens().get(i);
			if(v6 instanceof BinaryIntegerSigned){
				_unknown142.setV6(((BinaryIntegerSigned) v6).getIntegerSigned());
			}
			if(v6 instanceof BinaryShortUnsigned){
				_unknown142.setV6(((BinaryShortUnsigned) v6).getShortUnsigned());
			}

			BinaryToken v7 = newObj.getTokens().get(i);
			if(v7 instanceof BinaryIntegerSigned){
				_unknown142.setV7(((BinaryIntegerSigned) v7).getIntegerSigned());
			}
			if(v7 instanceof BinaryShortUnsigned){
				_unknown142.setV7(((BinaryShortUnsigned) v7).getShortUnsigned());
			}

			_unknowns142.add(_unknown142);
		}
		file._unknowns142 = _unknowns142;
	}

	@Override
	public String toString() {
		return "BVBFile{" +
				"_unknowns39=" + _unknowns39 +
				", _unknowns45=" + _unknowns45 +
				", positions=" + positions +
				", _unknowns142=" + _unknowns142 +
				'}';
	}

}
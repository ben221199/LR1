package com.lego.racers.file.tib;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.spb.SPBFile;
import com.lego.racers.file.tib.object._Timer;
import com.lego.racers.file.tib.object._Timers;

public class TIBFile{

	public static final byte BLOCK__TIMERS = 0x27;

	public static final byte PROPERTY__TIMERS = 0x27;
	public static final byte PROPERTY_40 = 0x28;
	public static final byte PROPERTY_41 = 0x29;
	public static final byte PROPERTY_42 = 0x2A;
	public static final byte PROPERTY_43 = 0x2B;
	public static final byte PROPERTY_45 = 0x2D;

	private _Timers _timers;

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		if(this._timers!=null){
			bin.getTokens().add(new BinaryToken(TIBFile.BLOCK__TIMERS));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this._timers.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			for(_Timer entry : this._timers){
				obj.getTokens().add(new BinaryToken(SPBFile.PROPERTY_STARTPOSITION));
				BinaryObject obj2 = new BinaryObject();
				//TODO Fix serialization
				obj.getTokens().add(obj);
			}
			bin.getTokens().add(obj);
		}
		return bin.toBytes();
	}

	public static TIBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		TIBFile tib = new TIBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==TIBFile.BLOCK__TIMERS){
				TIBFile.init_Timers(tib,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return tib;
	}

	private static void init_Timers(TIBFile file,BinaryObject obj){
		_Timers _timers = new _Timers();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==TIBFile.PROPERTY__TIMERS){
				TIBFile.init_Timer(_timers,(BinaryObject) obj.getTokens().get(i+1));
			}
		}
		file._timers = _timers;
	}

	private static void init_Timer(_Timers _timers,BinaryObject obj){
		_Timer _timer = new _Timer();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==TIBFile.PROPERTY_40){
				//TODO Fix deserialization
				if(obj.getTokens().get(i+1) instanceof BinaryIntegerSigned){
					BinaryIntegerSigned is = (BinaryIntegerSigned) obj.getTokens().get(i+1);
					_timer.setP40(is.getIntegerSigned());
				}
			}
			if(token.getToken()==TIBFile.PROPERTY_41){
				//TODO Fix deserialization
				if(obj.getTokens().get(i+1) instanceof BinaryIntegerSigned){
					BinaryIntegerSigned is = (BinaryIntegerSigned) obj.getTokens().get(i+1);
					_timer.setP41(is.getIntegerSigned());
				}
			}
			if(token.getToken()==TIBFile.PROPERTY_42){
				BinaryIntegerSigned is = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				_timer.setP42(is.getIntegerSigned());
			}
			if(token.getToken()==TIBFile.PROPERTY_43){
				BinaryIntegerSigned is = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				_timer.setP43(is.getIntegerSigned());
			}
			if(token.getToken()==TIBFile.PROPERTY_45){
				BinaryIntegerSigned is = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				_timer.setP45(is.getIntegerSigned());
			}
		}
		_timers.add(_timer);
	}

	@Override
	public String toString() {
		return "TIBFile{" +
				"_timers=" + _timers +
				'}';
	}

}
